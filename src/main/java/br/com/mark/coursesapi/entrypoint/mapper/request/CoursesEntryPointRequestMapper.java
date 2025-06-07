package br.com.mark.coursesapi.entrypoint.mapper.request;

import br.com.mark.coursesapi.entrypoint.model.request.CourseModelRequest;
import br.com.mark.coursesapi.usecases.domain.CoursesDomain;

import java.time.LocalDateTime;

public class CoursesEntryPointRequestMapper {


    public static CoursesDomain convert(CourseModelRequest modelRequest) {
        return CoursesDomain.builder()
                .title(modelRequest.getTitle())
                .price(modelRequest.getPrice())
                .description(modelRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .image(modelRequest.getImage())
                .typeCourses(modelRequest.getTypeCourses())
                .video(modelRequest.getVideo())
                .level(modelRequest.getLevel())
                .thumbnailUrl(modelRequest.getThumbnailUrl())
                .published(true)
                .build();
    }

}