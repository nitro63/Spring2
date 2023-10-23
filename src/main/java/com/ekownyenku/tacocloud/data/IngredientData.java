package com.ekownyenku.tacocloud.data;

import lombok.Data;

@Data
public class IngredientData {
    private  final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
