package com.gralak.cocktail.repository;

import com.gralak.cocktail.entity.Cocktail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepo extends MongoRepository<Cocktail, String>
{
    void deleteByCocktailName(String name);
    Cocktail findByCocktailName(String name);
}