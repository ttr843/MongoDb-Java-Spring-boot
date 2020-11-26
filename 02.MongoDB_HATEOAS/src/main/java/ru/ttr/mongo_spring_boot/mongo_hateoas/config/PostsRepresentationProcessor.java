package ru.ttr.mongo_spring_boot.mongo_hateoas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.ttr.mongo_spring_boot.mongo_hateoas.controllers.PostsController;
import ru.ttr.mongo_spring_boot.mongo_hateoas.models.Post;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PostsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Post>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Post> process(EntityModel<Post> model) {
        Post post = model.getContent();
        if(post != null && !post.isExpired()){
            model.add(linkTo(methodOn(PostsController.class).setExpired(post.get_id()))
                    .withRel("setExpired"));
        }
        if(post != null && post.isExpired()){
            model.add(links.linkToItemResource(Post.class,post.get_id()).withRel("delete"));
        }
        return model;
    }
}
