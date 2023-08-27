package com.restapi.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.entity.Note;
import com.restapi.entity.User;
import com.restapi.service.NoteService;
import com.restapi.service.UserService;

@RestController
@RequestMapping("/notesapi/user")
public class NoteController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private NoteService noteservice;
	
	@GetMapping("/test")
	public Note testing() {
		System.out.print("testing");
		Note temp = new Note();
		temp.setTitle("test title");
		temp.setContent("test content");
		return temp;
	}
	
	// get note handler
	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getNote(@PathVariable("id") int id, Principal principal) {
		System.out.print("get note");
		Note note = new Note();
		
		try {
			String name = principal.getName();
			note = this.noteservice.getNote(id, name);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		if(note==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.status(HttpStatus.OK).body(note);
	}
	
	// get all notes handler
	@GetMapping("/notes")
	public ResponseEntity<List<Note>> getAllNotes(Principal principal) {
		System.out.print("get note");
		User user = new User();
		
		try {
			String name = principal.getName();
			user = this.userservice.getUserDetails(name);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		if(user.getNotes()==null || user.getNotes().isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		return ResponseEntity.status(HttpStatus.OK).body(user.getNotes());
	}
	
	// add note handler
	@PostMapping("/notes")
	public ResponseEntity<Note> addNote(Principal principal, @RequestBody Note newNote) {
		
		Note note = new Note();
		
		try {
			String name = principal.getName();
			note = this.noteservice.addNote(name, newNote);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		if(note==null)
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		
	    return ResponseEntity.status(HttpStatus.CREATED).body(note);
	}
	
	// delete note handler
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Void> deleteNote(@PathVariable("id") int id, Principal principal) {
		System.out.print("get note");
		
		try {
			String name = principal.getName();
			if(this.noteservice.deleteNote(id, name)==false)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// update note handler
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable("id") int id, @RequestBody Note newNote, Principal principal) {
		System.out.print("get note");
		
		Note note = new Note();
		
		try {
			String name = principal.getName();
			note = this.noteservice.updateNote(id, name, newNote);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		if(note==null)
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		
		return ResponseEntity.status(HttpStatus.OK).body(note);
		
	}

}
