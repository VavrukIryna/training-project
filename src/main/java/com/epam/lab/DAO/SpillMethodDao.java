package com.epam.lab.DAO;

import com.epam.lab.model.SpillMethod;
import com.epam.lab.transformer.SpillMethodTransformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpillMethodDao implements CRUD<SpillMethod> {

    private SpillMethodTransformer spillMethodTransformer;

    public SpillMethodDao() {
        this.spillMethodTransformer = new SpillMethodTransformer();
    }

    @Override
    public SpillMethod readOne(Connection connection, Integer id) {
        SpillMethod spillMethod = null;

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM spill_method WHERE id_spill_method='" + String.valueOf(id) + "';";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                spillMethod = spillMethodTransformer.transform(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spillMethod;
    }

    @Override
    public List<SpillMethod> readAll(Connection connection) {
        List<SpillMethod> spillMethodList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM spill_method";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                spillMethodList.add(spillMethodTransformer.transform(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spillMethodList;
    }

    @Override
    public SpillMethod create(Connection connection, SpillMethod spillMethod) {
        String sqlQuery = "INSERT INTO spill_method (id_spill_method, size_beer, matherial) VALUES(?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            PreparedStatement preparedStatementPopulated = spillMethodTransformer.transform(preparedStatement, spillMethod);
            int rowsUpdated = preparedStatementPopulated.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Creating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatementPopulated.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    spillMethod.setIdSpillMethod(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spillMethod;
    }


    public SpillMethod update(Connection connection, SpillMethod spillMethod) {
        String sqlQuery = "UPDATE spill_method SET id_spill_method = ?, size_beer = ?, matherial= ? WHERE id_spill_method = '"
                + String.valueOf(spillMethod.getIdSpillMethod()) + "'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            PreparedStatement preparedStatementPopulated = spillMethodTransformer.transform(preparedStatement, spillMethod);
            preparedStatementPopulated.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spillMethod;
    }


    public void delete(Connection connection, Integer id) {
        String deleteSQL = "DELETE FROM spill_method WHERE id_spill_method = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            PreparedStatement preparedStatementPopulated = spillMethodTransformer.transformID(preparedStatement, id);
            preparedStatementPopulated.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
