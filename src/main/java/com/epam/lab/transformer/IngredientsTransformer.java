package com.epam.lab.transformer;

import com.epam.lab.model.Ingredients;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientsTransformer implements PrepareStatementTransformers<Ingredients> {

    public Ingredients transform(ResultSet resultSet) {
        Ingredients ingredients = null;
        try {
            ingredients = new Ingredients();
            ingredients.setIdIngredients(resultSet.getInt("id_ingredients"));
            ingredients.setWater(resultSet.getInt("water"));
            ingredients.setMalt(resultSet.getInt("malt"));
            ingredients.setHop(resultSet.getInt("hop"));
            ingredients.setSugar(resultSet.getInt("sugar"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public PreparedStatement transform(PreparedStatement preparedStatement, Ingredients ingredients) {
        try {
            preparedStatement.setInt(1, ingredients.getIdIngredients() != null ? ingredients.getIdIngredients() : 0);
            preparedStatement.setInt(2, ingredients.getWater());
            preparedStatement.setInt(3, ingredients.getMalt());
            preparedStatement.setInt(4, ingredients.getHop());
            preparedStatement.setInt(5, ingredients.getSugar());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    public PreparedStatement transformID(PreparedStatement preparedStatement, Integer id) {
        try {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

}





