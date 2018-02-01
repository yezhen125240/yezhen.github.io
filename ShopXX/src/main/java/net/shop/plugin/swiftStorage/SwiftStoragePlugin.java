/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.plugin.swiftStorage;


import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import net.shop.entity.PluginConfig;
import net.shop.plugin.StoragePlugin;

import org.apache.commons.lang.StringUtils;
import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.springframework.stereotype.Component;

/**
 * Plugin - Swift存储
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Component("swiftStoragePlugin")
public class SwiftStoragePlugin extends StoragePlugin {

	@Override
	public String getName() {
		return "Swift";
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
		return "swift_storage/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "swift_storage/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "swift_storage/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String userId = pluginConfig.getAttribute("userId");
			String secretKey = pluginConfig.getAttribute("secretKey");
			String authUrl = pluginConfig.getAttribute("authUrl");
			String containerName = pluginConfig.getAttribute("containerName");
			String fileName = StringUtils.removeStart(path, "/");
			
			AccountConfig config = new AccountConfig();
	        config.setUsername(userId);
	        config.setPassword(secretKey);
	        config.setAuthUrl(authUrl);
	        config.setAuthenticationMethod(AuthenticationMethod.BASIC);
	        Account account = new AccountFactory(config).createAccount();
	        
	        Container container = account.getContainer(containerName);
	        
	        if(!container.exists()) {
	  
	        	  container.create();
	        	//设置成公共可读写
	        	  container.makePublic();
	        }
	        Map<String, Object> metadata = new TreeMap<String, Object>();
	        metadata.put("ContentType", contentType);
	        metadata.put("ContentLength", file.length());
	        StoredObject object = container.getObject(fileName);
	        object.uploadObject(file);
	        object.setMetadata(metadata);
	       
	        //System.out.print(object.getPublicURL());
	        //设置分享外链，过期时间为1800秒
	        //pluginConfig.getAttributes().put("urlPrefix", object.getTempGetUrl(1800));
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