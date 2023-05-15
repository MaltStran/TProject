package ru.tinkoff.edu.java.scrapper.Model.Jpa;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "link")
@NoArgsConstructor
public class LinkJpa {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "update", nullable = false)
    private Timestamp update;

}
