package com.cong.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cong.dao.BaseDao;
import com.cong.entity.Table;
import com.cong.entity.TablePro;
import com.cong.util.CommonUtil;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询某数据库里面所有的表名
	 * @param databaseName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> selectTableName(String databaseName) {
		List<String> list = new ArrayList<String>();
		String sql = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE table_schema= '"+databaseName+"'";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : rows) {
			list.add(String.valueOf(map.get("TABLE_NAME")));
		}
		return list;
	}

	/**
	 * 查询某数据库下某张表的信息
	 * @param databaseName
	 * @param tableName
	 * @return
	 */
	public Table selectColumnAndType(String databaseName, String tableName) {
		Table table = new Table();
		table.setTableName(CommonUtil.toHumpName(tableName,0));
		String sql = "SELECT COLUMN_NAME,DATA_TYPE ,COLUMN_COMMENT FROM information_schema.columns "
				+ "WHERE table_schema= '"+databaseName+"' AND table_name = '"+tableName+"' ";
		
		List<TablePro> list = new ArrayList<TablePro>();
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : rows) {
			TablePro tablePro = new TablePro();	
			String columnType = String.valueOf(map.get("DATA_TYPE"));//字段类型
			String columnName = String.valueOf(map.get("COLUMN_NAME"));//字段名
			String columnComment = String.valueOf(map.get("COLUMN_COMMENT"));//字段备注
			if("varchar".equals(columnType) || "char".equals(columnType)
					|| "tinyint".equals(columnType) || "text".equals(columnType)){
				tablePro.setColumnType("String");
			}else if("bigint".equals(columnType)){
				tablePro.setColumnType("Long");
			}else if("datetime".equals(columnType)){
				tablePro.setColumnType("Date");
			}else if("decimal".equals(columnType)){
				tablePro.setColumnType("double");
			}else if("bit".equals(columnType)){
				tablePro.setColumnType("boolean");
			}else if("timestamp".equals(columnType)){
				tablePro.setColumnType("Timestamp");
			}else{
				tablePro.setColumnType(columnType);
			}
			tablePro.setColumnName(CommonUtil.toHumpName(columnName,1));
			if(columnComment == null){
				tablePro.setComment(null);
			}else{
				tablePro.setComment(columnComment);
			}
			list.add(tablePro);
		}
		table.setList(list);
		return table;
	}

}
