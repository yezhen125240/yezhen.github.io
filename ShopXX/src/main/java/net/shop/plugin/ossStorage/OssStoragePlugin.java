/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.plugin.ossStorage;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import net.shop.entity.PluginConfig;
import net.shop.plugin.StoragePlugin;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Plugin - 阿里云存储
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Component("ossStoragePlugin")
public class OssStoragePlugin extends StoragePlugin {

	@Override
	public String getName() {
		return "云存储S3";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "SHOP++";
	}

	@Override
	public String getSiteUrl() {
		return "http://www.shopxx.net";
	}

	@Override
	public String getInstallUrl() {
		return "oss_storage/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "oss_storage/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "oss_storage/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		
		 uploadtoCMMCloud(path,file,contentType);

	}
	
	private void uploadtoAliyun(String path, File file, String contentType) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String endpoint = pluginConfig.getAttribute("endpoint");
			String accessId = pluginConfig.getAttribute("accessId");
			String accessKey = pluginConfig.getAttribute("accessKey");
			String bucketName = pluginConfig.getAttribute("bucketName");
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
				
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentType(contentType);
				objectMetadata.setContentLength(file.length());
				
				String  fileName = StringUtils.removeStart(path, "/");
				
				ossClient.putObject(bucketName, fileName, inputStream, objectMetadata);
				
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
		}
	}
	private void uploadtoCMMCloud(String path, File file, String contentType) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String endpoint = pluginConfig.getAttribute("endpoint");
			String accessId = pluginConfig.getAttribute("accessId");
			String accessKey = pluginConfig.getAttribute("accessKey");
			String bucketName = pluginConfig.getAttribute("bucketName");
			String fileName = StringUtils.removeStart(path, "/");
			
			AWSCredentials credentials = new BasicAWSCredentials(accessId, accessKey);
		    ClientConfiguration opts = new ClientConfiguration();
		    opts.setSignerOverride("S3SignerType");
		    AmazonS3 s3 = new AmazonS3Client(credentials,opts);
		    s3.setEndpoint(endpoint);
			
			try {		
				if(!s3.doesBucketExist(bucketName)){

					CreateBucketRequest request = new CreateBucketRequest(bucketName);
					//默认权限是私有读写，设置成公共可读
					request.setCannedAcl(CannedAccessControlList.PublicRead);
					s3.createBucket(request);
				}
				com.amazonaws.services.s3.model.ObjectMetadata obMD = new com.amazonaws.services.s3.model.ObjectMetadata();
				obMD.setContentType(contentType);
				obMD.setContentLength(file.length());
				
				//s3.putObject(new PutObjectRequest(bucketName, fileName, file));
				
				s3.putObject(bucketName, fileName, new FileInputStream(file),obMD);
				//默认权限是私有读写，设置成公共可读
				s3.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);
				
			} catch (AmazonServiceException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch(AmazonClientException e){
				throw  new RuntimeException(e.getMessage(), e);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			finally {
			}
		}
	}
	@Override
	public String getUrl(String path) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String urlPrefix = pluginConfig.getAttribute("urlPrefix");
			return urlPrefix + path;
		}
		return null;
	}

}