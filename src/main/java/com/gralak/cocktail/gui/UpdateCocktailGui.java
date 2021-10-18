package com.gralak.cocktail.gui;

import com.gralak.cocktail.entity.Cocktail;
import com.gralak.cocktail.repository.CocktailRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("update-cocktail")
@PageTitle("Update Cocktail")
public class UpdateCocktailGui extends VerticalLayout
{
    @Autowired
    public UpdateCocktailGui(CocktailRepo cocktailRepo)
    {
        TextField name = new TextField("Enter name of cocktail you would like to update:");
        name.setWidth("25%");

        Button button = new Button("Update Cocktail");

        button.addClickListener(buttonClickEvent -> {
            Cocktail cocktailToUpdate = cocktailRepo.findByCocktailName(name.getValue());
            if(cocktailToUpdate != null)
            {
                UI.getCurrent().navigate(UpdateFormGui.class, cocktailToUpdate.getCocktailName());
            } else
            {
                Notification notification = new Notification("Cannot find cocktail: " + name.getValue(), 3000);
                notification.open();
            }
        });

        add(name, button);

        Button backToMenu = new Button("Back to menu");

        backToMenu.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(MenuGui.class);
        });

        add(backToMenu);
    }
}
