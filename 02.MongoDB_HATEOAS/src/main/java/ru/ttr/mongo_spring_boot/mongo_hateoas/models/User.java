package ru.ttr.mongo_spring_boot.mongo_hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private Integer age;
    private String country;
    private String course;
    private String firstname;
    private String lastname;
    private Double rate;
    private String status;
}
