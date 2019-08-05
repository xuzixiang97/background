package com.tempetek.business.systemparam.enumerate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DataType {
	
	MENU("CS_ACCOUNT"),
	USER("TP_USER"),
	ROLE("TP_ROLE"),
	TPMENU("TP_MENU"),
	USERANDROLE("TP_USER_ROLE"),
	ROLEANDMENU("TP_ROLE_MENU"),
	PEOPLE("TP_PERSON");

	private String name;

	public String getName() {
		return name;
	}

	private DataType(String name) {
		this.name = name;
	}

	/**
	 * 获取所有的枚举集合
	 * @return
	 */
	public static List<DataType> getValues() {
		return new ArrayList<DataType>(Arrays.asList(DataType.values()));
	}

}