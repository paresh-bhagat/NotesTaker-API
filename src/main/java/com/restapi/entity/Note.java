package com.restapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Note")
public class Note {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="note_id")
	private int id;
	
	@Column(length=70,name="note_title")
	private String title;
	
	@Column(length=7500,name="note_content")
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;

}
