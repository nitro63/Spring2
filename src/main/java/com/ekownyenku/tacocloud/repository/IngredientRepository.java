package com.ekownyenku.tacocloud.repository;

import com.ekownyenku.tacocloud.data.IngredientData;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<IngredientData> findAll();

    IngredientData findOne(String id);

    Optional<IngredientData> findById(String id);
    IngredientData save(IngredientData ingredient);
}
