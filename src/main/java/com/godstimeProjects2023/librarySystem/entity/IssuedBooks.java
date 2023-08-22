package com.godstimeProjects2023.librarySystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "issued_book")
public class IssuedBooks implements Serializable {

    /**
     *
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "returned")
    private Integer returned;

}
