package ru.ttr.mongo_with_java.mongo_repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ttr.mongo_with_java.mongo_repository.config.RepositoriesConfig;
import ru.ttr.mongo_with_java.mongo_repository.repositories.PostsRepositories;
import ru.ttr.mongo_with_java.mongo_template.models.Post;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        PostsRepositories postsRepositories = context.getBean(PostsRepositories.class);
        System.out.println(postsRepositories.find(Collections.singletonList("java"),6 ));
    }
}
