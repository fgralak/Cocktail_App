package com.gralak.cocktail.gui;

import com.gralak.cocktail.service.CocktailService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("delete-cocktail")
@PageTitle("Delete Cocktail")
public class DeleteCocktailGui extends VerticalLayout
{
    @Autowired
    public DeleteCocktailGui(CocktailService cocktailService)
    {
        TextField name = new TextField("Enter name of cocktail you would like to delete:");
        name.setWidth("25%");

        Button button = new Button("Delete Cocktail");

        button.addClickListener(buttonClickEvent -> {

            Notification notification;
            try
            {
                cocktailService.deleteCocktailByName(name.getValue());
                notification = new Notification("Cocktail: " + name.getValue() + " deleted!", 3000);
            } catch(Exception e)
            {
                notification = new Notification("Cannot delete cocktail: " + name.getValue(), 3000);
            }

            notification.open();
        });

        add(name, button);

        Button backToMenu = new Button("Back to menu");

        backToMenu.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(MenuGui.class);
        });

        add(backToMenu);
    }
}
