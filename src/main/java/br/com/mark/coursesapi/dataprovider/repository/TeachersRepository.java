package br.com.mark.coursesapi.dataprovider.repository;

import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.dataprovider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeachersRepository extends JpaRepository <Teachers, Long> {

    @Query("SELECT u FROM Teachers u WHERE u.email = :email")
    Optional<Teachers> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Teachers u WHERE u.phone = :phone")
    Optional<Teachers> findByPhone(@Param("phone")String phone);

    @Query("SELECT u FROM Teachers u WHERE u.role = :role")
    Optional<User> findByRole(@Param("role")String role);
}