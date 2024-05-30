package com.ecommerce.main.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name="profile")
public class UserProfileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="profile_id")
	private long profile_id;
	
	@Column(name="user_id")
	private long user_id;
	
	@Column(name="first_name", nullable = false)
	private String first_name;
	
	@Column(name="last_name", nullable = false)
	private String last_name;
	
	@Column(name="phone_number",unique = true , nullable = false, length = 10 )
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 characters long")
	private String phone_number;
	
	@Column(name="address_line1",  nullable = false)
	private String address_line1;
	
	@Column(name="address_line2",nullable = true)
	private String address_line2; 
	
	@Column(name="city", nullable = false)
	private String city;
	
	@Column(name="state", nullable = false)
	private String state;
	
	@Column(name="zipcode",  nullable = false)
	private String zipcode;
	
	@Column(name="country", nullable = false)
	private String country;
	
	@Column(name="profile_created")
	private LocalDateTime profile_created;
	
	@Column(name="profile_last_update")
	private LocalDateTime profile_last_update;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private UserEntity user;
	
	@PrePersist
    protected void onCreate() {
		profile_last_update = profile_created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	profile_last_update = LocalDateTime.now();
    }
 
   
}
