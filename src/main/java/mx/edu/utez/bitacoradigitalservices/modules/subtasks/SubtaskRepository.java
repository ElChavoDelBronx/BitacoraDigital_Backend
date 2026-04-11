package mx.edu.utez.bitacoradigitalservices.modules.subtasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends JpaRepository<SubTask, Long> {
    @Modifying
    @Query(value = "UPDATE subtask SET checked = !checked WHERE id = :id", nativeQuery = true)
    void changeSubtaskChecked(@Param("id") Long id);
}
