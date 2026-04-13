package mx.edu.utez.bitacoradigitalservices.modules.period;

import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.BasicPeriodProjection;
import mx.edu.utez.bitacoradigitalservices.modules.period.projections.PeriodLimitsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    @Query("SELECT pe.id AS id, pe.namePeriod AS periodAlias FROM Period pe " +
            "WHERE :today BETWEEN pe.startDate AND pe.dueDate " +
            "OR :today < pe.startDate")
    List<BasicPeriodProjection> findActiveOrFuturePeriod(@Param("today") LocalDate today);


    @Query("SELECT DISTINCT pe FROM Period pe WHERE :today BETWEEN pe.startDate AND pe.dueDate")
    Optional<Period> findActivePeriod(@Param("today") LocalDate today);

    @Query(value = """
        SELECT 
            (SELECT DATE_ADD(MAX(due_date), INTERVAL 1 DAY) FROM period 
             WHERE due_date < :currentStart AND id != :periodId) as lowerLimit,
            (SELECT DATE_SUB(MIN(start_date), INTERVAL 1 DAY) FROM period 
             WHERE start_date > :currentEnd AND id != :periodId) as upperLimit
        """, nativeQuery = true)
    Optional<PeriodLimitsProjection> findLimitsForPeriod(
        @Param("currentStart") LocalDate currentStart,
        @Param("currentEnd") LocalDate currentEnd,
        @Param("periodId") Long periodId
    );

    @Query(value = "SELECT CAST(COALESCE(MAX(pe.due_date) + INTERVAL 1 DAY, NOW()) AS DATE) FROM Period pe", nativeQuery = true)
    LocalDate getAbsoluteMaxDate();

    @Query(value = "SELECT COUNT(id) FROM period " +
            "WHERE id != :periodId AND start_date <= :currentEnd " +
            "AND due_date >= :currentStart", nativeQuery = true)
    Long getOverlappingPeriods(
            @Param("currentStart") LocalDate currentStart,
            @Param("currentEnd") LocalDate currentEnd,
            @Param("periodId") Long periodId
    );
}
