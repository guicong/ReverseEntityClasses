package com.cong.entity;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 * @description TODO
 * @author guicong
 * @veriosn 1.0
 * @date 2017-11-7
 */
@Data
public class SysUser {

	/**
	 * 主键
	 */
	 private int id;
	/**
	 * 账号
	 */
	 private String usercode;
	/**
	 * 姓名
	 */
	 private String username;
	/**
	 * 密码
	 */
	 private String password;
	/**
	 * 盐
	 */
	 private String salt;
	/**
	 * 账号是否锁定，1：锁定，0未锁定
	 */
	 private String locked;
	
}