package com.epam.lab.transformer;

import com.epam.lab.model.SpillMethod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpillMethodTransformer implements PrepareStatementTransformers<SpillMethod> {
    public SpillMethod transform(ResultSet resultSet) {
        SpillMethod spillMethod = new SpillMethod();
        try {
            spillMethod.setIdSpillMethod(resultSet.getInt("id_spill_method"));
            spillMethod.setSize(resultSet.getDouble("size_beer"));
            spillMethod.setMatherial(resultSet.getString("matherial"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spillMethod;
    }

    @Override
    public PreparedStatement transform(PreparedStatement preparedStatement, SpillMethod spillMethod) {
        try {
            preparedStatement.setInt(1, spillMethod.getIdSpillMethod() != null ? spillMethod.getIdSpillMethod() : 0);
            preparedStatement.setDouble(2, spillMethod.getSize());
            preparedStatement.setString(3, spillMethod.getMatherial());
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
