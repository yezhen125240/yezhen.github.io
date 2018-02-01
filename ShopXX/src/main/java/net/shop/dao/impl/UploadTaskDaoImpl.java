package net.shop.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import net.shop.dao.UploadTaskDao;
import net.shop.entity.Area;
import net.shop.entity.UploadTask;

@Repository("uploadTaskDaoImpl")
public class UploadTaskDaoImpl extends BaseDaoImpl<UploadTask, Long> implements UploadTaskDao{

	public List<UploadTask> findAll(boolean isUp){
		
		String jpql = "select uploadTask from UploadTask uploadTask order by uploadTask.httpStart";
		jpql= isUp ? jpql + " ASC" : jpql+" DESC";
		
		try {
			return entityManager.createQuery(jpql, UploadTask.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
