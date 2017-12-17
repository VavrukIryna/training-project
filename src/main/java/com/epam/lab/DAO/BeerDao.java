package com.epam.lab.DAO;

import com.epam.lab.model.Beer;
import com.epam.lab.model.Chars;
import com.epam.lab.model.Ingredients;
import com.epam.lab.transformer.BeerTransformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerDao implements CRUD<Beer> {
    private BeerTransformer beerTransformer;
    private CharsDao charsDao;
    private IngredientsDao ingredientsDao;

    public BeerDao() {
        this.beerTransformer = new BeerTransformer();
        this.charsDao = new CharsDao();
        this.ingredientsDao = new IngredientsDao();
    }

    @Override
    public Beer readOne(Connection connection, Integer id) {
        Beer beer = null;

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM beer WHERE id_beer='" + String.valueOf(id) + "';";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                beer = beerTransformer.transform(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beer;
    }


    @Override
    public List<Beer> readAll(Connection connection) {
        List<Beer> beerList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM beer " +
                    "JOIN ingredients ON beer.id_ingredients=ingredients.id_ingredients " +
                    "JOIN chars ON beer.id_chars=chars.id_chars " +
                    "JOIN spill_method ON chars.id_spill_method=spill_method.id_spill_method";

            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                beerList.add(beerTransformer.transform(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beerList;
    }


    @Override
    public Beer create(Connection connection, Beer beer) {
        // FK ingridients
        Ingredients ingredients = ingredientsDao.create(connection, beer.getIngredients());

        // FK Chars
        Chars chars = charsDao.create(connection, beer.getChars());

        // Add Id's
        beer.getIngredients().setIdIngredients(ingredients.getIdIngredients());
        beer.getChars().setIdChars(chars.getIdChars());

        String sqlQuery = "INSERT INTO Beer (id_beer,name_beer,type_beer,ai,manufacturer,id_ingredients,id_chars) VALUES(?, ?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            PreparedStatement preparedStatementPopulated = beerTransformer.transform(preparedStatement, beer);
            int rowsUpdated = preparedStatementPopulated.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Creating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatementPopulated.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    beer.setIdBeer(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beer;
    }


    @Override
    public Beer update(Connection connection, Beer beer) {
        Ingredients ingredients = ingredientsDao.create(connection, beer.getIngredients());
        Chars chars = charsDao.create(connection, beer.getChars());
        beer.getIngredients().setIdIngredients(ingredients.getIdIngredients());
        beer.getChars().setIdChars(chars.getIdChars());
        String sqlQuery = "UPDATE beer SET id_beer = ?, name_beer = ?, type_beer= ?, ai = ?, manufacturer=?,id_ingredients=?, id_chars=? WHERE id_beer = '"
                + String.valueOf(beer.getIdBeer()) + "'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            PreparedStatement preparedStatementPopulated = beerTransformer.transform(preparedStatement, beer);
            preparedStatementPopulated.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beer;
    }

    public void delete(Connection connection, Integer id) {
        String deleteSQL = "DELETE FROM beer WHERE id_beer = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            PreparedStatement preparedStatementPopulated = beerTransformer.transformID(preparedStatement, id);
            preparedStatementPopulated.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


