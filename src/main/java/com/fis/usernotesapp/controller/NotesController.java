package com.fis.usernotesapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fis.usernotesapp.exception.UserNotesException;
import com.fis.usernotesapp.model.Notes;
import com.fis.usernotesapp.model.Users;
import com.fis.usernotesapp.service.NotesService;
import com.fis.usernotesapp.service.UserService;
import com.fis.usernotesapp.util.UserNotesUtil;

/**
 * Rest Controller to handle notes related Spring Rest Calls
 * 
 * @author Akhil Garg
 *
 */
@RestController
public class NotesController {

	Logger logger = LoggerFactory.getLogger(NotesController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private NotesService notesService;

	/**
	 * Method to get all notes assigned to a particular user
	 * 
	 * @param emailId
	 * @return List<Notes>
	 */

	@GetMapping("/users/{emailId}/notes")
	public List<Notes> getAllNotesByMailId(@PathVariable(value = "emailId") String emailId) {
		try {
			UserNotesUtil.validateLoggedInUserWithMailId(emailId);

			return userService.getUserDetailsByMailId(emailId).map(user -> {
				List<Notes> notesList = (List<Notes>) notesService.getNotesByUserId(user.getUserId());
				if (!notesList.isEmpty())
					return notesList;
				return notesList;
			}).orElseThrow(() -> new UserNotesException("Email Id " + emailId + " is not present in the database."));
		} catch (UserNotesException ex) {
			throw new UserNotesException(ex.getMessage());
		}
	}

	/**
	 * Method to create note for a particular user
	 * 
	 * @param emailId
	 * @param notes
	 * @return Note entry created in the database
	 */
	@PostMapping("/users/{emailId}/notes")
	public ResponseEntity<Notes> createNote(@PathVariable(value = "emailId") String emailId,
			@Valid @RequestBody Notes notes) {
		try {
			UserNotesUtil.validateLoggedInUserWithMailId(emailId);

			return userService.getUserDetailsByMailId(emailId).map(user -> {
				logger.info("user retreived:::" + user.getEmailId());
				logger.info("user retreived:::" + user);
				notes.setUsers(user);
				notesService.saveNotes(notes);
				return new ResponseEntity<Notes>(notes, HttpStatus.CREATED);
			}).orElseThrow(() -> new UserNotesException("Email Id " + emailId + " is not present in the database."));
		} catch (UserNotesException ex) {
			throw new UserNotesException(ex.getMessage());
		}

	}

	/**
	 * Update a note on the basis of noteId and emailId
	 * 
	 * @param emailId
	 * @param noteId
	 * @param noteRequest
	 * @return updated note object
	 */
	@PutMapping("/users/{emailId}/notes/{noteId}")
	public ResponseEntity<?> updateNotes(@PathVariable(value = "emailId") String emailId,
			@PathVariable(value = "noteId") Long noteId, @Valid @RequestBody Notes noteRequest) {
		try {
			UserNotesUtil.validateLoggedInUserWithMailId(emailId);
			if (!userService.getUserDetailsByMailId(emailId).isPresent()) {
				throw new UserNotesException("Email Id " + emailId + " is not present in the database.");
			}

			return notesService.getNoteByNoteId(noteId).map(notes -> {
				notes.setNote(noteRequest.getNote());
				notes.setTitle(noteRequest.getTitle());
				notes.setLastUpdatedTime(new Date());
				notesService.saveNotes(notes);
				return new ResponseEntity<Notes>(notes, new HttpHeaders(), HttpStatus.OK);
			}).orElseThrow(() -> new UserNotesException(
					"Note not found with id " + noteId + " and emailId " + emailId + " in the database."));
		} catch (UserNotesException ex) {
			throw new UserNotesException(ex.getMessage());
		}
	}

	/**
	 * Delete a note on the basis of noteId and userId
	 * 
	 * @param postId
	 * @param commentId
	 * @return
	 */
	@DeleteMapping("/users/{emailId}/notes/{noteId}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "emailId") String emailId,
			@PathVariable(value = "noteId") Long notesId) {
		try {
			UserNotesUtil.validateLoggedInUserWithMailId(emailId);

			Optional<Users> users = userService.getUserDetailsByMailId(emailId);
			if (!users.isPresent()) {
				throw new UserNotesException("Email Id " + emailId + " is not present in the database.");
			}

			return notesService.getNoteByNoteId(notesId).map(notes -> {
				notes.setLastUpdatedTime(new Date());
				notesService.deleteNote(notes);
				return ResponseEntity.ok().build();
			}).orElseThrow(() -> new UserNotesException(
					"Note not found with id " + notesId + " and emailId: " + emailId + " in the database."));
		} catch (UserNotesException ex) {
			throw new UserNotesException(ex.getMessage());
		}
	}
}