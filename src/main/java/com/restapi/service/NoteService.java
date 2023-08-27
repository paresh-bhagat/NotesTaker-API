package com.restapi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restapi.entity.Note;
import com.restapi.entity.User;
import com.restapi.repository.NoteRepository;
import com.restapi.repository.UserRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private UserRepository userrepo;
	
	public Note getNote(int id, String username) {
        return this.noteRepository.findByIdAndUserUsername(id, username).orElse(null);
    }
	
	public Note addNote(String name,Note note) {
		
		User user = this.userrepo.getUserByUserName(name);
		note.setUser(user);
		Date date = new Date();
		note.setDate(date);
		return this.noteRepository.save(note);
	}
	
	public Boolean deleteNote(int id , String name) {
		
		Note temp = getNote(id,name);
		
		if(temp==null)
			return false;
		
		this.noteRepository.deleteById(id);
		
		return true;
	}

	public Note updateNote(int id, String name, Note note) {
		
		Note temp = getNote(id,name);
		
		if(temp==null)
			return null;
		
		temp.setTitle(note.getTitle());
		temp.setContent(note.getContent());
		Date date = new Date();
		temp.setDate(date);
		
		return this.noteRepository.save(temp);
	}
}
