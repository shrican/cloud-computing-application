package edu.neu.csye.tasks.dataaccess.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "ATTACHMENTS")
@Data
public class AttachmentEntity {

    @Id
    @Column(name = "attachmentId")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String attachmentId;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name="taskId", nullable=false)
    private TaskEntity task;
}

