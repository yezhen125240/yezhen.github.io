package net.shop.dao;


import java.util.List;

import net.shop.entity.UploadTask;

public interface UploadTaskDao extends BaseDao<UploadTask, Long> {
	
	public List<UploadTask> findAll(boolean isUp);

}
