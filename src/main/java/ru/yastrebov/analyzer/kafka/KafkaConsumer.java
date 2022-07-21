package ru.yastrebov.analyzer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.yastrebov.analyzer.service.impl.AnalyzerServiceImpl;
import ru.yastrebov.requestAnalyzerLib.model.RequestDTO;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final AnalyzerServiceImpl analyzerService;

    @KafkaListener(topics = "loan_requests", groupId = "${spring.kafka.consumer.group-id}")

    private void listenGroupRequests(ConsumerRecord<String, RequestDTO> message) {

        log.info("Received message: {}", message);

        analyzerService.checkRequest(message.value());
    }
}
