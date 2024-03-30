package com.his.dto;

import java.time.LocalDate;

public class KidDTO {
	private Integer kidId;
	private String kidName;	
	private LocalDate dob;
	private Long kidSsn;	 
	private Integer appNumber;
	public Integer getKidId() {
		return kidId;
	}
	public void setKidId(Integer kidId) {
		this.kidId = kidId;
	}
	public String getKidName() {
		return kidName;
	}
	public void setKidName(String kidName) {
		this.kidName = kidName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Long getKidSsn() {
		return kidSsn;
	}
	public void setKidSsn(Long kidSsn) {
		this.kidSsn = kidSsn;
	}
	public Integer getAppNumber() {
		return appNumber;
	}
	public void setAppNumber(Integer appNumber) {
		this.appNumber = appNumber;
	}
	

}
