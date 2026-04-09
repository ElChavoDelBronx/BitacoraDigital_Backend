package mx.edu.utez.bitacoradigitalservices.modules.evidence;

import mx.edu.utez.bitacoradigitalservices.modules.dashboard.projections.RecentEvidences;
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

    @Query(value = "SELECT CONCAT(u.name_user, ' ', u.lastname) AS studentName, " +
            "t.name_task AS taskTitle, e.upload_date AS uploadDate " +
            "FROM evidence e " +
            "JOIN task t ON t.id = e.id_task " +
            "JOIN student_has_project shp ON shp.id_student = t.id_student " +
            "JOIN user u ON u.id = shp.id_student " +
            "ORDER BY e.upload_date DESC", nativeQuery = true) //Limitar filas, ya sea en backend o frontend
    List<RecentEvidences> getRecentEvidences();


}
