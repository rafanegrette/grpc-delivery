package com.perficient.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private Long invoiceId;
  private String clientId;
  private String productId;
  private double value;
  private LocalDateTime requestDate;
  private LocalDateTime responseDate;
}
