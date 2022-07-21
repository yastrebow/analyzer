package ru.yastrebov.analyzer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yastrebov.analyzer.service.AnalyzerService;
import ru.yastrebov.analyzer.model.ProcessedRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class AnalyzeController {

    private final AnalyzerService analyzerService;

    @Operation(
            summary = "Create row in DB 'processed_requests'",
            description = "Add row into DB after setting status")
    @PostMapping
    public ResponseEntity<ProcessedRequestDTO> postRequest(@RequestBody ProcessedRequestDTO processedRequestDTO) {

        return new ResponseEntity<>(analyzerService.createProcessedRequest(processedRequestDTO), HttpStatus.CREATED);
    }
}
