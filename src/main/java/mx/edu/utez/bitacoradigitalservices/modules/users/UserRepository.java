package mx.edu.utez.bitacoradigitalservices.modules.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);
    @Query("SELECT DISTINCT u FROM User u WHERE u.id IN :ids AND u.rol = :roleName")
    List<User> findAllByIdInAndRole(@Param("ids") List<Long> ids, @Param("roleName") String roleName);
}