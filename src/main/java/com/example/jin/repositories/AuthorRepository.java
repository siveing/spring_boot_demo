package com.example.jin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jin.models.AuthorModel;

public interface AuthorRepository extends CrudRepository<AuthorModel, Integer> {
    // Define custom methods if needed
}