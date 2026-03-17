package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);

    @Query("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.subTask WHERE t.student.id = :studentId")
    List<Task> findAllByStudentId(@Param("studentId") Long studentId);
    List<Task> findByProjectIdAndStatus(Long projectId, String status);

}
