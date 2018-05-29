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
public class Appointment {

	/**
	 * 图书ID
	 */
	 private Long bookId;
	/**
	 * 学号
	 */
	 private Long studentId;
	/**
	 * 预约时间
	 */
	 private Timestamp appointTime;
	
}