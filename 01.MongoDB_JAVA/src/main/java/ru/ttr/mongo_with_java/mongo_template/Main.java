package ru.ttr.mongo_with_java.mongo_template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import ru.ttr.mongo_with_java.mongo_template.config.SimpleMongoConfig;
import ru.ttr.mongo_with_java.mongo_template.models.Post;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
        MongoTemplate template = applicationContext.getBean(MongoTemplate.class);
        //save query
        Post post = Post.builder()
                .date("Пятница 13")
                .description("Java + MongoDb")
                .expired(false)
                .grade(5)
                .keywords(Arrays.asList("Java","MongoDB","Template","Example"))
                .title("learn MongoDB + JAVA")
                .build();
        template.save(post);
        //find query
        List<Post> postList = template.find(
                new Query(
                        where("expired").is(false)
                        .orOperator(where("keywords").is("Java"),
                                where("grade").is(5)
                        )
                ),Post.class,"post"
        );
        System.out.println(postList);
    }
}
