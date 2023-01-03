package ru.intervale.smev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intervale.smev.entity.Penalty;

import java.util.Optional;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    Optional<Penalty> findByVehicleCertificate(String vehicleCertificate);

    Optional<Penalty> findByTaxpayerIdentificationNumber(String taxpayerIdentificationNumber);
}
