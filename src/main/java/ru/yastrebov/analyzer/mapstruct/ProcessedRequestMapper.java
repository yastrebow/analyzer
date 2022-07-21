package ru.yastrebov.analyzer.mapstruct;

import org.mapstruct.*;
import ru.yastrebov.analyzer.model.ProcessedRequestDTO;
import ru.yastrebov.analyzer.model.ProcessedRequest;

@Mapper(componentModel = "spring")
public interface ProcessedRequestMapper {

    ProcessedRequestDTO entityToDto(ProcessedRequest processedRequest);

    ProcessedRequest dtoToEntity (ProcessedRequestDTO processedRequestDTO);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    void updateFromDtoToEntity(ProcessedRequestDTO processedRequestDTO, @MappingTarget ProcessedRequest processedRequest);
}
