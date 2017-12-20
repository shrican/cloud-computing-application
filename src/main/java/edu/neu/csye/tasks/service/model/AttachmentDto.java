/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/

package edu.neu.csye.tasks.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A model of the task domain object that is exposed to the public via an api.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachmentDto {

    private String attachmentid;
    private String url;
    private String taskId;
}
