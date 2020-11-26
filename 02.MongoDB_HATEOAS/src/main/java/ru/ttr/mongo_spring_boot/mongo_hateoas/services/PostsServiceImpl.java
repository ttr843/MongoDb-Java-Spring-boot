package ru.ttr.mongo_spring_boot.mongo_hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ttr.mongo_spring_boot.mongo_hateoas.models.Post;
import ru.ttr.mongo_spring_boot.mongo_hateoas.repositories.PostsRepository;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public Post setExpired(String postId) {
        Post post = postsRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        post.setExpired(true);
        postsRepository.save(post);
        return post;
    }
}
