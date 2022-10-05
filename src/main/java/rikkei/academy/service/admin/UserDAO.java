package rikkei.academy.service.admin;

import rikkei.academy.model.Admin;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/form_login_jv06";
    private static final String USER = "root";
    private static final String PASS = "haphamlathe";

    private static final String INSERT_ADMIN_SQL = "INSERT INTO admin" + "  (name) VALUES " + " (?);";

    private static final String SELECT_ADMIN_BY_ID = "select id,name from admin where id =?";
    private static final String SELECT_ALL_ADMIN = "select * from admin";
    private static final String DELETE_ADMIN_SQL = "delete from admin where id = ?;";
    private static final String UPDATE_ADMIN_SQL = "update admin set name = ? where id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    //

    //
    public void insertAdmin(Admin admin) throws SQLException {
        System.out.println(INSERT_ADMIN_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
            preparedStatement.setString(1, admin.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Admin selectAdmin(int id) {
        Admin admin = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                admin = new Admin(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return admin;
    }


    public List<Admin> selectAllUsers() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMIN);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                adminList.add(new Admin(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return adminList;
    }


    public boolean deleteAdmin(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateAdmin(Admin admin) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_SQL);) {
            statement.setString(1, admin.getName());
            statement.setInt(2, admin.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
