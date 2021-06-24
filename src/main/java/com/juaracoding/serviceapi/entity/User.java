package com.juaracoding.serviceapi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 20)	
	private String username;

	@Email
	@Size(max = 50)
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

    private String name;
	private String addressOne;
	private String addressTwo;
	private int provincesId;
	private int regenciesId;
	private int zipCode;
	private String country;
	private String phoneNumber;
	private String birthday;
	private String gender;
	
	public User(String name, String username, String email, String password, 
			String addressOne, String addressTwo, int provincesId, int regenciesId, int zipCode,
			String country, String phoneNumber, String birthday, String gender) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.provincesId = provincesId;
		this.regenciesId = regenciesId;
		this.zipCode = zipCode;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.gender = gender;
	}
		public User(String name, String username, String email, String password) {
			this.name = name;
			this.username = username;
			this.email = email;
			this.password = password;
		}
		
		@JsonIgnore
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(	name = "user_roles", 
					joinColumns = @JoinColumn(name = "user_id"), 
					inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<Role> role = new HashSet<>();

		@JsonIgnore
		@OneToOne(mappedBy = "users")
	    private Doctors doctors;
		
		@JsonIgnore
		@OneToMany(mappedBy = "users")
	    private Set<Transactions> transactions = new HashSet<>();
		
}
