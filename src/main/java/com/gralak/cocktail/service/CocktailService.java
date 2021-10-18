package com.gralak.cocktail.service;

import com.gralak.cocktail.dto.CocktailDto;
import com.gralak.cocktail.entity.Cocktail;
import com.gralak.cocktail.model.Drink;
import com.gralak.cocktail.model.DrinkList;
import com.gralak.cocktail.repository.CocktailRepo;
import com.gralak.cocktail.webclient.CocktailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CocktailService
{
    private final CocktailClient cocktailClient;
    private final CocktailRepo cocktailRepo;

    public List<CocktailDto> getCocktailsByName(String name)
    {
        return createCocktailDtosList(cocktailClient.getCocktailsByName(name));
    }

    public List<CocktailDto> getCocktailsByFirstLetter(String letter)
    {
        return createCocktailDtosList(cocktailClient.getCocktailsByFirstLetter(letter));
    }

    public CocktailDto getRandomCocktail()
    {
        return createCocktailDtosList(cocktailClient.getRandomCocktail()).get(0);
    }

    public CocktailDto createCocktailDto(Drink drink)
    {
        return new CocktailDto(
                drink.getStrDrink(),
                drink.getStrGlass(),
                createIngredientsWithMeasurementsMap(drink),
                drink.getStrInstructions(),
                drink.getStrDrinkThumb()
        );
    }

    public List<CocktailDto> createCocktailDtosList(DrinkList drinkList)
    {
        List<CocktailDto> cocktailDtos = new ArrayList<>();
        for (Drink drink : Objects.requireNonNull(drinkList).getDrinks())
        {
            cocktailDtos.add(createCocktailDto(drink));
        }
        return cocktailDtos;
    }

    public HashMap<String, String> createIngredientsWithMeasurementsMap(Drink drink)
    {
        HashMap<String, String> map = new HashMap<>();
        if(drink.getStrIngredient1() != null )
            map.put(drink.getStrIngredient1(), drink.getStrMeasure1());
        if(drink.getStrIngredient2() != null )
            map.put(drink.getStrIngredient2(), drink.getStrMeasure2());
        if(drink.getStrIngredient3() != null )
            map.put(drink.getStrIngredient3(), drink.getStrMeasure3());
        if(drink.getStrIngredient4() != null )
            map.put(drink.getStrIngredient4(), drink.getStrMeasure4());
        if(drink.getStrIngredient5() != null )
            map.put(drink.getStrIngredient5(), drink.getStrMeasure5());
        if(drink.getStrIngredient6() != null )
            map.put(drink.getStrIngredient6(), drink.getStrMeasure6());
        if(drink.getStrIngredient7() != null )
            map.put(drink.getStrIngredient7(), drink.getStrMeasure7());
        if(drink.getStrIngredient8() != null )
            map.put(drink.getStrIngredient8(), drink.getStrMeasure8());
        if(drink.getStrIngredient9() != null )
            map.put(drink.getStrIngredient9(), drink.getStrMeasure9());
        return map;
    }

    public List<Cocktail> getAllCocktailsFromDB()
    {
        return cocktailRepo.findAll();
    }

    public Cocktail getCocktailFromDBByName(String name)
    {
        return cocktailRepo.findByCocktailName(name);
    }

    public Cocktail saveCocktail(Cocktail cocktail)
    {
        if(cocktailRepo.findByCocktailName(cocktail.getCocktailName()) != null)
        {
            throw new IllegalStateException("Cocktail with such name already exists");
        }
        if(cocktail.getCocktailName() == null || cocktail.getCocktailName().length() == 0)
        {
            throw new IllegalStateException("Cocktail name is null or empty");
        }
        return cocktailRepo.save(cocktail);
    }

    public Cocktail updateCocktail(Cocktail cocktail)
    {
        if(cocktailRepo.findByCocktailName(cocktail.getCocktailName()) == null)
        {
            throw new NoSuchElementException("Cannot find cocktail with given name");
        }
        if(cocktail.getCocktailName() == null || cocktail.getCocktailName().length() == 0)
        {
            throw new IllegalStateException("Cocktail name is null or empty");
        }
        cocktailRepo.deleteByCocktailName(cocktail.getCocktailName());
        return cocktailRepo.save(cocktail);
    }

    public void deleteCocktailByName(String name)
    {
        if(cocktailRepo.findByCocktailName(name) == null)
        {
            throw new NoSuchElementException("Cannot find cocktail with given name");
        }
        cocktailRepo.deleteByCocktailName(name);
    }
}
