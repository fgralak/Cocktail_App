package com.gralak.cocktail.gui;

import com.gralak.cocktail.entity.Cocktail;
import com.gralak.cocktail.service.CocktailService;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Route(value = "favourite-cocktails")
@StyleSheet("/css/gallerystyle.css")
@PageTitle("Favourite Cocktails")
public class CocktailsGalleryGui extends VerticalLayout
{
    @Autowired
    public CocktailsGalleryGui(CocktailService cocktailService)
    {
        List<Cocktail> cocktails = cocktailService.getAllCocktailsFromDB();

        Html title = new Html("<h2>Your Favourite Cocktails:</h2>");
        title.getElement().getStyle().set("color", "Red");
        title.getElement().getStyle().set("font-family", "Times New Roman");
        add(title);

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

        Button backToMenu = new Button("Back to menu");

        backToMenu.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(MenuGui.class));

        add(backToMenu);
    }
}
