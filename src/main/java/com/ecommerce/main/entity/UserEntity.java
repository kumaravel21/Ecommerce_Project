package com.ecommerce.main.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name="USER")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private long user_id;
	
	@Column(name="user_name", unique = true, nullable = false, length = 20)
	private String user_name;
	
	@Column(name="user_email", unique = true, nullable = false, length = 30)
	@Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @Size(max = 30)
	private String user_email;
	
	@Column(name="user_password", unique = true, nullable = false, length = 20)
	@NotNull(message = "password cannot be null")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	 @Pattern(
		        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#]).{8,}$",
		        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
		    )
	private String user_password;
	
	@Column(name="user_created")
	private LocalDateTime user_created;
	
	@Column(name="user_last_update")
	private LocalDateTime user_last_update;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserProfileEntity userProfile;
	
	@OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<OrderEntity> orderList = new ArrayList<OrderEntity>();
	
	@PrePersist
    protected void onCreate() {
		user_last_update = user_created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
    	user_last_update = LocalDateTime.now();
    }
}
