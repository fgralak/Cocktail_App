package com.gralak.cocktail.gui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@PageTitle("Menu")
public class MenuGui extends VerticalLayout
{
    @Autowired
    public MenuGui()
    {
        Html title = new Html("<h1>Your Favourite Cocktail App</h1>");
        title.getElement().getStyle()
                .set("margin-top", "10px")
                .set("margin-bottom", "10px")
                .set("color", "MediumBlue")
                .set("font-family", "Times New Roman");

        add(title);

        RouterLink showMyFavouriteCocktails = new RouterLink("Show my favourite cocktails", CocktailsGalleryGui.class);
        showMyFavouriteCocktails.getElement().getStyle()
                .set("color", "MediumOrchid")
                .set("font-size", "25px");

        RouterLink addCocktailToMyList = new RouterLink("Add cocktail", AddCocktailGui.class);
        addCocktailToMyList.getElement().getStyle()
                .set("color", "LimeGreen")
                .set("font-size", "25px");

        RouterLink updateCocktailFromMyList = new RouterLink("Update cocktail", UpdateCocktailGui.class);
        updateCocktailFromMyList.getElement().getStyle()
                .set("color", "Gold")
                .set("font-size", "25px");

        RouterLink findRandomCocktail = new RouterLink("Find random cocktail", RandomCocktailGui.class);
        findRandomCocktail.getElement().getStyle()
                .set("color", "Cyan")
                .set("font-size", "25px");

        RouterLink findCocktailsByName = new RouterLink("Find cocktails with given name", CocktailByNameGui.class);
        findCocktailsByName.getElement().getStyle()
                .set("color", "Pink")
                .set("font-size", "25px");

        RouterLink deleteCocktailFromMyList = new RouterLink("Delete cocktail", DeleteCocktailGui.class);
        deleteCocktailFromMyList.getElement().getStyle()
                .set("color", "Red")
                .set("font-size", "25px");

        add(showMyFavouriteCocktails, addCocktailToMyList, updateCocktailFromMyList, findRandomCocktail,
                findCocktailsByName, deleteCocktailFromMyList);
    }
}
