/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.shop.FileType;
import net.shop.Setting;
import net.shop.entity.UploadTask;
import net.shop.plugin.StoragePlugin;
import net.shop.service.FileService;
import net.shop.service.PluginService;
import net.shop.service.UploadTaskService;
import net.shop.util.FreeMarkerUtils;
import net.shop.util.SystemUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import freemarker.template.TemplateException;

/**
 * Service - 文件
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements FileService, ServletContextAware {

	/** ServletContext */
	private ServletContext servletContext;

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	@Resource(name = "pluginServiceImpl")
	private PluginService pluginService;
	//Add file upload to storage monitor
	@Resource(name = "uploadTaskServiceImpl")
	private UploadTaskService uploadTaskService;
	/**
	 * 设置ServletContext
	 * 
	 * @param servletContext
	 *            ServletContext
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 添加文件上传任务
	 * 
	 * @param storagePlugin
	 *            存储插件
	 * @param path
	 *            上传路径
	 * @param file
	 *            上传文件
	 * @param contentType
	 *            文件类型
	 */
	private void addUploadTask(final StoragePlugin storagePlugin, final String path, final File file, final String contentType,final UploadTask uploadTask) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				upload(storagePlugin, path, file, contentType,uploadTask);
			}
		});
	}

	/**
	 * 上传文件
	 * 
	 * @param storagePlugin
	 *            存储插件
	 * @param path
	 *            上传路径
	 * @param file
	 *            上传文件
	 * @param contentType
	 *            文件类型
	 */
	private void upload(StoragePlugin storagePlugin, String path, File file, String contentType, UploadTask uploadTask) {
		Assert.notNull(storagePlugin);
		Assert.hasText(path);
		Assert.notNull(file);
		Assert.hasText(contentType);
		UploadTask temp = uploadTask;
		try {
			temp.setStorageStart(new Date());
			storagePlugin.upload(path, file, contentType);
			FileUtils.deleteQuietly(file);
			temp.setStorageEnd(new Date());
			temp.setViewLink(storagePlugin.getUrl(path));
			temp.setStatus(1);
			uploadTaskService.updateUploadTask(temp);
		} catch(Exception e) {
			FileUtils.deleteQuietly(file);
			temp.setStatus(-1);
			uploadTaskService.updateUploadTask(temp);
		}
	}

	public boolean isValid(FileType fileType, MultipartFile multipartFile) {
		Assert.notNull(fileType);
		Assert.notNull(multipartFile);
		Assert.state(!multipartFile.isEmpty());

		Setting setting = SystemUtils.getSetting();
		if (setting.getUploadMaxSize() != null && setting.getUploadMaxSize() != 0 && multipartFile.getSize() > setting.getUploadMaxSize() * 1024L * 1024L) {
			return false;
		}
		String[] uploadExtensions;
		switch (fileType) {
		case media:
			uploadExtensions = setting.getUploadMediaExtensions();
			break;
		case file:
			uploadExtensions = setting.getUploadFileExtensions();
			break;
		case image:
			uploadExtensions = setting.getUploadImageExtensions();
			break;
		default:
			String filterExtensions= new String("jpg,jpeg,bmp,gif,png,swf,flv,mp3,wav,avi,rm,rmvb,zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx");
			uploadExtensions = StringUtils.split(filterExtensions, ",");
			break;
		}
		if (ArrayUtils.isNotEmpty(uploadExtensions)) {
			return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), uploadExtensions);
		}
		return false;
	}

	public String upload(FileType fileType, MultipartFile multipartFile, boolean async) {
		Assert.notNull(fileType);
		Assert.notNull(multipartFile);
		Assert.state(!multipartFile.isEmpty());

		Setting setting = SystemUtils.getSetting();
		String uploadPath;
		switch (fileType) {
		case media:
			uploadPath = setting.getMediaUploadPath();
			break;
		case file:
			uploadPath = setting.getFileUploadPath();
			break;
		default:
			uploadPath = setting.getImageUploadPath();
			break;
		}
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("uuid", UUID.randomUUID().toString());
			String path = FreeMarkerUtils.process(uploadPath, model);
			String destPath = path + UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			
			StoragePlugin storagePlugin = pluginService.getStoragePlugins(true).get(0);
			
				
				//Add file upload to storage monitor task
			    UploadTask uploadTask = new UploadTask();
				uploadTask.setFileName(multipartFile.getOriginalFilename());
				uploadTask.setDestPath(destPath);
				uploadTask.setFileSize(multipartFile.getSize()/1024);
				uploadTask.setStorageType(storagePlugin.getName());
				//"0" is for upload progressing
				uploadTask.setStatus(0);
				//Set http upload star time
				uploadTask.setHttpStart(new Date());
				File tempFile = new File(FileUtils.getTempDirectory(), UUID.randomUUID() + ".tmp");
				multipartFile.transferTo(tempFile);
				//set http upload end time
				uploadTask.setHttpEnd(new Date());
				
				//record current upload task 
				uploadTask = uploadTaskService.addUploadTask(uploadTask);
				
				String viewUrl = "Wait for generating URL";
				
				String contentType = multipartFile.getContentType();
				if (async) {
					addUploadTask(storagePlugin, destPath, tempFile, contentType,uploadTask);
				} else {
					upload(storagePlugin, destPath, tempFile, contentType,uploadTask);
				}
				viewUrl = storagePlugin.getUrl(destPath);
				return viewUrl;
			
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		//return null;
	}

	public String upload(FileType fileType, MultipartFile multipartFile) {
		Assert.notNull(fileType);
		Assert.notNull(multipartFile);
		Assert.state(!multipartFile.isEmpty());

		return upload(fileType, multipartFile, true);
	}

	public String uploadLocal(FileType fileType, MultipartFile multipartFile) {
		Assert.notNull(fileType);
		Assert.notNull(multipartFile);
		Assert.state(!multipartFile.isEmpty());

		Setting setting = SystemUtils.getSetting();
		String uploadPath;
		switch (fileType) {
		case media:
			uploadPath = setting.getMediaUploadPath();
			break;
		case file:
			uploadPath = setting.getFileUploadPath();
			break;
		default:
			uploadPath = setting.getImageUploadPath();
			break;
		}
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("uuid", UUID.randomUUID().toString());
			String path = FreeMarkerUtils.process(uploadPath, model);
			String destPath = path + UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			File destFile = new File(servletContext.getRealPath(destPath));
			new File(path).mkdirs();
			multipartFile.transferTo(destFile);
			return destPath;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (TemplateException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}