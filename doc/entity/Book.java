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
public class Book {

	/**
	 * 图书ID
	 */
	 private Long bookId;
	/**
	 * 图书名称
	 */
	 private String name;
	/**
	 * 馆藏数量
	 */
	 private int number;
	
}