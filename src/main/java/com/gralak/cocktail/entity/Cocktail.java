package com.gralak.cocktail.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
@Data
public class Cocktail
{
    @Id
    private String id;
    private String cocktailName;
    private String typeOfGlass;
    private HashMap<String, String> ingredientsWithMeasurement;
    private String prepareInstruction;
    private String imageURL;

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
