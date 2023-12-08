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
public class accounts {
	@Id
	String username;
	String password;
	Boolean isactive;
	Boolean isadmin;
	Date createdate;
	java.util.Date lastlogin;
	Boolean currentlylogin;
	@ManyToOne
    @JoinColumn(name = "accountdetailid")
    accountdetail accountdetail;
	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	List<retails> retail;
	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	List<comments> comments;
	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	List<history> history;
	String verification;
}
