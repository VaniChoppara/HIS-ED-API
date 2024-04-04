package com.his.exception;

import java.util.Date;

public class EdApiError {

	String errCode;
	String errDesc;
	Date date;

	public EdApiError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EdApiError(String errCode, String errDesc, Date date) {
		super();
		this.errCode = errCode;
		this.errDesc = errDesc;
		this.date = date;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ApiError [errCode=" + errCode + ", errDesc=" + errDesc + ", date=" + date + "]";
	}
}
