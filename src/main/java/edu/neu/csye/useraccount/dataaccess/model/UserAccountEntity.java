
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.useraccount.dataaccess.model;

import edu.neu.csye.tasks.dataaccess.model.ResetTokenEntity;
import edu.neu.csye.tasks.dataaccess.model.TaskEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="DEPT_ID", unique= true, nullable=true, insertable=true, updatable=true)
    private ResetTokenEntity resetEntity;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "USERACCOUNT_TASK", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "taskId") )
    private Set<TaskEntity> taskEntity;

    public UserAccountEntity() {
        taskEntity = new HashSet<>();
    }


}
