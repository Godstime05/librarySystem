package com.godstimeProjects.librarySystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {
    /**
     * written by Inibehe Ekanem(Godstime05)
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Enter book title")
    @NotBlank(message = "Enter book title")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Enter book authors")
    @NotBlank(message = "Enter book authors")
    @Column(name = "authors")
    private String authors;

    @Column(name = "publisher")
    private String publisher;

    @NotNull(message = "Enter book tag")
    @NotBlank(message = "Enter book tag")
    @Column(name = "tag")
    private String tag;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "status")
    private Integer status;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "created_Date")
    private Date createdDate;

}
