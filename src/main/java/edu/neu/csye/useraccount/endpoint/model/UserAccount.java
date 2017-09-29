package edu.neu.csye.useraccount.endpoint.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;

/**
 * Defines the properties and basic validation for a request to register a UserAccount.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount implements Serializable {

    private static final long serialVersionUID = -7488073602465822378L;

    @NotBlank(message = "EMAIL_CANT_BE_BLANK")
    private String email;

    @NotBlank(message = "PASSWORD_CANT_BE_BLANK")
    private String password;
}
