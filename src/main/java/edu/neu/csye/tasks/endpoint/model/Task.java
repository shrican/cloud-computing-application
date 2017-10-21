/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.endpoint.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the properties and basic validation for a request to create a task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    private static final long serialVersionUID = -7588073602465822378L;

    private String id;

    @NotBlank(message = "DESCRIPTION CANT BE BLANK")
    private String description;

    private List<Attachment> attachment;
}
