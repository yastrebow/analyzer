package ru.yastrebov.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yastrebov.analyzer.model.ProcessedRequest;

@Repository
public interface ProcessedRequestRepository extends JpaRepository<ProcessedRequest, Long> {
}
