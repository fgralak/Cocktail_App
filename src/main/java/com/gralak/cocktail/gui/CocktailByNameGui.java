package com.gralak.cocktail.gui;

import com.gralak.cocktail.dto.CocktailDto;
import com.gralak.cocktail.service.CocktailService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Route(value = "cocktails-by-name")
@StyleSheet("/css/gallerystyle.css")
@PageTitle("Cocktails By Name")
public class CocktailByNameGui extends VerticalLayout
{
    @Autowired
    public CocktailByNameGui(CocktailService cocktailService)
    {
        TextField text = new TextField("Enter cocktail name:");

        Button findByName = new Button("Find Cocktails");

        findByName.addClickListener(buttonClickEvent ->
                displayCocktails(cocktailService, text.getValue()));

        Button backToMenu = new Button("Back to menu");

        backToMenu.addClickListener(buttonClickEvent ->
                UI.getCurrent().navigate(MenuGui.class));

        add(text, findByName, backToMenu);
    }

    public void displayCocktails(CocktailService cocktailService, String cocktailName)
    {
        List<CocktailDto> cocktails = cocktailService.getCocktailsByName(cocktailName);

        Button reenterName = new Button("Reenter name");

        reenterName.addClickListener(buttonClickEvent ->
                UI.getCurrent().getPage().reload());

        add(reenterName);

        Objects.requireNonNull(cocktails).forEach(cocktail -> {

            Span name = new Span("Name: " + cocktail.getCocktailName());

            Span glass = new Span("Glass: " + cocktail.getTypeOfGlass());

            Span ingredients = new Span();
            ingredients.getElement().setProperty("innerHTML",
                    "Ingredients with measurements:<br>" +
                            "<ul>"+
                            cocktail.getHtmlListIngWithMes() +
                            "</ul> ");

            Span instruction = new Span("Preparation: " + cocktail.getPrepareInstruction());

            Image image = new Image(cocktail.getImageURL(), " There is no such photo :(");
            image.setMaxHeight("20%");
            image.setMaxWidth("20%");

            Label gap = new Label();
            gap.setHeight("1em");

            add(name, glass, ingredients, instruction, image, gap);
        });
    }
}
