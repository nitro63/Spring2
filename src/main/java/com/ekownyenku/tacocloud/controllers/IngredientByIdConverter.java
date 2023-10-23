package com.ekownyenku.tacocloud.controllers;

import com.ekownyenku.tacocloud.data.IngredientData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.ekownyenku.tacocloud.data.IngredientData.Type;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientData> {

    private Map<String, IngredientData> ingredientMap = new HashMap<>();

    public IngredientByIdConverter(){
        ingredientMap.put("FLTO", new IngredientData("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientMap.put("COTO", new IngredientData("COTO", "Corn Tortilla", Type.WRAP));
        ingredientMap.put("GRBF", new IngredientData("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientMap.put("CARN", new IngredientData("CARN", "Carnitas", Type.PROTEIN));
        ingredientMap.put("TMTO", new IngredientData("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientMap.put("LETC", new IngredientData("LETC", "Lettuce", Type.VEGGIES));
        ingredientMap.put("CHED", new IngredientData("CHED", "Cheddar", Type.CHEESE));
        ingredientMap.put("JACK", new IngredientData("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientMap.put("SLSA", new IngredientData("SLSA", "Salsa", Type.SAUCE));
        ingredientMap.put("SRCR", new IngredientData("SRCR", "Sour Cream", Type.SAUCE));
    }

    @Override
    public IngredientData convert(String id) {
        return ingredientMap.get(id);
    }
}
