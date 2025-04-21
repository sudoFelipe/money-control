package dev.programing.moneycontrol.repository;

import dev.programing.moneycontrol.model.Categoria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends ReactiveMongoRepository<Categoria, String> {
}
