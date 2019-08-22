package Dao;

import Db.Db;
import Model.User;
import Model.UserGroup;

import java.sql.*;
import java.util.Arrays;

public class UserGroupDao {

    private static final String CREATE_USERGROUP_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";

    private static final String READ_USERGROUP_QUERY =
            "SELECT * FROM user_group WHERE id = ?";

    private static final String UPDATE_USERGROUP_QUERY =
            "UPDATE user_group SET name = ? where id = ?";

    private static final String DELETE_USERGROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";

    private static final String FIND_ALL_USERGROUP_QUERY =
            "SELECT * FROM user_group";

    public UserGroup create(UserGroup group) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USERGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // to be modified

    public UserGroup read(int groupId) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USERGROUP_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup group = new UserGroup();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(UserGroup group) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERGROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (Connection connection = Db.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USERGROUP_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserGroup[] addToArray(UserGroup ug, UserGroup[] groups) {
        UserGroup[] tmpGroups = Arrays.copyOf(groups, groups.length + 1);
        tmpGroups[groups.length] = ug;
        return tmpGroups;
    }

    public UserGroup[] findAll() {
        try (Connection connection = Db.getConnection()) {
            UserGroup[] groups = new UserGroup[0];
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERGROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserGroup group = new UserGroup();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups = addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }



}
