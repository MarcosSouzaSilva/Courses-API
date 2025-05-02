package br.com.mark.coursesapi.dataprovider.repository;

import br.com.mark.coursesapi.dataprovider.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {


}