package com.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account_Details {
	@Id
	String name;
	String email;
	String address;
	String phonenumber;
	Date dob;
	@ManyToOne
	@JoinColumn(name = "username")
	Account account;
}
