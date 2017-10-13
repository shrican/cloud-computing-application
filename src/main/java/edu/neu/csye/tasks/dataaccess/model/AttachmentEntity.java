package edu.neu.csye.tasks.dataaccess.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


    @Entity
    @Table(name = "Attachment")
    @Data
    public class AttachmentEntity {

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private int attachment_id;

        @Column(name = "url")
        private String url;
    }
}
