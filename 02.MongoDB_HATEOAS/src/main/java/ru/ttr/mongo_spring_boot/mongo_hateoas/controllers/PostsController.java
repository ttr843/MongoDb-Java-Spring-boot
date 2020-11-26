package ru.ttr.mongo_spring_boot.mongo_hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ttr.mongo_spring_boot.mongo_hateoas.services.PostsService;



@RepositoryRestController
public class PostsController {
    @Autowired
    private PostsService postsService;

    @RequestMapping(value = "/posts/{post-id}/setExpired",method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> setExpired(@PathVariable("post-id") String postId){
        return ResponseEntity.ok(
                EntityModel.of(postsService.setExpired(postId))
        );
    }
}
