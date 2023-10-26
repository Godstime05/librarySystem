package com.godstimeProjects2023.librarySystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Select member type")
    @NotNull(message = "Select Member Type")
    @Column(name = "type")
    private String type;

    @NotEmpty(message = "*Please enter first name")
    @NotNull(message = "*Please enter first name")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Enter middle name")
    @NotNull(message = "Enter middle name")
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Select gender")
    @NotNull(message = "Select gender")
    @Column(name = "gender")
    private String gender;

    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "Enter your date of birth")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "joining_date")
    private Date joiningDate;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email")
    private String email;

}
