package com.cong.entity;

import lombok.Data;

@Data
public class TablePro {

	/**
	 * 表字段
	 */
	private String columnName;
	
	/**
	 * 表字段类型
	 */
	private String columnType;
	
	/**
	 * 表字段备注
	 */
	private String comment;
	
}
