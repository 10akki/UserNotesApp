package com.fis.usernotesapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fis.usernotesapp.validation.UniqueEmail;

/**
 * Users Entity class
 * @author Akhil Garg
 *
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class Users extends AuditModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * userId
	 */
	@Id
	@Column(name="user_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
 
	/**
	 * emailId
	 */
	@NotBlank(message = "Email Id cannot be blank")
	@UniqueEmail
	@Email(message = "Kindly enter valid email Id")
	@Column(name="username",nullable = false)
	private String emailId; 
 
	/**
	 * emailId
	 */
	@Column(name="password",nullable = false)
	@Size(min = 8)
	private String password;
	
	/**
	 * enabled
	 */
	@Column(name="enabled",nullable = false)
	private int enabled;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private List<Notes> notes;
	
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
}
