package net.shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import net.shop.dao.UploadTaskDao;
import net.shop.entity.UploadTask;
import net.shop.service.UploadTaskService;

@Service("uploadTaskServiceImpl")
public class UploadTaskServiceImpl  extends  BaseServiceImpl<UploadTask, Long> implements UploadTaskService {

	private final static String UNIT = "KB/S";
	
	@Resource(name = "uploadTaskDaoImpl")
	private UploadTaskDao uploadTaskDao;
	
	
	public UploadTask addUploadTask(UploadTask uploadTask){
		
		Assert.notNull(uploadTask);
		Assert.notNull(uploadTask.getHttpStart());
		Assert.notNull(uploadTask.getHttpEnd());
		Assert.notNull(uploadTask.getFileSize());
		
		String httpRate = calculateRate(uploadTask.getHttpStart(), uploadTask.getHttpEnd(), uploadTask.getFileSize());
		uploadTask.setHttpRate(httpRate);
		return this.save(uploadTask);
	}
    public UploadTask updateUploadTask(UploadTask uploadTask){
		
		Assert.notNull(uploadTask.getStorageStart());
		//Assert.notNull(uploadTask.getStorageEnd());
		if(uploadTask.getStorageEnd() != null) {
			
		  String storageRate = calculateRate(uploadTask.getStorageStart(),uploadTask.getStorageEnd(),uploadTask.getFileSize());
		  uploadTask.setStorageRate(storageRate);
		}
		
		return this.update(uploadTask);
		
	}
	
	private String calculateRate(Date startTime, Date endTime, long size){
		
		long sTime= startTime.getTime();
		long eTime= endTime.getTime();
		
		double httpRate= 0d;
		if( size > 0  &&  eTime > sTime ) {
			
			BigDecimal bdsTime = new BigDecimal(Long.toString(sTime));
			BigDecimal bdeTime = new BigDecimal(Long.toString(eTime));
			BigDecimal bdSize = new BigDecimal(Long.toString(size));
			
            BigDecimal httpRateBD = bdSize.divide(bdeTime.subtract(bdsTime),8, BigDecimal.ROUND_HALF_UP);
            httpRateBD = httpRateBD.multiply(new BigDecimal(Long.toString(1000000L))).divide(new BigDecimal(Long.toString(1024L)),8, BigDecimal.ROUND_HALF_UP);
			
			httpRate = httpRateBD.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
		}
		return httpRate + this.UNIT;
	}
	
	public List<UploadTask> findAll() {
		
		return uploadTaskDao.findAll(false);
	}
}
