package com.wdb007.baseservice.model;

public enum GatherSourceTypeEnum {
	
	//JD(1, "京东网"), 
	DANGDANG(2, "当当网"),
    WENXUAN(1,"文轩网");
	private Integer sourceCode;
	private String sourceName;

	private GatherSourceTypeEnum(int sourceCode, String sourceName) {
		this.sourceCode = sourceCode;
		this.sourceName = sourceName;
	}

	public Integer getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(Integer sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
}
