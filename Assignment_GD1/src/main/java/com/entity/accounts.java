package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class accounts {
	@Id
	String username;
	String password;
	Boolean isactive;
	String isadmin;
	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	List<accountdetails> accountdetail;
	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	List<retails> retail;
	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	List<comments> comments;
}
