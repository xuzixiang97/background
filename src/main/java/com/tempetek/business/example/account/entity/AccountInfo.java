package com.tempetek.business.example.account.entity;

import com.tempetek.core.base.LongId;

public class AccountInfo extends LongId {

	private static final long serialVersionUID = 1L;
	// 账号名称
	private String accountName;
	// 账户密码
	private String accountPwd;
	// 账户锁定
	private Long locked;
	// 所属人员
	private Long personid;
	// 账户类型
	private String type;
	// 账户锁定(名称)
	private String lockedStr;
	// 账户类型(名称)
	private String typeStr;
	// 所属人员(名称)
	private String personStr;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public Long getLocked() {
		return locked;
	}

	public void setLocked(Long locked) {
		this.locked = locked;
	}

	public Long getPersonid() {
		return personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLockedStr() {
		return lockedStr;
	}

	public void setLockedStr(String lockedStr) {
		this.lockedStr = lockedStr;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getPersonStr() {
		return personStr;
	}

	public void setPersonStr(String personStr) {
		this.personStr = personStr;
	}

	@Override
	public String toString() {
		return "AccountInfo{" +
				"accountName='" + accountName + '\'' +
				", accountPwd='" + accountPwd + '\'' +
				", locked=" + locked +
				", personid=" + personid +
				", type='" + type + '\'' +
				", lockedStr='" + lockedStr + '\'' +
				", typeStr='" + typeStr + '\'' +
				", personStr='" + personStr + '\'' +
				'}';
	}
}