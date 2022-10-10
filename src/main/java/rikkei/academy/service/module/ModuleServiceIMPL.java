package rikkei.academy.service.module;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.Module;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleServiceIMPL {
    private Connection connection = ConnectMySQL.getConnection();

    private static final String URL = "jdbc:mysql://localhost:3306/form_login_jv06";
    private static final String USER = "root";
    private static final String PASS = "haphamlathe";

    private static final String INSERT_MODULE_SQL = "INSERT INTO module" + " (name_module) VALUES " + "(?);";
    private static final String SELECT_MODULE_BY_ID = "select id,name_module from module where id =?";
    private static final String SELECT_MODULE_ALL = "select id,name_module from module";
    private static final String DELETE_MODULE_SQL = "delete from module WHERE id = ?";
    private static final String UPDATE_MODULE_SQL = "UPDATE module SET name_module = ? where id = ?";


    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public void insertModule(Module module) {
        System.out.println(INSERT_MODULE_SQL);
        try (Connection connection1 = getConnection();
             PreparedStatement preparedStatement = connection1.prepareStatement(INSERT_MODULE_SQL)) {
            preparedStatement.setString(1, module.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Module selectModule(int id) {
        Module modules = null;
        try (Connection connection1 = getConnection();
             PreparedStatement preparedStatement = connection1.prepareStatement(SELECT_MODULE_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rsu = preparedStatement.executeQuery();
            while (rsu.next()) {
                String name = rsu.getString("name_module");
                modules = new Module(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return modules;
    }

    public List<Module> selectAllModule() {
        List<Module> listModule = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MODULE_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name_module");
                listModule.add(new Module(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listModule;
    }

    public boolean deleteModule(int id) throws SQLException {
        boolean howDelete;
        try (Connection connection1 = getConnection();
             PreparedStatement pre = connection1.prepareStatement(DELETE_MODULE_SQL);) {
            pre.setInt(1, id);
            howDelete = pre.executeUpdate() > 0;
        }
        return howDelete;
    }

    public boolean updateModule(Module moDule) throws SQLException {
        boolean howUpdate;
        try (Connection connection1 = getConnection();
             PreparedStatement pre = connection1.prepareStatement(UPDATE_MODULE_SQL);) {
            pre.setString(1, moDule.getName());
            pre.setInt(2, moDule.getId());
            howUpdate = pre.executeUpdate() > 0;
        }
        return howUpdate;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable m : ex) {
            if (m instanceof SQLException) {
                m.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) m).getSQLState());
                System.err.println("Error Code: " + ((SQLException) m).getErrorCode());
                System.err.println("Message: " + m.getMessage());
                Throwable l = ex.getCause();
                while (l != null) {
                    System.out.println("Cause: " + l);
                    l = l.getCause();
                }
            }
        }
    }
}