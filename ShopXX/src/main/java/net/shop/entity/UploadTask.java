package net.shop.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "xx_uploadtask")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_uploadtask")
public class UploadTask extends BaseEntity<Long>{
	
	private static final long serialVersionUID = -4050178130548739301L;
	
	/** 上传文件原始名称 */
	private String fileName;
	
	/** 上传文件大小 */
	private long fileSize;

	/** 上传文件源路径 */
	private String sourcePath;

	/** 上传文件目的路径  */
	private String destPath;

	/** 文件上传状态：0表示上传中，1表示结束 */
	private int status;

	/** 文件上传HTTP阶段开始时间 */
	private Date  httpStart;

	/** 文件上传HTTP阶段结束时间 */
	private Date httpEnd;
	
	/** 文件上传storage开始时间 */
	private Date  storageStart;
	
	/** 文件上传storage结束时间 */
	private Date  storageEnd;
	
	/** 文件上传HTTP阶段上传速度 */
	private String httpRate;
	
	/** 文件上传后续阶段存储名称：本地存储/FTP/阿里云*/
	private String storageType;
	
	/** 文件上传后续阶段本地存储/FTP/阿里云上传速度 */
	private String storageRate;
	
	/** 文件上传整体速度 */
	private String rate;
	
	/** 上传文件预览地址 */
	private String viewLink;
	
	/** 保留他用 */
	private String others;

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHttpRate() {
		return httpRate;
	}

	public void setHttpRate(String httpRate) {
		this.httpRate = httpRate;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getStorageRate() {
		return storageRate;
	}

	public void setStorageRate(String storageRate) {
		this.storageRate = storageRate;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getViewLink() {
		return viewLink;
	}

	public void setViewLink(String viewLink) {
		this.viewLink = viewLink;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getStorageStart() {
		return this.storageStart;
	}

	public void setStorageStart(Date storStart) {
		this.storageStart = storStart;
	}

	public Date getStorageEnd() {
		return this.storageEnd;
	}

	public void setStorageEnd(Date storEnd) {
		this.storageEnd = storEnd;
	}

	public Date getHttpEnd() {
		return httpEnd;
	}

	public void setHttpEnd(Date httpEnd) {
		this.httpEnd = httpEnd;
	}

	public Date getHttpStart() {
		return this.httpStart;
	}

	public void setHttpStart(Date httpStart) {
		this.httpStart = httpStart;
	}

}
