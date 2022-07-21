package ru.yastrebov.analyzer.service;

import ru.yastrebov.analyzer.model.ProcessedRequestDTO;
import ru.yastrebov.requestAnalyzerLib.model.RequestDTO;

public interface AnalyzerService {

    ProcessedRequestDTO createProcessedRequest(ProcessedRequestDTO processedRequestDTO);

    void checkRequest(RequestDTO message);
}
