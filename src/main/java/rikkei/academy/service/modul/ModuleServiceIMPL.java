package rikkei.academy.service.modul;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static rikkei.academy.config.ConnectMySQL.getConnection;

public class ModuleServiceIMPL implements IModuleService {
    private Connection connection = getConnection();
    private static String LIST_MODULE = "SELECT * FROM module WHERE id_lotrinh = ?;";
    private static String MODULE_BY_ID = "SELECT * FROM module WHERE name = ?";

    /////
    private static final String INSERT_MODULE_SQL = "INSERT INTO module" + " (name_module) VALUES " + "(?);";
    private static final String SELECT_MODULE_BY_ID = "select id,name_module from module where id =?";
    private static final String SELECT_MODULE_ALL = "select id,name_module from module";
    private static final String DELETE_MODULE_SQL = "delete from module WHERE id = ?";
    private static final String UPDATE_MODULE_SQL = "UPDATE module SET name_module = ? where id = ?";


    @Override
    public List findAll() {
        List<Module> moduleList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(LIST_MODULE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_lotrinh = resultSet.getInt("id_lotrinh");
                String name = resultSet.getString("name_module");
                Module module = new Module(id, id_lotrinh, name);
                moduleList.add(module);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return moduleList;
    }

    @Override
    public Module findByName(String name) {
        Module module = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(MODULE_BY_ID);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_lotrinh = resultSet.getInt("id_lotrinh");
                module = new Module(id, id_lotrinh, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return module;
    }

    @Override
    public List<Module> findByLoTrinh(int idLoTrinh) {
        List<Module> moduleList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(LIST_MODULE);
            statement.setInt(1,idLoTrinh);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_lotrinh = resultSet.getInt("id_lotrinh");
                String name_module = resultSet.getString("name_module");
                Module module = new Module(id, id_lotrinh, name_module);
                moduleList.add(module);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return moduleList;
    }

    ////

//    public Connection getConnection() {
//        Connection con = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection(URL, USER, PASS);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return con;
//    }

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
