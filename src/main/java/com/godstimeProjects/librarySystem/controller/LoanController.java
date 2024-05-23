package com.godstimeProjects.librarySystem.controller;

import com.godstimeProjects.librarySystem.entity.Loan;
import com.godstimeProjects.librarySystem.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/getAll")
    public List<Loan> getAllLoans(){
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id){
        return loanService.getLoanById(id);
    }

    @PostMapping("/create")
    public Loan createLoan(@RequestBody Loan loan){
        return loanService.saveLoan(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan loan){
        Loan existingLoan = loanService.getLoanById(id);
        existingLoan.setLoanDate(loan.getLoanDate());
        existingLoan.setReturnDate(loan.getReturnDate());

        return loanService.saveLoan(existingLoan);
    }
    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        loanService.deleteLoan(id);
    }
}
