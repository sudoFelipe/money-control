package dev.programing.moneycontrol.repository;

import dev.programing.moneycontrol.model.Ativo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AtivoRepository extends ReactiveMongoRepository<Ativo, String> {
}
