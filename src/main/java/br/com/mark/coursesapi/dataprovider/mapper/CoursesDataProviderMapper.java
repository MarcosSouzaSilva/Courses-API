package br.com.mark.coursesapi.dataprovider.mapper;

import br.com.mark.coursesapi.dataprovider.entity.Courses;
import br.com.mark.coursesapi.usecases.domain.CoursesDomain;

import java.io.IOException;
import java.time.LocalDateTime;


public class CoursesDataProviderMapper {


    public static Courses convert(CoursesDomain domain) throws IOException {
        System.err.println(domain.getImage().getBytes());

        return Courses.builder()
                .title(domain.getTitle())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .typeCourses(domain.getTypeCourses())
                .image(domain.getImage().getBytes()) // converto pra binario
                .video(domain.getVideo().getBytes())
                .level(domain.getLevel())
                .thumbnailUrl(domain.getThumbnailUrl().getBytes())
                .published(true)
                .build();
    }

}