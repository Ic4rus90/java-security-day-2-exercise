package com.booleanuk.library.repository;

import com.booleanuk.library.models.Loan;
import com.booleanuk.library.models.User;
import com.booleanuk.library.models.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    public List<Loan> findLoansByVideoGame(VideoGame videoGame);
    public List<Loan> findLoansByUser(User user);
}
