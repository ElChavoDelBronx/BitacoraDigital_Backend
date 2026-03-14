package mx.edu.utez.bitacoradigitalservices.modules.projects;

import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.ProjectSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT new mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.ProjectSummaryDTO(" +
            "p.id, p.nameProject, p.description, u.nameUser, pe.namePeriod, COUNT(st)) " +
            "FROM Project p " +
            "JOIN p.adviser u " +
            "JOIN p.period pe " +
            "LEFT JOIN p.students st " +
            "GROUP BY p.id, p.nameProject, p.description, u.nameUser, pe.namePeriod")
    List<ProjectSummaryDTO> findProjectSummary();

    boolean existsByNameProjectAndPeriod(String nameProject, Period period);
}
