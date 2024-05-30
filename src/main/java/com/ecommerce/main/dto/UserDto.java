package com.ecommerce.main.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	@NotNull(message = "username must be filled")
	@NotBlank(message = "username must not be blank")
	private String user_name;
	
	@Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @Size(max = 30)
	private String user_email;
	
	@NotNull(message = "password cannot be null")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	 @Pattern(
		        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#]).{8,}$",
		        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
		    )
	private String user_password;
	
	private UserProfileDto userProfile;
}
