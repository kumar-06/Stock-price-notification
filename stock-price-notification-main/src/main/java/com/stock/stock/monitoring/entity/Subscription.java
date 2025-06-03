package com.stock.stock.monitoring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "username")
    private User user;


}