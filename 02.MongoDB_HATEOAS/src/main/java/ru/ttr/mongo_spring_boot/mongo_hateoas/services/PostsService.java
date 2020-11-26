package ru.ttr.mongo_spring_boot.mongo_hateoas.services;

import ru.ttr.mongo_spring_boot.mongo_hateoas.models.Post;

public interface PostsService {
    Post setExpired(String postId);
}
