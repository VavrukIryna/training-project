package com.epam.lab.transformer;

import com.epam.lab.model.Chars;
import com.epam.lab.model.SpillMethod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharsTransformer implements PrepareStatementTransformers<Chars> {

    public Chars transform(ResultSet resultSet) {
        Chars chars = new Chars();
        SpillMethod spillMethod = new SpillMethod();

        try {
            chars.setIdChars(resultSet.getInt("id_chars"));
            chars.setVolumeAlcoholFraction(resultSet.getDouble("volume_alcohol_fraction"));
            chars.setClearness(resultSet.getString("clearness"));
            chars.setFiltered(resultSet.getString("filtered"));
            chars.setNutritionalValue(resultSet.getString("nutritional_value"));

            spillMethod.setIdSpillMethod(resultSet.getInt("id_spill_method"));
            spillMethod.setSize(resultSet.getDouble("size_beer"));
            spillMethod.setMatherial(resultSet.getString("matherial"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        chars.setSpillMethod(spillMethod);

        return chars;
    }

    @Override
    public PreparedStatement transform(PreparedStatement preparedStatement, Chars chars) {
        try {

            preparedStatement.setInt(1, chars.getIdChars() != null ? chars.getIdChars() : 0);
            preparedStatement.setDouble(2, chars.getVolumeAlcoholFraction());
            preparedStatement.setString(3, chars.getClearness());
            preparedStatement.setString(4, chars.getFiltered());
            preparedStatement.setString(5, chars.getNutritionalValue());
            preparedStatement.setInt(6, chars.getSpillMethod().getIdSpillMethod());//chars

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
