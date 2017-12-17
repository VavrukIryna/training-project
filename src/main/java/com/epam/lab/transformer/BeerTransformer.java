package com.epam.lab.transformer;

import com.epam.lab.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeerTransformer implements PrepareStatementTransformers<Beer> {


    public Beer transform(ResultSet resultSet) {
        Beer beer = new Beer();

        try {
            beer = new Beer();
            beer.setIdBeer(resultSet.getInt("id_beer"));
            beer.setName(resultSet.getString("name_beer"));
            beer.setType(Type.valueOf(resultSet.getString("type_beer")));
            beer.setAi(resultSet.getString("ai"));
            beer.setManufacturer(resultSet.getString("manufacturer"));
            Ingredients ingredients = new Ingredients();
            ingredients.setIdIngredients(resultSet.getInt("id_ingredients"));
            ingredients.setWater(resultSet.getInt("water"));
            ingredients.setMalt(resultSet.getInt("malt"));
            ingredients.setHop(resultSet.getInt("hop"));
            ingredients.setSugar(resultSet.getInt("sugar"));
            beer.setIngredients(ingredients);

            Chars chars = new Chars();
            chars.setIdChars(resultSet.getInt("id_chars"));
            chars.setVolumeAlcoholFraction(resultSet.getDouble("volume_alcohol_fraction"));
            chars.setClearness(resultSet.getString("clearness"));
            chars.setFiltered(resultSet.getString("filtered"));
            chars.setNutritionalValue(resultSet.getString("nutritional_value"));

            SpillMethod spillMethod = new SpillMethod();
            spillMethod.setIdSpillMethod(resultSet.getInt("id_spill_method"));
            spillMethod.setSize(resultSet.getDouble("size_beer"));
            spillMethod.setMatherial(resultSet.getString("matherial"));
            chars.setSpillMethod(spillMethod);
            beer.setChars(chars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beer;
    }

    @Override
    public PreparedStatement transform(PreparedStatement preparedStatement, Beer beer) {
        try {
            preparedStatement.setInt(1, beer.getIdBeer() != null ? beer.getIdBeer() : 0);
            preparedStatement.setString(2, beer.getName());
            preparedStatement.setString(3, beer.getType().toString());
            preparedStatement.setString(4, beer.getAi());
            preparedStatement.setString(5, beer.getManufacturer());
            preparedStatement.setInt(6, beer.getIngredients().getIdIngredients());
            preparedStatement.setInt(7, beer.getChars().getIdChars());

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
