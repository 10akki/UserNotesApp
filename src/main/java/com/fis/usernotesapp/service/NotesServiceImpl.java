package com.fis.usernotesapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fis.usernotesapp.dao.NotesDao;
import com.fis.usernotesapp.exception.UserNotesException;
import com.fis.usernotesapp.model.Notes;

/**
 * Service Implementation class
 * @author Akhil Garg
 *
 */
@Service
@Transactional
public class NotesServiceImpl implements NotesService{

	@Autowired
	NotesDao notesDao;
	
	
	@Override
	public List<Notes> getNotesByUserId(Long userId) throws UserNotesException{
		return notesDao.getNotesByUserId(userId);
	}

	@Override
	public void saveNotes(Notes notes) throws UserNotesException{
		notesDao.saveUpdateNotes(notes);
	}

	@Override
	public Optional<Notes> getNoteByNoteId(Long noteId) throws UserNotesException{
		return notesDao.getNoteByNoteId(noteId);
	}

	@Override
	public void deleteNote(Notes notes) throws UserNotesException{
		notesDao.deleteNote(notes);
	}
}