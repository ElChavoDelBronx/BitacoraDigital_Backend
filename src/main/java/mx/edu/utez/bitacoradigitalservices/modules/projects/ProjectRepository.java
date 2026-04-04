package mx.edu.utez.bitacoradigitalservices.modules.projects;

import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.profile.dtos.StudentProfileProjection;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.ProjectSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value =
            "SELECT p.id AS id, p.name_project AS name, p.description AS description, " +
            "CONCAT(u.name_user, ' ', u.lastname) AS advisorName, pe.name_period AS periodName, " +
            "COUNT(DISTINCT shp.id_student) AS studentCount, COUNT(DISTINCT t.id) AS totalTasks, " +
            "COUNT(DISTINCT CASE WHEN t.status = 'Completed' THEN t.id END) AS completedTasks, " +
            "COALESCE(SUM(DISTINCT CASE WHEN t.status = 'Completed' THEN e.worked_hours END), 0) AS workedHours " +
            "FROM project p " +
            "JOIN user u ON p.id_adviser = u.id " +
            "JOIN period pe ON p.id_period = pe.id " +
            "LEFT JOIN student_has_project shp ON p.id = shp.id_project " +
            "LEFT JOIN task t ON p.id = t.id_project " +
            "LEFT JOIN evidence e ON t.id = e.id_task " +
            "GROUP BY p.id, p.name_project, p.description, CONCAT(u.name_user, ' ', u.lastname), pe.name_period",
            nativeQuery = true)
    List<ProjectSummaryDTO> findProjectSummary();
    @Query(value =
            "SELECT p.id AS id, p.name_project AS name, p.description AS description, " +
                    "CONCAT(u.name_user, ' ', u.lastname) AS advisorName, pe.name_period AS periodName, " +
                    "COUNT(DISTINCT shp.id_student) AS studentCount, COUNT(DISTINCT t.id) AS totalTasks, " +
                    "COUNT(DISTINCT CASE WHEN t.status = 'Completed' THEN t.id END) AS completedTasks, " +
                    "COALESCE(SUM(DISTINCT CASE WHEN t.status = 'Completed' THEN e.worked_hours END), 0) AS workedHours " +
                    "FROM project p " +
                    "JOIN user u ON p.id_adviser = u.id " +
                    "JOIN period pe ON p.id_period = pe.id " +
                    "LEFT JOIN student_has_project shp ON p.id = shp.id_project " +
                    "LEFT JOIN task t ON p.id = t.id_project " +
                    "LEFT JOIN evidence e ON t.id = e.id_task " +
                    "WHERE p.id_adviser = :advisorId " +
                    "GROUP BY p.id, p.name_project, p.description, CONCAT(u.name_user, ' ', u.lastname), pe.name_period",
            nativeQuery = true)
    List<ProjectSummaryDTO> findProjectSummaryByAdvisor(@Param("advisorId") Long advisorId);

    @Query(value =
            "SELECT u.email AS email, pe.name_period AS activePeriod, p.needed_hours AS neededHours, " +
                    "COALESCE(SUM(e.worked_hours), 0) AS validatedHours " +
                    "FROM project p " +
                    "JOIN period pe ON pe.id = p.id_period " +
                    "JOIN task t ON t.id_project = p.id " +
                    "JOIN user u ON u.id = t.id_student " +
                    "JOIN evidence e ON e.id_task = t.id " +
                    "WHERE pe.state = 'Activo' AND t.id_student = :studentId AND e.status = 'Approved' " +
                    "GROUP BY u.email, pe.name_period, p.needed_hours"
            , nativeQuery = true)
    StudentProfileProjection getStudentProfile(@Param("studentId") Long studentId);

    boolean existsByNameProjectAndPeriod(String nameProject, Period period);
}