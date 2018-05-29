package com.cong.entity;

import java.util.List;

import lombok.Data;

@Data
public class Table {

	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 表属性
	 */
	private List<TablePro> list;
	
}
