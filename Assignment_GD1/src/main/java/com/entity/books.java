package com.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer bookid;
	@ManyToOne
	@JoinColumn(name = "booknameid")
	bname bname;
	@ManyToOne
	@JoinColumn(name = "authorid")
	authors authors;
	@ManyToOne
	@JoinColumn(name = "issuerid")
	issuer issuer;
	@ManyToOne
	@JoinColumn(name = "publisherid")
	publishers publishers;
	String description;
	Integer yearpub;
	Boolean available;
	Boolean hardcover;
	String image;
	String pdf;
	@JsonIgnore
	@OneToMany(mappedBy = "books")
	List<details> retaildetail;
	@JsonIgnore
	@OneToMany(mappedBy = "books")
	List<comments> comments;
	@ManyToOne
	@JoinColumn(name = "categoryid")
	categories categories;
	
}
