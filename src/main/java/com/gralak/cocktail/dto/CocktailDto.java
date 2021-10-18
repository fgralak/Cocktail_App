package com.gralak.cocktail.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CocktailDto
{
    private String cocktailName;
    private String typeOfGlass;
    private HashMap<String, String> ingredientsWithMeasurement;
    private String prepareInstruction;
    private String imageURL;

    @JsonIgnore
    public String getHtmlListIngWithMes()
    {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : ingredientsWithMeasurement.entrySet())
        {
            result.append("<li>");
            result.append(entry.getKey()).append(" - ").append(entry.getValue());
            result.append("</li>");
        }
        return result.toString();
    }
}
