package ru.ttr.mongo_spring_boot.mongo_hateoas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ttr.mongo_spring_boot.mongo_hateoas.models.User;

public interface UsersRepository extends PagingAndSortingRepository<User, String> {
}
