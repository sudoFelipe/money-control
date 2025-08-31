package dev.programing.moneycontrol.kafka;

import dev.programing.moneycontrol.model.Ativo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserNotificationProducer {


    private final String topic;

    private final KafkaTemplate<Object, Object> template;

    public UserNotificationProducer(@Value("${kafka.task.notification-output}") String topic,
                                    KafkaTemplate<Object, Object> template) {
        this.topic = topic;
        this.template = template;
    }

    public Mono<Ativo> sendNotification(Ativo ativo) {
        return Mono.just(ativo)
                .map(a -> this.template.send(topic, ativo))
                .map(a -> ativo)
                .doOnNext(a -> log.info("Ativo enviado com sucesso."));
    }
}
