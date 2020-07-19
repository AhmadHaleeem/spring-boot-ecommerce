package com.haleem.ecommerce.models;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long SerialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 2, message = "Username must be a least 2 characters long")
	private String username;

	@Size(min = 4, message = "Password must be a least 4 characters long")
	private String password;

	@Transient // It's not gonna be persist
	private String confirmPassword;

	@Email(message = "Please enter a valid email")
	private String email;

	@Column(name = "phone_number")
	@Size(min = 6, message = "Phone number must be a least 6 characters long")
	private String phoneNumber;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public User(@Size(min = 2, message = "Username must be a least 2 characters long") String username,
			@Size(min = 4, message = "Password must be a least 4 characters long") String password,
			String confirmPassword, @Email(message = "Please enter a valid email") String email,
			@Size(min = 6, message = "Phone number must be a least 6 characters long") String phoneNumber) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
