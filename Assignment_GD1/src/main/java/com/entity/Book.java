package com.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	@Id
	String bookname;
	String author;
	String publisher;
	Double price;
	Date releasedate;
	Boolean available;
	String image;
	String pdf;
	@JsonIgnore
	@OneToMany(mappedBy = "book")
	List<Retail> retail;
	
	
}
