package com.ekownyenku.tacocloud.repository;

import com.ekownyenku.tacocloud.data.IngredientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<IngredientData> findAll() {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    private IngredientData mapRowToIngredient(ResultSet resultSet, int rowNum)
        throws SQLException{
            return new IngredientData(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    IngredientData.Type.valueOf(resultSet.getString("type"))
            );
        }

    @Override
    public IngredientData findOne(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient where id = ?",
                this::mapRowToIngredient, id);
    }

    @Override
    public Optional<IngredientData> findById(String id) {
        List<IngredientData> results =jdbc.query(
                "select id, name, type from Ingredient where id = ?",
                this::mapRowToIngredient,
                id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public IngredientData save(IngredientData ingredient) {
        jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
