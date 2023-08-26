package com.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.entity.Note;

@RestController
@RequestMapping("/notesapi")
public class NoteController {
	
	@GetMapping("/notes")
	public Note getNotes() {
		System.out.print("testing");
		Note temp = new Note();
		temp.setTitle("test title");
		temp.setContent("test content");
		return temp;
	}

}
