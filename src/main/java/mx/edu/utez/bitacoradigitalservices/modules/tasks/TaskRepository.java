package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import mx.edu.utez.bitacoradigitalservices.modules.tasks.dtos.BasicTaskProjection;
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

    @Query("SELECT t.id AS id, t.nameTask AS title FROM Task t " +
            "WHERE t.status IN ('InProgress', 'Rejected')  AND t.student.id = :studentId " +
            "ORDER BY t.dueDate ASC")
    List<BasicTaskProjection> findInProgressTasks(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.id = :projectId")
    long countTotalTasksByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.id = :projectId AND t.status = 'DONE'")
    long countCompletedTasksByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT COALESCE(SUM(t.loggedHours), 0) FROM Task t WHERE t.student.id = :studentId AND t.status = 'DONE'")
    double sumLoggedHoursByStudentId(@Param("studentId") Long studentId);
}