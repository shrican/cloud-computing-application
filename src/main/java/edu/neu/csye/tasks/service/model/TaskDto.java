/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * A model of the task domain object that is exposed to the public via an api.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    private String id;
    private String description;
    private Set<AttachmentDto> attachmentDtoSet;
    private String userId;

}
