
package com.gralak.cocktail.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "drinks"
})
@Generated("jsonschema2pojo")
public class DrinkList {

    @JsonProperty("drinks")
    private List<Drink> drinks = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("drinks")
    public List<Drink> getDrinks() {
        return drinks;
    }

    @JsonProperty("drinks")
    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
