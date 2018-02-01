package net.shop.objstorage;

import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author swzhang
 * @create 2016/5/30.
 */
public class SwiftSample {
	
	    
    public static void main(String[] args) throws IOException {
    	
        /**
         * swift用户名。
         * eg. testuser:swift
         */
        String username = "e7d458fdddfa4061a3d4c819c64199d5:yyzxbwebtest";

        /**
         * swift用户的password，即secretKey.
         */
        String password = "XVCBvoDR1IvUUYTCFFtBKYYEDzZOCc7Cm1OUUgFC";

        /**
         * 进行身份验证的url，由云存储服务商提供。
         * eg. http://10.128.12.1/auth
         * http://eos-beijing-1.cmecloud.cn/auth/1.0
         */
        String authUrl = "http://eos-beijing-1.cmecloud.cn/auth/1.0";

        /**
         * 初始化客户端
         */
        AccountConfig config = new AccountConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setAuthUrl(authUrl);
        config.setAuthenticationMethod(AuthenticationMethod.BASIC);
        Account account = new AccountFactory(config).createAccount();

        /**
         * 创建一个容器，并且设置其可让其他用户访问。在默认情况下，
         * 新创建的容器的访问权限是private，即只有创建者可以访问。
         */
       
        Container container = account.getContainer("yyzxbtest001-001");
       
        if(!container.exists()) {
      	  
      	  container.create();
      	  
      	  container.makePublic();
      }
        
        System.out.println("create container completed! \n");

        /**
         * 为了确认上一步中的创建容器操作成功，利用list接口，可以列出该
         * 用户下的所有已创建容器。
         */
        
        Collection<Container> containers = account.list();
        for (Container currentContainer : containers) {
            System.out.println(currentContainer.getName());
        }
        System.out.println("list container completed! \n");
        /**
         * 设置容器的meta data。
         */
      
        Map<String, Object> metadata = new TreeMap<String, Object>();
        metadata.put("title", "Some Title");
        metadata.put("department", "Some Department");
        container.setMetadata(metadata);
        System.out.println("set comtainer metadata completed! \n");

        /**
         * 获取容器的meta data。
         */
        Map<String, Object> returnedMetadata = container.getMetadata();
        for (String name : returnedMetadata.keySet()) {
            System.out.println("META / "+name+": "+returnedMetadata.get(name));
        }
        System.out.println("Get comtainer metadata completed! \n");
        /**
         * 上传一个本地文件。
         */
        StoredObject object = container.getObject("zxbtest/dog1"+UUID.randomUUID());
        object.uploadObject(createSampleFile());
        System.out.println("upload object: " + object.getName());

        /**
         * 获取上传文件的元数据信息。
         */
        System.out.println("Last modified:  "+object.getLastModified());
        System.out.println("ETag:           "+object.getEtag());
        System.out.println("Content type:   "+object.getContentType());
        System.out.println("Content length: "+object.getContentLength());

        /**
         * 获取容器中的对象列表。
         */
        Collection<StoredObject> objects = container.list();
        for (StoredObject currentObject : objects) {
            System.out.println(currentObject.getName());
        }

        /**
         * 下载文件到本地。
         */
       // object.downloadObject(new File("/dog2.png"));

        /**
         * 拷贝对象。
         */
//        StoredObject newObject = container.getObject("new-dog.png");
//        object.copyObject(container, newObject);

    }
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("01234567890112345678901234\n");
        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
        writer.write("01234567890112345678901234\n");
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.close();

        return file;
    }
}
