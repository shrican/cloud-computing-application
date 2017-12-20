
/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/
package edu.neu.csye.tasks.endpoint.model;

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
public class Attachment implements Serializable {

    private static final long serialVersionUID = -7488073602465822378L;

    private String attachmentid;

    @NotBlank(message = "URL CANT BE BLANK")
    private String url;
}
