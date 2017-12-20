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

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Defines the properties and basic validation for a request to create a task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    private static final long serialVersionUID = -7588073602465822378L;

    private String taskId;

    @NotBlank(message = "DESCRIPTION CANT BE BLANK")
    @Size(max = 4096, message = "DESCRIPTION_TOO_LARGE")
    private String description;

}
