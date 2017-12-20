/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/

package edu.neu.csye.tasks.dataaccess.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TASKS")
@Data
public class TaskEntity {

    @Id
    @Column(name = "taskId")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String taskId;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "TASK_ATTACHMENT", joinColumns = @JoinColumn(name = "taskId"), inverseJoinColumns = @JoinColumn(name = "attachmentId") )
    private Set<AttachmentEntity> attachment;
}
