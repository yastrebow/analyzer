package ru.yastrebov.analyzer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yastrebov.analyzer.mapstruct.ProcessedRequestMapper;
import ru.yastrebov.analyzer.model.ProcessedRequest;
import ru.yastrebov.analyzer.repository.ProcessedRequestRepository;
import ru.yastrebov.analyzer.service.AnalyzerService;
import ru.yastrebov.analyzer.model.ProcessedRequestDTO;
import ru.yastrebov.requestAnalyzerLib.model.Request;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyzerServiceImpl implements AnalyzerService {

    private final ProcessedRequestRepository processedRequestRepository;
    private final ProcessedRequestMapper processedRequestMapper;

    @Override
    public ProcessedRequestDTO createProcessedRequest(ProcessedRequestDTO checkedMessage) {

        ProcessedRequest entity = processedRequestMapper.dtoToEntity(checkedMessage);
        ProcessedRequest savedProcessedRequest = processedRequestRepository.save(entity);

        log.info("Processed request \"{}\" successfully saved!", savedProcessedRequest);

        return processedRequestMapper.entityToDto(savedProcessedRequest);
    }

    @Override
    public void checkRequest(Request message) {
        LocalDate birthDay = message.getBirthDate();
        int age = Period.between(birthDay, LocalDate.now()).getYears();

        ProcessedRequestDTO checkedMessage = new ProcessedRequestDTO();
        checkedMessage.setId(message.getId());
        checkedMessage.setName(message.getName());
        checkedMessage.setBirthDate(message.getBirthDate());
        checkedMessage.setAmount(message.getAmount());
        checkedMessage.setTerm(message.getTerm());

        if ((age < 18 || age > 80) || message.getAmount() > 50_000_000) {

            checkedMessage.setStatus(ProcessedRequestDTO.StatusEnum.NOT_APPROVED);

        } else {
            checkedMessage.setStatus(ProcessedRequestDTO.StatusEnum.APPROVED);

        }
        createProcessedRequest(checkedMessage);
    }
}
