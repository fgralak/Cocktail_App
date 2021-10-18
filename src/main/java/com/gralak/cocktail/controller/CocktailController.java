package com.gralak.cocktail.controller;

import com.gralak.cocktail.dto.CocktailDto;
import com.gralak.cocktail.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CocktailController
{
   private final CocktailService cocktailService;

   @GetMapping("/by-name")
   public ResponseEntity<List<CocktailDto>> getCocktailByName(@RequestParam String name)
   {
      List<CocktailDto> cocktailDtos = cocktailService.getCocktailsByName(name);
      return new ResponseEntity<>(cocktailDtos, HttpStatus.OK);
   }

   @GetMapping("/by-letter")
   public ResponseEntity<List<CocktailDto>> getCocktailByFirstLetter(@RequestParam String letter)
   {
      List<CocktailDto> cocktailDtos = cocktailService.getCocktailsByFirstLetter(letter);
      return new ResponseEntity<>(cocktailDtos, HttpStatus.OK);
   }

   @GetMapping("/random")
   public ResponseEntity<CocktailDto> getRandomCocktail()
   {
      CocktailDto cocktailDto = cocktailService.getRandomCocktail();
      return new ResponseEntity<>(cocktailDto, HttpStatus.OK);
   }

   /*@GetMapping("/all")
   public ResponseEntity<List<Cocktail>> getAllCocktailsFromDB()
   {
      List<Cocktail> cocktails = cocktailService.getAllCocktailsFromDB();
      return new ResponseEntity<>(cocktails, HttpStatus.OK);
   }

   @PostMapping("/save-cocktail")
   public ResponseEntity<Cocktail> addCocktailToDB(@RequestBody Cocktail newCocktail)
   {
      Cocktail cocktail;
      try
      {
         cocktail = cocktailService.saveCocktail(newCocktail);
      } catch (Exception e)
      {
         return new ResponseEntity<>(null, HttpStatus.CONFLICT);
      }
      return new ResponseEntity<>(cocktail, HttpStatus.CREATED);
   }

   @PutMapping("/update-cocktail")
   public ResponseEntity<Cocktail> updateCocktailInDB(@RequestBody Cocktail newCocktail)
   {
      Cocktail cocktail;
      try
      {
         cocktail = cocktailService.updateCocktail(newCocktail);
      } catch (Exception e)
      {
         return new ResponseEntity<>(null, HttpStatus.CONFLICT);
      }
      return new ResponseEntity<>(cocktail, HttpStatus.OK);
   }

   @DeleteMapping
   public ResponseEntity<?> deleteCocktailByName(@RequestParam String name)
   {
      try
      {
         cocktailService.deleteCocktailByName(name);
      } catch (Exception e)
      {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(HttpStatus.OK);
   }*/
}
