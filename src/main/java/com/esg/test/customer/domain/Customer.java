package com.esg.test.customer.domain;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByPosition(position = 1)
    private String customerName;
    @Column(unique=true)
    @CsvBindByPosition(position = 2)
    private String customerRef;
    @CsvBindByPosition(position = 3)
    private String addressLine1;
    @CsvBindByPosition(position = 4)
    private String addressLine2;
    @CsvBindByPosition(position = 5)
    private String town;
    @CsvBindByPosition(position = 6)
    private String county;
    @CsvBindByPosition(position = 7)
    private String country;
    @CsvBindByPosition(position = 8)
    private String postcode;
}
