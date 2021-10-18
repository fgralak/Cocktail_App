package com.gralak.cocktail.gui;

import com.gralak.cocktail.entity.Cocktail;
import com.gralak.cocktail.service.CocktailService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Route("add-cocktail")
@PageTitle("Add Cocktail")
public class AddCocktailGui extends VerticalLayout
{
    @Autowired
    public AddCocktailGui(CocktailService cocktailService)
    {
        TextField name = new TextField("Enter your cocktail name:");
        name.setWidth("20%");

        TextField glass = new TextField("Enter recommended type of glass:");
        glass.setWidth("20%");

        TextArea ingredients = new TextArea("Enter list of ingredients:");

        TextArea measurements = new TextArea("Enter list of measurements:");

        Label label = new Label();
        label.add(ingredients, measurements);

        TextField instruction = new TextField("Enter preparation instruction:");
        instruction.setWidth("20%");

        TextField imageUrl = new TextField("Enter image URL:");
        imageUrl.setWidth("20%");

        Button button = new Button("Add Cocktail");

        button.addClickListener(buttonClickEvent -> {
            Cocktail newCocktail = new Cocktail();
            newCocktail.setCocktailName(name.getValue());
            newCocktail.setTypeOfGlass(glass.getValue());
            newCocktail.setIngredientsWithMeasurement(createHashMap(ingredients.getValue(), measurements.getValue()));
            newCocktail.setPrepareInstruction(instruction.getValue());
            newCocktail.setImageURL(imageUrl.getValue());

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

        add(name, glass, label, instruction, imageUrl, button);

        Button backToMenu = new Button("Back to menu");

        backToMenu.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(MenuGui.class);
        });

        add(backToMenu);
    }

    private HashMap<String, String> createHashMap(String listOfIng, String listOfMes)
    {
        String[] splitIng = listOfIng.split("\n");
        String[] splitMes = listOfMes.split("\n");

        HashMap<String, String> map = new HashMap<>();

        for(int i = 0; i < splitIng.length; ++i)
        {
            map.put(splitIng[i], splitMes[i]);
        }

        return map;
    }
}
