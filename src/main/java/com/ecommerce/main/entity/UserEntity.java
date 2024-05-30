package com.ecommerce.main.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
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
@Table(name="USERS")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private long user_id;
	
	@Column(name="user_name", unique = true, nullable = false)
	private String user_name;
	
	@Column(name="user_email", unique = true, nullable = false)
	private String user_email;
	
	@Column(name="user_password", unique = true, nullable = false)
	private String user_password;
	
	@Column(name="user_created")
	private LocalDateTime user_created;
	
	@Column(name="user_last_update")
	private LocalDateTime user_last_update;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserProfileEntity userProfile;
	
	@OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<OrderEntity> orderList = new HashSet<OrderEntity>();
	
	@PrePersist
    protected void onCreate() {
		user_last_update = user_created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	user_last_update = LocalDateTime.now();
    }
}
