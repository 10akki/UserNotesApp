package com.fis.uersnotesapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Notes entity class
 * 
 * @author Akhil Garg
 *
 */
@Entity
@Table(name = "notes")
public class Notes extends AuditModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * notesId
	 */
	@Id
	@Column(name = "notes_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notesId;

	/**
	 * title
	 */
	@NotNull(message = "Title cannot be null")
	@NotBlank(message = "Title cannot be blank")
	@Column(name = "title", nullable = false)
	@Size(max = 50, message = "Title length should not exceed 50 characters.")
	private String title;

	/**
	 * note
	 */
	@Column(name = "note", nullable = false)
	@NotNull(message = "Note cannot be null")
	@Lob
	@NotBlank(message = "Note cannot be blank")
	@Size(max = 1000, message = "Note length should not exceed 1000 characters")
	private String note;

	/**
	 * userId
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_Id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty("user_Id")
	@JsonIgnore
	private Users users;

	/**
	 * @return the notesId
	 */
	public Long getNotesId() {
		return notesId;
	}

	/**
	 * @param notesId the notesId to set
	 */
	public void setNotesId(Long notesId) {
		this.notesId = notesId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the users
	 */

	public Users getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

}