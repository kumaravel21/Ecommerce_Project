package com.ecommerce.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserProfileDto {
	
	@NotNull(message= "first name is mandatory to fill")
	@NotBlank(message= "first name shouldn't be blank")
	private String first_name;
	
	private String last_name;
	
	private String phone_number;
	
	private String address_line1;
	
	private String address_line2; 
	
	private String city;
	
	private String state;
	
	private String zipcode;
	
	private String country;

}
