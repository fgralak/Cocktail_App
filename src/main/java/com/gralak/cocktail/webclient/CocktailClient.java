package com.gralak.cocktail.webclient;

import com.gralak.cocktail.model.DrinkList;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CocktailClient
{
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String API_KEY = "1";
    private final RestTemplate restTemplate = new RestTemplate();

    public DrinkList getCocktailsByName(String name)
    {
        return callGetMethod("{API_KEY}/search.php?s={name}", DrinkList.class, API_KEY, name);
    }
    public DrinkList getCocktailsByFirstLetter(String letter)
    {
        return callGetMethod("{API_KEY}/search.php?f={name}", DrinkList.class, API_KEY, letter);
    }

    public DrinkList getRandomCocktail()
    {
        return callGetMethod("{API_KEY}/random.php", DrinkList.class, API_KEY);
    }

    public <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(BASE_URL + url, responseType, objects);
    }
}
