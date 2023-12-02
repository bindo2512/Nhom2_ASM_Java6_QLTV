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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class retails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer retailid;
	@NotNull (message = "Bạn chưa chọn ngày mượn")
	Date retaildate;
	@NotNull
	Date returndate;
	@NotNull (message = "Bạn chưa nhập địa chỉ")
	String address;
	@NotNull (message = "Bạn chưa nhập họ tên")
	String fullname;
	@NotNull (message = "Bạn chưa nhập email")
	@Email (message = "Định dạng nhập phải là email")
	String email;
	@NotNull
	String phonenumber;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "username")
	accounts accounts;
	@JsonIgnore
	@OneToMany(mappedBy = "retails")
	List<details> details;
	@ManyToOne
	@JoinColumn(name = "orderstateid")
	orderstate orderstate;
	String verification;
	Boolean isverify;
}
