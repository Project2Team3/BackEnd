package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

	@NotNull
	@NotBlank
	@Length(min=2)
	private String username;
	
	@NotNull
	@NotBlank
	private String password;

}