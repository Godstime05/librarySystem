package com.godstimeProjects2023.librarySystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class User implements Serializable {
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "active")
    private Integer active;

    @NotNull
    @Column(name = "role")
    private String role;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public User(@NotNull String name, @NotNull String username, @NotNull String password, @NotNull String role) {
        super();
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
