package com.coop8.demojwt.Request;

import java.io.Serializable;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest implements Serializable {

	private static final long serialVersionUID = -562067409640377017L;

	@NotBlank
	
	private String password;

	@NotBlank
	
	private String newPassword;

	@NotBlank
	
	private String repeatNewPassword;
}
