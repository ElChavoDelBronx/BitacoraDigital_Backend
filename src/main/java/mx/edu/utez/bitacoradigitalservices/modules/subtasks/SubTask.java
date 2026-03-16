package mx.edu.utez.bitacoradigitalservices.modules.subtasks;

import jakarta.persistence.*;
import mx.edu.utez.bitacoradigitalservices.kernel.BaseEntity;
import mx.edu.utez.bitacoradigitalservices.modules.tasks.Task;

@Entity
@Table(name = "sub_task")
public class SubTask extends BaseEntity {
    @Column(name = "name_subtask", nullable = false)
    private String name;
    @Column(name = "checked")
    private boolean checked;

    @ManyToOne
    @JoinColumn(name = "id_task", referencedColumnName = "id")
    private Task task;
}
 

