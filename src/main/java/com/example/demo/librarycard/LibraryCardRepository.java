package com.example.demo.librarycard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer> {

    @Query("SELECT c FROM LibraryCard c WHERE c.card_number = ?1 ")
    Optional<LibraryCard >findLibraryCardByCardNumber(String card_number);
}
