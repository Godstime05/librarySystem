package com.godstimeProjects.librarySystem.repository;

import com.godstimeProjects.librarySystem.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
