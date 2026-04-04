package mx.edu.utez.bitacoradigitalservices.modules.period;

import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.BasicPeriodProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    @Query("SELECT pe.id AS id, pe.namePeriod AS periodAlias FROM Period pe WHERE pe.state = 'Activo' OR pe.state = 'Futuro' ORDER BY pe.state")
    List<BasicPeriodProjection> findActiveOrFuturePeriod();

    Period findByState(String state);
}
