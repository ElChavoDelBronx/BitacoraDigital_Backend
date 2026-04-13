package mx.edu.utez.bitacoradigitalservices.modules.period;

import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.ActiveProjectsAndStudents;
import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.BasicPeriodProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    @Query("SELECT pe.id AS id, pe.namePeriod AS periodAlias FROM Period pe " +
            "WHERE :today BETWEEN pe.startDate AND pe.dueDate " +
            "OR :today < pe.startDate")
    List<BasicPeriodProjection> findActiveOrFuturePeriod(@Param("today") LocalDateTime today);

    @Query(value = "SELECT " +
            "(SELECT COUNT(*) FROM user WHERE rol = 'Estudiante' AND user_status = 'Active') AS activeStudents, " +
            "COUNT(DISTINCT p.id) AS activeProjects " +
            "FROM period pe " +
            "JOIN project p ON p.id_period = pe.id " +
            "WHERE NOW() BETWEEN pe.start_date AND pe.due_date", nativeQuery = true)
    ActiveProjectsAndStudents findActiveProjectsAndStudents();

    Period findByState(String state);

    @Query("SELECT DISTINCT pe FROM Period pe WHERE :today BETWEEN pe.startDate AND pe.dueDate")
    Period findActivePeriod(@Param("today") LocalDateTime today);
}
