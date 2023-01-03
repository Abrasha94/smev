package ru.intervale.smev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intervale.smev.entity.LegalQueueRequest;

@Repository
public interface LegalQueueRequestRepository extends JpaRepository<LegalQueueRequest, Long> {
}
