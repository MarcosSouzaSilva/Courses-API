package br.com.mark.coursesapi.usecases.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDomain {


    String name;

    String description;

    String iconUrl;

}
