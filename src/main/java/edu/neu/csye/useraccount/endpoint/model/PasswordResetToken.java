
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.useraccount.endpoint.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Defines the properties and basic validation for a request to save a UserAccount.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken implements Serializable {

    private static final long serialVersionUID = -7488073602465822378L;

    @NotBlank(message = "USERNAME_CANT_BE_BLANK")
    private String username;

    @NotBlank(message = "PASSWORD_CANT_BE_BLANK")
    private String password;
}
