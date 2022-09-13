package ru.yastrebov.analyzer.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yastrebov.analyzer.mapstruct.ProcessedRequestMapper;
import ru.yastrebov.analyzer.model.ProcessedRequest;
import ru.yastrebov.analyzer.model.ProcessedRequestDTO;
import ru.yastrebov.analyzer.model.enums.Status;
import ru.yastrebov.analyzer.repository.ProcessedRequestRepository;
import ru.yastrebov.requestAnalyzerLib.model.Request;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalyzerServiceImplTest {

    @Mock
    private ProcessedRequestRepository repository;

    @Mock
    private ProcessedRequestMapper mapper;

    @InjectMocks
    private AnalyzerServiceImpl service;

    Request message = Request.builder()
            .id(1234567890L)
            .name("John Dow")
            .birthDate(LocalDate.parse("2000-01-01"))
            .amount(15000000L)
            .term(84)
            .build();

    ProcessedRequestDTO checkedMessage = new ProcessedRequestDTO()
            .id(1234567890L)
            .name("John Dow")
            .birthDate(LocalDate.parse("2000-01-01"))
            .amount(15000000L)
            .term(84)
            .status(ProcessedRequestDTO.StatusEnum.valueOf("APPROVED"));

    ProcessedRequest checkedRequest = ProcessedRequest.builder()
            .id(1234567890L)
            .name("John Dow")
            .birthDate(LocalDate.parse("2000-01-01"))
            .amount(15000000L)
            .term(84)
            .status(Status.valueOf("APPROVED"))
            .build();

    LocalDate birthDay = message.getBirthDate();
    int checkedAge = Period.between(birthDay, LocalDate.now()).getYears();

    @Test
    void createProcessedRequest() {
        when(repository.save(checkedRequest)).thenReturn(checkedRequest);
        when(mapper.dtoToEntity(any())).thenReturn(checkedRequest);
        when(mapper.entityToDto(any())).thenReturn(checkedMessage);

        ProcessedRequestDTO createdDto = service.createProcessedRequest(checkedMessage);

        assertEquals(createdDto.getStatus().toString().toUpperCase(), checkedRequest.getStatus().toString());
        verify(repository, times(1)).save(checkedRequest);
    }

    @Test
    void checkRequest() {
        when(mapper.entityToDto(any())).thenReturn(checkedMessage);

        service.checkRequest(message);

        assertEquals(message.getId(), 1234567890L);
        assertEquals(checkedAge, 22);
        assertEquals(message.getTerm(), 84);
        assertEquals(checkedMessage.getStatus().toString().toUpperCase(), "APPROVED");
    }
}