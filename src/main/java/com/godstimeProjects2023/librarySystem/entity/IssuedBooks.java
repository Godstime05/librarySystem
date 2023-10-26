package com.godstimeProjects2023.librarySystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "issued_books")
public class IssuedBooks implements Serializable {

    /**
     *
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @NotNull
    private Book book;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "issued_id")
    @NotNull
    private Issued issue;

    @Column(name = "returned")
    private Integer returned;

}
