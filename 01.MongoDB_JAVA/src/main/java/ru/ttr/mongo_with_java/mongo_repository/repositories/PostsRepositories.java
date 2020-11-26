package ru.ttr.mongo_with_java.mongo_repository.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ttr.mongo_with_java.mongo_repository.models.Post;

import java.util.List;

public interface PostsRepositories extends MongoRepository<Post,String> {
    @Query(value = "{expired: true, $or: [{keywords: ?keywords}, {grade: {$lt: ?1}}]}")
    List<Post> find(@Param("keywords") List<String> keywords,@Param("grade") int grade);
}
