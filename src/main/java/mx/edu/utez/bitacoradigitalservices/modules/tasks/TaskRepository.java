package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.id = :projectId")
    long countTotalTasksByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.id = :projectId AND t.status = 'DONE'")
    long countCompletedTasksByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT COALESCE(SUM(t.loggedHours), 0) FROM Task t WHERE t.student.id = :studentId AND t.status = 'DONE'")
    double sumLoggedHoursByStudentId(@Param("studentId") Long studentId);
}