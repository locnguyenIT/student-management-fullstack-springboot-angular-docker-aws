package com.example.demo.librarycard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/spring-boot/library-card")
public class LibraryCardController {

    private final LibraryCardService libraryCardService;

    @Autowired
    public LibraryCardController(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @GetMapping
    public List<LibraryCard> getAllLibraryCard()
    {
        return libraryCardService.getAllLibraryCard();
    }

    @GetMapping(path = "/get/{libraryId}")
    public LibraryCard getOneLibraryCard(@PathVariable("libraryId") Integer libraryId)
    {
        return libraryCardService.getOneLibraryCard(libraryId);
    }

    @PostMapping(path = "/add/studentId/{studentId}")
    public void addLibraryCard(@RequestBody LibraryCard libraryCard, @PathVariable("studentId") Integer studentId)
    {
        libraryCardService.addLibraryCard(libraryCard,studentId);
    }

    @DeleteMapping(path = "/delete/{library-card-Id}")
    public void deleteLibrary(@PathVariable("library-card-Id") Integer libraryCardId)
    {
        libraryCardService.deleteLibrary(libraryCardId);
    }

    @PutMapping(path = "/update/{libraryId}") //http://localhost:8080/api/spring-boot/library/update/"libraryId"?card_number=""
    public void updateLibrary(@PathVariable("libraryId") Integer libraryId, @RequestParam(required = false) String card_number)
    {
        libraryCardService.updateLibrary(libraryId,card_number);
    }



}
