package com.cong.dao;

import java.util.List;

import com.cong.entity.Table;

public interface BaseDao {
	
	/**
	 * 查询某数据库里面所有的表名
	 * @param databaseName
	 * @return
	 */
	public List<String> selectTableName(String databaseName);

	/**
	 * 查询某数据库下某张表的信息
	 * @param databaseName
	 * @param tableName
	 * @return
	 */
	public Table selectColumnAndType(String databaseName, String tableName);

}
