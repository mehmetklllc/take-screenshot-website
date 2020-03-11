package com.fourdsight.service.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "creation_user")
	private String creationUser;

	@CreatedDate
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Column(name = "last_update_user")
	private String lastUpdateUser;

	@LastModifiedDate
	@Column(name = "last_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateTime;
	
	@Column(name = "deleted")
	private boolean deleted;

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void persistListen(String user){
		
		Calendar cal = Calendar.getInstance(); 
		Date current = cal.getTime();
		setCreationUser(user);
		setCreationTime(current);
		setLastUpdateUser(user);
		setLastUpdateTime(current);
	}
	
	public void updateListen(String user) {
		
		Calendar cal = Calendar.getInstance(); 
		Date current = cal.getTime();
		setLastUpdateUser(user);
		setLastUpdateTime(current);
	}

}