package com.his.dto;

import java.util.List;

public class KidsDTO {
	
	private Integer appNumber;
	private List<KidDTO> kids;
	
	public Integer getAppNumber() {
		return appNumber;
	}
	public void setAppNumber(Integer appNumber) {
		this.appNumber = appNumber;
	}
	public List<KidDTO> getKids() {
		return kids;
	}
	public void setKids(List<KidDTO> kids) {
		this.kids = kids;
	}
	
	

}
