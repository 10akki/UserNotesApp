package com.fis.usernotesapp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fis.usernotesapp.exception.UserNotesException;
import com.fis.usernotesapp.model.Notes;

/**
 * Dao class to handle CRUD operations on Notes entity
 * 
 * @author Akhil Garg
 *
 */
@Repository
public class NotesDaoImpl implements NotesDao {

	/**
	 * sessionFactory
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Method to get Session object
	 * 
	 * @return session object
	 */
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notes> getNotesByUserId(Long userId) throws UserNotesException{
		Query query = getSession().createQuery("from Notes where users.userId=:userId");
		query.setParameter("userId", userId);
		List<Notes> notesList = query.getResultList();
		return notesList;
	}

	@Override
	public void saveUpdateNotes(Notes notes) throws UserNotesException{
		getSession().merge(notes.getUsers());
		notes.setCreatedTime(new Date());
		getSession().merge(notes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Notes> getNoteByNoteId(Long noteId) throws UserNotesException{
		Query query = getSession().createQuery("select notes from Notes notes where note.notesId= :noteId");
		query.setParameter("noteId", noteId);
		List<Notes> notesList = query.getResultList();
		List<Notes> matchedNote =new ArrayList<>();
		if (notesList!=null && !notesList.isEmpty()) {
			matchedNote = notesList.stream().filter(note -> note.getNotesId().intValue() == noteId)
					.collect(Collectors.toList());
			if(!matchedNote.isEmpty()) {
				Optional<Notes> optionalNote = Optional.of(matchedNote.get(0));
				return optionalNote.isPresent() ? Optional.of(optionalNote.get()) : Optional.empty();
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteNote(Notes notes) {
			getSession().delete(notes);
	}
}