package com.example.dave.wallet.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Transaction {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Boolean withdrawal;

    private Integer amount;

}
