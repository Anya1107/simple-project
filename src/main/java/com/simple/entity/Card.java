package com.simple.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "logic_status")
    private String logicStatus;

    @Column(name = "card_first_name")
    private String cardFirstName;

    @Column(name = "card_last_name")
    private String cardLastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private CardAccount cardAccount;
}
