package ru.intervale.smev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intervale.smev.entity.NaturalQueueRequest;

@Repository
public interface NaturalQueueRequestRepository extends JpaRepository<NaturalQueueRequest, Long> {
}
