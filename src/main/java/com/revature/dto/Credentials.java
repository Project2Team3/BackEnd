package com.revature.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
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
