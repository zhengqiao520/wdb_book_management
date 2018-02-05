package com.wdb007.baseservice.model;

import java.util.List;
public class ResolveBookResult {
	
	public List<String> successList;
	public List<String> dbExistsList;
	public List<String> failList;
	public List<String> getSuccessList() {
		return successList;
	}

	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}

	public List<String> getDbExistsList() {
		return dbExistsList;
	}

	public void setDbExistsList(List<String> dbExistsList) {
		this.dbExistsList = dbExistsList;
	}

	public List<String> getFailList() {
		return failList;
	}

	public void setFailList(List<String> failList) {
		this.failList = failList;
	}
}
