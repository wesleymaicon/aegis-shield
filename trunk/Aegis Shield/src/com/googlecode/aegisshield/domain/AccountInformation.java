/*
 * Nov 23, 2008
 * Copyright (C) 2007 Mihai Campean.
 */
package com.googlecode.aegisshield.domain;

/**
 * 	Domain object holding the information about the user accounts.
 * 
 * @author Mihai Campean
 */
public class AccountInformation {
	/**
	 * 	The technical account id for this account information object.
	 */
	private int id;
	
	/**
	 * 	The user name for this account information.
	 */
	private String userName;
	
	/**
	 * 	The password of the account entered as account information.
	 */
	private String password;
	
	/**
	 * 	The description of this account information.
	 */
	private String description;
	
	/**
	 * 	The name of the account for which the password information is saved.
	 */
	private String accountName;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
