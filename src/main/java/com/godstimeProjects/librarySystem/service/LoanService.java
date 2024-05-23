package com.godstimeProjects.librarySystem.service;

import com.godstimeProjects.librarySystem.entity.Loan;
import com.godstimeProjects.librarySystem.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public Loan getLoanById(Long id){
        return loanRepository.findById(id).orElse(null);
    }
    public List<Loan> getAllLoans(){
        return loanRepository.findAll();
    }

    public Loan saveLoan(Loan loan){
        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id){
        loanRepository.deleteById(id);
    }
}
