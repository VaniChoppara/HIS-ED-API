package com.his.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="HIS_ELIG_DETERMINE")
public class EligDetermine{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eligId;
	private String planName;
	private String eligStatus;
	private LocalDate eligStartdate;
	private LocalDate eligEndDate;
	private Integer benefitAmount;
	private String denialReason;
	private LocalDate eligiDeterDate;
	private Integer appNumber;
	public Integer getEligId() {
		return eligId;
	}
	public void setEligId(Integer eligId) {
		this.eligId = eligId;
	}
	public String getEligStatus() {
		return eligStatus;
	}
	public void setEligStatus(String eligStatus) {
		this.eligStatus = eligStatus;
	}
	public LocalDate getEligStartdate() {
		return eligStartdate;
	}
	public void setEligStartdate(LocalDate eligStartdate) {
		this.eligStartdate = eligStartdate;
	}
	public LocalDate getEligEndDate() {
		return eligEndDate;
	}
	public void setEligEndDate(LocalDate eligEndDate) {
		this.eligEndDate = eligEndDate;
	}
	public Integer getBenefitAmount() {
		return benefitAmount;
	}
	public void setBenefitAmount(Integer benefitAmount) {
		this.benefitAmount = benefitAmount;
	}
	public String getDenialReason() {
		return denialReason;
	}
	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}
	public LocalDate getEligiDeterDate() {
		return eligiDeterDate;
	}
	public void setEligiDeterDate(LocalDate localDate) {
		this.eligiDeterDate = localDate;
	}
	public Integer getAppNumber() {
		return appNumber;
	}
	public void setAppNumber(Integer appNumber) {
		this.appNumber = appNumber;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	

}
