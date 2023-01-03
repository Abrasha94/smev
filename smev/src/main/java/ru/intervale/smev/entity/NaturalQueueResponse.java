package ru.intervale.smev.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "natural_queue_response")
public class NaturalQueueResponse {

    @Id
    private Long id;

    @Column(name = "accrual_amount", nullable = false)
    private BigDecimal accrualAmount;

    @Column(name = "amount_to_be_paid", nullable = false)
    private BigDecimal amountToBePaid;

    @Column(name = "resolution_number", nullable = false)
    private String resolutionNumber;

    @Column(name = "vehicle_certificate", nullable = false)
    private String vehicleCertificate;

    @Column(name = "date_of_the_resolution", nullable = false)
    private LocalDate dateOfTheResolution;

    @Column(name = "article_of_the_administrative_code", nullable = false)
    private String articleOfTheAdministrativeCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NaturalQueueResponse that = (NaturalQueueResponse) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
