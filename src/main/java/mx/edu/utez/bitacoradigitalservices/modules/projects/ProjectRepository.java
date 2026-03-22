package mx.edu.utez.bitacoradigitalservices.modules.projects;

import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.ProjectSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value =
            "SELECT p.id AS id, p.name_project AS name, p.description AS description, " +
            "CONCAT(u.name_user, ' ', u.lastname) AS advisorName, pe.name_period AS periodName, " +
            "COUNT(DISTINCT shp.id_student) AS studentCount, COUNT(DISTINCT t.id) AS totalTasks, " +
            "COUNT(DISTINCT CASE WHEN t.status = 'completed' THEN t.id END) AS completedTasks, " +
            "COALESCE(SUM(e.worked_hours), 0) AS workedHours " +
            "FROM project p " +
            "JOIN user u ON p.id_adviser = u.id " +
            "JOIN period pe ON p.id_period = pe.id " +
            "LEFT JOIN student_has_project shp ON p.id = shp.id_project " +
            "LEFT JOIN task t ON p.id = t.id_project " +
            "LEFT JOIN evidence e ON t.id = e.id_task " +
            "GROUP BY p.id, pe.name_period",
            nativeQuery = true)
    List<ProjectSummaryDTO> findProjectSummary();

    boolean existsByNameProjectAndPeriod(String nameProject, Period period);
}