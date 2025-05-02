package br.com.mark.coursesapi.dataprovider.repository;

import br.com.mark.coursesapi.dataprovider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository <User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password ")
    Optional<User> findEmailAndPassword(@Param("email") String email, @Param("password") String password);


    @Query("SELECT u FROM User u WHERE u.phone = :phone")
    Optional<User> findByPhone(@Param("phone")String phone);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    Optional<User> findByRole(@Param("role")String role);
}