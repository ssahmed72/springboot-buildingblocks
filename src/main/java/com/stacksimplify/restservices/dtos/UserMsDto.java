package com.stacksimplify.restservices.dtos;

public class UserMsDto {
	
	private Long id;
	private String username;
	private String emailaddress;
	private String rolename;
	
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public UserMsDto(Long id, String username, String emailaddress, String rolename) {
		super();
		this.id = id;
		this.username = username;
		this.emailaddress = emailaddress;
		this.rolename = rolename;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
	public UserMsDto() {

	}

}
