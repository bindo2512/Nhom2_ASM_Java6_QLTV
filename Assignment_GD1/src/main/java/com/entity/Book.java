package com.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@ManyToOne
	@JoinColumn(name = "publishername")
	Publisher publisher;
	Double price;
	Date releasedate;
	Boolean available;
	String image;
	String pdf;
	
	@OneToMany(mappedBy = "book")
	List<Retail> retail;
	
	
	@Override
	public String toString() {
		return "Book{" +
				"Book name=" + bookname +
				", author='" + author + '\'' +
				", price=" + price +
				", release date=" + releasedate +
				", available=" + available +
				", image=" + image +
				", pdf='" + pdf + '\'' +
				'}';
	}
}
