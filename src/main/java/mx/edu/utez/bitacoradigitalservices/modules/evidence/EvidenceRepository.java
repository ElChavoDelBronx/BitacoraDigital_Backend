package mx.edu.utez.bitacoradigitalservices.modules.evidence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    @Query("SELECT DISTINCT e FROM Evidence e " +
            "JOIN e.task t JOIN t.project p JOIN t.student s " +
            "LEFT JOIN FETCH e.files " +
            "WHERE p.adviser.id = :advisorId")
    List<Evidence> getEvidencesByAdvisorId(@Param("advisorId") Long advisorId);

}
