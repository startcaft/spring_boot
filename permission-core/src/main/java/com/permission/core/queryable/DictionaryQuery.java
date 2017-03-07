package com.permission.core.queryable;

public class DictionaryQuery extends PageRequest {
	
	private Integer dicTypeId;
	private String dicName;

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public Integer getDicTypeId() {
		return dicTypeId;
	}

	public void setDicTypeId(Integer dicTypeId) {
		this.dicTypeId = dicTypeId;
	}
	
}
