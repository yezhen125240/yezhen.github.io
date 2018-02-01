package net.shop.service;


import java.util.List;

import net.shop.entity.UploadTask;

public interface UploadTaskService extends BaseService<UploadTask, Long>{
	
	public UploadTask addUploadTask(UploadTask uploadTask);
	
	public UploadTask updateUploadTask(UploadTask uploadTask);
	
	public List<UploadTask> findAll();
}
