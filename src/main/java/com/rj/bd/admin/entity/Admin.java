package com.rj.bd.admin.entity;
/**
 * @desc Admin
 */
import lombok.Data;

@Data
public class Admin {
	public String adminId;
	public String username;
	public String password;
	public String token;
}
