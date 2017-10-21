
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.useraccount.dataaccess.model;

import edu.neu.csye.tasks.dataaccess.model.TaskEntity;
import edu.neu.csye.tasks.endpoint.model.Task;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "taskId")
    private int id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "USERACCOUNT_TASK", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "taskId") )
    private Set<TaskEntity> taskEntity;
}
