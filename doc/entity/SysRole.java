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
public class SysRole {

	/**
	 * 
	 */
	 private int id;
	/**
	 * 
	 */
	 private String name;
	/**
	 * 是否可用,1：可用，0不可用
	 */
	 private String available;
	
}