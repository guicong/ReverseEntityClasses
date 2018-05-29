package com.cong.util;

public class CommonUtil {
	
	/**
	 * 将获取的表名转换为驼峰命名
	 * 				如:complain_handler转换为ComplainHandler
	 * @param tableOrColumnName 表名或者字段名
	 * @param type 0:表示表名;1:表示字段名
	 * @return
	 */
	public static String toHumpName(String tableOrColumnName,int type) {
		if(type == 0) {//表名首字母大写
			tableOrColumnName = tableOrColumnName.substring(0, 1).toUpperCase() + tableOrColumnName.substring(1);//首字母大写
		}
		for(int i = 0;i <= tableOrColumnName.lastIndexOf("_");i++) {
			int index = tableOrColumnName.indexOf("_",i);
			System.out.println(index);
			if(index > 0) {//存在则去掉下划线并将下划线后面一位转为大写
				System.out.println(tableOrColumnName);
				tableOrColumnName = tableOrColumnName.substring(0,index) + tableOrColumnName.substring(index + 1,index + 2).toUpperCase() + tableOrColumnName.substring(index + 2);
			}
		}
		return tableOrColumnName;
	}
	
	public static void main(String[] args) {
		System.out.println(toHumpName("com_co_sad",1));
	}
	

}
