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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Route("update-form")
@PageTitle("Update Form")
public class UpdateFormGui extends VerticalLayout  implements HasUrlParameter<String>
{
    TextField name;
    TextField glass;
    TextArea ingredients;
    TextArea measurements;
    Label label;
    TextArea instruction;
    TextField imageUrl;
    
    private final CocktailService cocktailService;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String cocktailName)
    {
        if(cocktailName == null)
        {
            throw new RuntimeException("Cocktail name was not sent");
        }
        Cocktail cocktail = cocktailService.getCocktailFromDBByName(cocktailName);

        name.setValue(cocktail.getCocktailName());
        glass.setValue(cocktail.getTypeOfGlass());

        StringBuilder ing = new StringBuilder();
        StringBuilder mes = new StringBuilder();

        for (Map.Entry<String, String> entrySet : cocktail.getIngredientsWithMeasurement().entrySet())
        {
            ing.append(entrySet.getKey()).append("\n");
            mes.append(entrySet.getValue()).append("\n");
        }

        ingredients.setValue(ing.toString());
        measurements.setValue(mes.toString());
        label.add(ingredients, measurements);
        instruction.setValue(cocktail.getPrepareInstruction());
        imageUrl.setValue(cocktail.getImageURL());
    }

    @Autowired
    public UpdateFormGui(CocktailService cocktailService, CocktailService cocktailService1)
    {
        this.cocktailService = cocktailService1;
        
        name = new TextField("Enter your cocktail name:");
        name.setWidth("20%");

        glass = new TextField("Enter recommended type of glass:");
        glass.setWidth("20%");

        ingredients = new TextArea("Enter list of ingredients:");

        measurements = new TextArea("Enter list of measurements:");

        label = new Label();
        label.add(ingredients, measurements);

        instruction = new TextArea("Enter preparation instruction:");
        instruction.setWidth("20%");

        imageUrl = new TextField("Enter image URL:");
        imageUrl.setWidth("20%");

        Button button = new Button("Update Cocktail");

        button.addClickListener(buttonClickEvent ->
        {
            Cocktail updatedCocktail = new Cocktail();
            updatedCocktail.setCocktailName(name.getValue());
            updatedCocktail.setTypeOfGlass(glass.getValue());
            try
            {
                updatedCocktail.setIngredientsWithMeasurement(createHashMap(ingredients.getValue(), measurements.getValue()));
            }
            catch (Exception e)
            {
                throw new RuntimeException("Empty ingredient or measurement value");
            }
            updatedCocktail.setPrepareInstruction(instruction.getValue());
            updatedCocktail.setImageURL(imageUrl.getValue());

            Notification notification;
            try
            {
                Cocktail result = cocktailService.updateCocktail(updatedCocktail);
                notification = new Notification("Cocktail " + result.getCocktailName() +" updated!", 3000);
            } catch (Exception e)
            {
                notification = new Notification("Cannot update cocktail!", 3000);
            }
            notification.open();
        });

        add(name, glass, label, instruction, imageUrl, button);

        Button backToMenu = new Button("Back to menu");

        backToMenu.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(MenuGui.class));

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
