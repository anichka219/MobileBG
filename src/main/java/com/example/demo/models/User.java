package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name="users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	@Column(name = "password")
	private String password;
	@Column (nullable=false,name="crypt_password")
	@Transient
	private String cryptPassword;
	@Column(name = "role")
	private int role;
	@Column (name = "first_name")
	private String firstName;
	@Column (name = "last_name")
	private String lasstName;
	@Column (name = "profile_pic")
	private String profilePic;
	@ManyToOne
	private Region region;
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Advertisement> myAdvertisement= new ArrayList<Advertisement>();
	@ManyToMany(mappedBy="favourites",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Advertisement> myFavourites= new HashSet<Advertisement>();
	
	public User() {
		
	}
}
