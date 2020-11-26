package ru.ttr.mongo_spring_boot.mongo_hateoas.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.ttr.mongo_spring_boot.mongo_hateoas.models.Post;

import java.awt.print.Pageable;
import java.util.List;

public interface PostsRepository extends MongoRepository<Post,String> {

    @RestResource(path = "expired",rel = "expired")
    @Query(value = "{expired: true}")
    List<Post> findExpired();

}
