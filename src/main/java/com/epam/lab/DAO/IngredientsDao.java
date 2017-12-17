package com.epam.lab.DAO;

import com.epam.lab.model.Ingredients;
import com.epam.lab.transformer.IngredientsTransformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientsDao implements CRUD<Ingredients> {
    private IngredientsTransformer ingredienstTransformer;

    public IngredientsDao() {
        this.ingredienstTransformer = new IngredientsTransformer();
    }

    @Override
    public Ingredients readOne(Connection connection, Integer id) {
        Ingredients ingredients = null;

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM ingredients WHERE id_ingredients='" + String.valueOf(id) + "';";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                ingredients = ingredienstTransformer.transform(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    @Override
    public List<Ingredients> readAll(Connection connection) {
        List<Ingredients> ingredientsList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM ingredients";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                ingredientsList.add(ingredienstTransformer.transform(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredientsList;
    }

    @Override
    public Ingredients create(Connection connection, Ingredients ingredients) {
        String sqlQuery = "INSERT INTO ingredients (id_ingredients,water, malt,hop,sugar) VALUES(?, ?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            PreparedStatement preparedStatementPopulated = ingredienstTransformer.transform(preparedStatement, ingredients);
            int rowsUpdated = preparedStatementPopulated.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Creating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatementPopulated.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ingredients.setIdIngredients(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    @Override
    public Ingredients update(Connection connection, Ingredients ingredients) {
        String sqlQuery = "UPDATE ingredients SET id_ingredients = ?, water = ?, malt= ?, hop = ?, sugar=? WHERE id_ingredients = '"
                + String.valueOf(ingredients.getIdIngredients()) + "'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            PreparedStatement preparedStatementPopulated = ingredienstTransformer.transform(preparedStatement, ingredients);
            preparedStatementPopulated.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    @Override
    public void delete(Connection connection, Integer id) {
        String deleteSQL = "DELETE FROM ingredients WHERE id_ingredients = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            PreparedStatement preparedStatementPopulated = ingredienstTransformer.transformID(preparedStatement, id);
            preparedStatementPopulated.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
