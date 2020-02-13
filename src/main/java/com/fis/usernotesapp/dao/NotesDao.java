package com.fis.usernotesapp.dao;

import java.util.List;
import java.util.Optional;

import com.fis.usernotesapp.exception.UserNotesException;
import com.fis.usernotesapp.model.Notes;

public interface NotesDao {

	/**
	 * Method to get noted list by userId
	 * 
	 * @return List<Notes>
	 */
	List<Notes> getNotesByUserId(Long userId) throws UserNotesException;

	/**
	 * Method to save new notes/update exsiting notes
	 *
	 */
	void saveUpdateNotes(Notes notes) throws UserNotesException;

	/**
	 * Method to get note by noteId @ Optional<Notes> note object
	 */
	Optional<Notes> getNoteByNoteId(Long noteId) throws UserNotesException;

	/**
	 * Method to delete a note
	 * 
	 * @param notes
	 * 
	 */
	void deleteNote(Notes notes) throws UserNotesException;

}
