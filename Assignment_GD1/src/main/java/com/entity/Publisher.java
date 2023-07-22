package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publisher {
	@Id
	String pulishername;
	String address;
	
	@OneToMany(mappedBy = "publisher")
	List<Book> book;
}
