package com.booleanuk.library.controllers;

import com.booleanuk.library.models.DVD;
import com.booleanuk.library.repository.DVDRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardgames")
public class DVDController extends GenericController<DVD, DVDRepository> {

    @Override
    protected void updateItemValues(DVD existingDVD, DVD updatedDVD){
        if (updatedDVD.getTitle() != null){
            existingDVD.setTitle(updatedDVD.getTitle());
        }
    }
}