package ru.intervale.smev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intervale.smev.entity.NaturalQueueResponse;

import java.util.Optional;

@Repository
public interface NaturalQueueResponseRepository extends JpaRepository<NaturalQueueResponse, Long> {

    Optional<NaturalQueueResponse> findByVehicleCertificate(String vehicleCertificate);
}
