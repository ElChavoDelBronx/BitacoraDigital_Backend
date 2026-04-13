package mx.edu.utez.bitacoradigitalservices.modules.tasks;

import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.StudentDashboardTaskCount;
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

    @Query(value = "SELECT COUNT(DISTINCT CASE WHEN t.status = 'Completed' THEN t.id END) AS completedTask, " +
            "COUNT(DISTINCT CASE WHEN t.status = 'InProgress' THEN t.id END) AS InProgressTask, " +
            "COUNT(DISTINCT t.id) AS totalTasks " +
            "FROM Task t " +
            "WHERE t.id_student = :studentId", nativeQuery = true)
    StudentDashboardTaskCount findStudentDashboardTaskCount(@Param("studentId") Long studentId);

    @Query("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.subTask WHERE t.student.id = :studentId ORDER BY t.id DESC LIMIT 2")
    List<Task> findRecentTasksByStudent(@Param("studentId") Long studentId);

    @Query(value = "SELECT COALESCE(SUM(e.worked_hours), 0) " +
            "FROM Task t JOIN Project p ON p.id = t.id_project " +
            "JOIN Period pe ON pe.id = p.id_period " +
            "JOIN Evidence e ON e.id_task = t.id " +
            "WHERE t.id_student = :studentId AND e.status = 'Approved'" +
            "AND NOW() BETWEEN pe.start_date AND pe.due_date", nativeQuery = true)
    Long countValidateHoursByStudent(@Param("studentId") Long studentId);
}