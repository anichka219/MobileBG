package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class User {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (unique=true)
	private String email;
	@Column (nullable=false)
	private String password;
	@Column (name = "first_name")
	private String firstName;
	@Column (name = "last_name")
	private String lasstName;
	@Column (name = "profile_pic")
	private String profilePic;
	@ManyToOne
	private Region region;
	@OneToMany(mappedBy = "user")
	private Set<Advertisement> myAdvertisement= new HashSet<Advertisement>();
	@ManyToMany(mappedBy="favourites")
	private Set<Advertisement> myFavourites= new HashSet<Advertisement>();
	
	
    private String password2;
	public boolean isAdministrator() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setAdministrator(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
