package com.booleanuk.library.controllers;

import com.booleanuk.library.models.Book;
import com.booleanuk.library.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController extends GenericController<Book, BookRepository> {

    @Override
    protected void updateItemValues(Book existingBook, Book updatedBook){
        if (updatedBook.getTitle() != null){
            existingBook.setTitle(updatedBook.getTitle());
        }
    }
}