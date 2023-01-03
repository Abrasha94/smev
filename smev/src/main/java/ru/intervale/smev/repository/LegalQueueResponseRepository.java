package ru.intervale.smev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intervale.smev.entity.LegalQueueResponse;

import java.util.Optional;

@Repository
public interface LegalQueueResponseRepository extends JpaRepository<LegalQueueResponse, Long> {

    Optional<LegalQueueResponse> findByTaxpayerIdentificationNumber(String taxpayerIdentificationNumber);
}
