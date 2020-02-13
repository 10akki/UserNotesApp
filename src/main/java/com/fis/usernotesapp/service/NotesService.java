package com.fis.usernotesapp.service;

import java.util.List;
import java.util.Optional;

import com.fis.usernotesapp.exception.UserNotesException;
import com.fis.usernotesapp.model.Notes;

/**
 * Service class to implement business logic related to notes entity
 * @author Akhil Garg
 *
 */
public interface NotesService {

	/**
	 * Method to get notes on the basis of userId
	 * @param userId
	 * @return List<Notes>
	 * @throws UserNotesException
	 */
	List<Notes> getNotesByUserId(Long userId) throws UserNotesException;

	/**
	 * Method to save newly entered notes
	 * @param notes
	 * @throws UserNotesException
	 */
	void saveNotes(Notes notes) throws UserNotesException;

	/**
	 * Method to get single note on the basis of noteId
	 * @param noteId
	 * @return Optional<Notes>
	 * @throws UserNotesException
	 */
	Optional<Notes> getNoteByNoteId(Long noteId) throws UserNotesException;

	/**
	 * Method to delete note from the database
	 * @param notes
	 * @throws UserNotesException
	 */
	void deleteNote(Notes notes) throws UserNotesException;
	
}
