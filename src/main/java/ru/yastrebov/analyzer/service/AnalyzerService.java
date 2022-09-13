package ru.yastrebov.analyzer.service;

import ru.yastrebov.analyzer.model.ProcessedRequestDTO;
import ru.yastrebov.requestAnalyzerLib.model.Request;

public interface AnalyzerService {

    ProcessedRequestDTO createProcessedRequest(ProcessedRequestDTO processedRequestDTO);

    void checkRequest(Request message);
}
