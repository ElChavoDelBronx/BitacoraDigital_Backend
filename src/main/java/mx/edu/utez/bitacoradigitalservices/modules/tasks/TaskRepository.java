package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByStudentId(Long studentId);
    List<Task> findByProjectIdAndStatus(Long projectId, String status);

}
