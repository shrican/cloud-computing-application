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

import java.util.HashSet;
import java.util.Set;

/**
 * A model of the task domain object that is exposed to the public via an api.
 */
@Data
@AllArgsConstructor
@Builder
public class TaskDto {

    private String id;
    private String description;
    private Set<AttachmentDto> attachmentDtos;

    public TaskDto() {
        attachmentDtos = new HashSet<>();
    }
}
