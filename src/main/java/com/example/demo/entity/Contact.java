package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String surname1;
	private String surname2;
	private Integer phone;
	private String email;

	public Contact() {
	}
	
	public Contact(String name, String surname1, String surname2, Integer phone, String email) {
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.phone = phone;
		this.email = email;
	}

	public Contact(Integer id, String name, String surname1, String surname2, Integer phone, String email) {
		this(name, surname1, surname2, phone, email);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}