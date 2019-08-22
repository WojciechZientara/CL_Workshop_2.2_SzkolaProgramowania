package Dao;

import Db.Db;
import Model.Exercise;
import Model.User;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {

    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercise(title, description) VALUES (?, ?)";

    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercise WHERE id = ?";

    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercise SET title = ?, description = ?, where id = ?";

    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercise WHERE id = ?";

    private static final String FIND_ALL_EXERCISE_QUERY =
            "SELECT * FROM exercise";

    public Exercise create(Exercise exercise) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // to be modified

    public Exercise read(int exerciseId) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Exercise exercise) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int exerciseId) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Exercise[] addToArray(Exercise exercise, Exercise[] exercises) {
        Exercise[] tmpEx = Arrays.copyOf(exercises, exercises.length + 1);
        tmpEx[exercises.length] = exercise;
        return tmpEx;
    }

    public Exercise[] findAll() {
        try (Connection connection = Db.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXERCISE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise ex = new Exercise();
                ex.setId(resultSet.getInt("id"));
                ex.setTitle(resultSet.getString("title"));
                ex.setDescription(resultSet.getString("description"));
                exercises = addToArray(ex, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }



}
