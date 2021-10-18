package com.gralak.cocktail.gui;

import com.gralak.cocktail.dto.CocktailDto;
import com.gralak.cocktail.entity.Cocktail;
import com.gralak.cocktail.service.CocktailService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("random-cocktail")
@StyleSheet("/css/gallerystyle.css")
@PageTitle("Random Cocktail")
public class RandomCocktailGui extends VerticalLayout
{
    @Autowired
    public RandomCocktailGui(CocktailService cocktailService)
    {
        CocktailDto cocktailDto = cocktailService.getRandomCocktail();

        Span name = new Span("Name: " + cocktailDto.getCocktailName());
        Span glass = new Span("Glass: " + cocktailDto.getTypeOfGlass());
        Span ingredients = new Span();
        ingredients.getElement().setProperty("innerHTML",
                "Ingredients with measurements:<br>" +
                        "<ul>"+
                        cocktailDto.getHtmlListIngWithMes() +
                        "</ul> ");
        Span instruction = new Span("Preparation: " + cocktailDto.getPrepareInstruction());
        Image image = new Image(cocktailDto.getImageURL(), " There is no such photo :(");
        image.setMaxHeight("20%");
        image.setMaxWidth("20%");

        add(name, glass, ingredients, instruction, image);

        Button addToFavourites = new Button("Add cocktail to your favourites");

        addToFavourites.addClickListener(buttonClickEvent -> {

            Cocktail newCocktail = new Cocktail();
            newCocktail.setCocktailName(cocktailDto.getCocktailName());
            newCocktail.setTypeOfGlass(cocktailDto.getTypeOfGlass());
            newCocktail.setIngredientsWithMeasurement(cocktailDto.getIngredientsWithMeasurement());
            newCocktail.setPrepareInstruction(cocktailDto.getPrepareInstruction());
            newCocktail.setImageURL(cocktailDto.getImageURL());

            Notification notification;
            try
            {
                Cocktail addedCocktail = cocktailService.saveCocktail(newCocktail);
                notification = new Notification("Cocktail: " + addedCocktail.getCocktailName() + " added!", 3000);

            } catch(Exception e)
            {
                notification = new Notification("Cannot add cocktail: " + newCocktail.getCocktailName(), 3000);
            }
            notification.open();

        });

        Button findNewRandom = new Button("Find new random cocktail");

        findNewRandom.addClickListener(buttonClickEvent -> UI.getCurrent().getPage().reload());

        Button backToMenu = new Button("Back to menu");

        backToMenu.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(MenuGui.class));

        add(addToFavourites, findNewRandom, backToMenu);
    }
}
