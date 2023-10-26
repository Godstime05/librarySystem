package com.godstimeProjects2023.librarySystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    /**
     *
     * written by Inibehe Ekanem (Godstime05)
     *
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Enter category name")
    @NotBlank(message = "Enter category name")
    @Column(name = "name")
    private String name;

    @NotNull(message = "*Enter category short name")
    @NotBlank(message = "Enter category short name")
    @Size(max = 4, message = "Short_name must not exceed 4 characters")
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "notes")
    @Size(max = 1000, message = "Notes Must not exceed 1000 characters.")
    private String notes;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "created_date")
    private Date createdDate;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;
}
