package com.example.demo.librarycard;

import com.example.demo.exception.APIEntityNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LibraryCardService {

    private final LibraryCardRepository libraryCardRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public LibraryCardService(LibraryCardRepository libraryCardRepository, StudentRepository studentRepository) {
        this.libraryCardRepository = libraryCardRepository;
        this.studentRepository = studentRepository;
    }

    public List<LibraryCard> getAllLibraryCard()
    {
        return libraryCardRepository.findAll();
    }

    public LibraryCard getOneLibraryCard(Integer libraryId)
    {
        return libraryCardRepository.findById(libraryId).orElseThrow(()-> new APIEntityNotFoundException("Library with id "+libraryId+" was not found"));
    }

    public void addLibraryCard(LibraryCard libraryCard, Integer studentId)
    {
        boolean exists = studentRepository.existsById(studentId);//check if student already have in student table
        if(!exists)
        {
            throw new APIEntityNotFoundException("Student with id "+studentId+" was not found in student table. Please add student");
        }
        boolean student = libraryCardRepository.existsById(studentId); //check if student already have in library table
        if(student)
        {
            throw new APIEntityNotFoundException("Student with id "+studentId+" already exists in library_card table");
        }
        Optional<LibraryCard> card = libraryCardRepository.findLibraryCardByCardNumber(libraryCard.getCard_number());
        if(card.isPresent())
        {
            throw new BadRequestException("Card number already exist in database");
        }
        libraryCard.setStudent(studentRepository.getById(studentId));
        libraryCardRepository.save(libraryCard);
        System.out.println(libraryCard);

    }

    public void deleteLibrary(Integer libraryId)
    {
        boolean exists = libraryCardRepository.existsById(libraryId); //check if student already have in library table
        if(!exists)
        {
            throw new APIEntityNotFoundException("library with id "+libraryId+" was not found");
        }
        System.out.println("library with id "+libraryId+" has been delete");
        libraryCardRepository.deleteById(libraryId);

    }

    public void updateLibrary(Integer libraryId, String card_number)
    {
         LibraryCard libraryCard = libraryCardRepository.findById(libraryId).orElseThrow(()-> new APIEntityNotFoundException("library with id "+libraryId+" was not found"));
         if(card_number != null && !Objects.equals(libraryCard.getCard_number(),card_number))
         {
             Optional<LibraryCard> libraryCarByCardNumber = libraryCardRepository.findLibraryCardByCardNumber(card_number);
             if(libraryCarByCardNumber.isPresent())
             {
                 throw new BadRequestException("Card number already exist in database");
             }
             libraryCard.setCard_number(card_number);
         }
         libraryCardRepository.save(libraryCard);

    }


}
