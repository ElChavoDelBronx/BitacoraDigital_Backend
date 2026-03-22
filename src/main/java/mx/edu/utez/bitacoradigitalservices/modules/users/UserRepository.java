package mx.edu.utez.bitacoradigitalservices.modules.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.BasicUserProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);

    Optional<User> findByEmailAndResetToken(String email, String resetToken);
    @Query("SELECT DISTINCT u FROM User u WHERE u.id IN :ids AND u.rol = :roleName")
    List<User> findAllByIdInAndRole(@Param("ids") List<Long> ids, @Param("roleName") String roleName);

    @Query(value =
            "SELECT DISTINCT u.id AS id, CONCAT(u.name_user, ' ', u.lastname) AS name FROM user u " +
                    "WHERE u.rol = :roleName",
            nativeQuery = true)
    List<BasicUserProjection> findAllByRol(@Param("roleName") String roleName);

    @Query(value =
            "SELECT DISTINCT u.id AS id, CONCAT(u.name_user, ' ', u.lastname) AS name FROM user u " +
                    "WHERE u.rol = 'Estudiante' AND u.id NOT IN (" +
                    "SELECT DISTINCT u.id FROM user u2 JOIN student_has_project shp ON shp.id_student = u2.id " +
                    "JOIN project p ON p.id = shp.id_project JOIN period pe On pe.id = p.id_period " +
                    "WHERE pe.state = 'Activo')",
            nativeQuery = true)
    List<BasicUserProjection> findAvailableStudents();


    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.projects")
    List<User> findAllUserList ();
}