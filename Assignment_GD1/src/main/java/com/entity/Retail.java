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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Retail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer retailid;
	Date retaildate;
	Date returndate;
	String address;
	String name;
	String email;
	String phonenumber;
	Boolean active;
	@ManyToOne
	@JoinColumn(name = "username")
	Account account;
	@JsonIgnore
	@OneToMany(mappedBy = "retail")
	List<Detail> rdetail;
}
