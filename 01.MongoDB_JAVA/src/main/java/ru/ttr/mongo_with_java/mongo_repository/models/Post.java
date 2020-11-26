package ru.ttr.mongo_with_java.mongo_repository.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    private String _id;
    private String date;
    private String description;
    private boolean expired;
    private Integer grade;
    private List<String> keywords;
    private String title;
    private String user;
}
