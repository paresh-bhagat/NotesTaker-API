package com.restapi.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
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
	@Size(min=1,max=70,message="Title between 1 to 70 characters")
	private String title;
	
	@Column(length=7500,name="note_content")
	@Size(min=1,max=7500,message="Content between 1 to 7500 characters")
	private String content;
	
	@Column(name="note_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;

}
