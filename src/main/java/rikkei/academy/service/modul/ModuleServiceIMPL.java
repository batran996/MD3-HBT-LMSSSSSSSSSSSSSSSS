package rikkei.academy.service.modul;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleServiceIMPL implements IModuleService {
    private Connection connection = ConnectMySQL.getConnection();
    private static String LIST_MODULE = "SELECT * FROM module;";
    private static String LIST_MODULE_LOTRINH1 = "SELECT * FROM module WHERE id_lotrinh = 1 ;";
    private static String LIST_MODULE_LOTRINH2 = "SELECT * FROM module WHERE id_lotrinh = 2 ;";
    private static String MODULE_BY_ID = "SELECT * FROM module WHERE name = ?";

    @Override
    public List findAll() {
        List<Module> moduleList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(LIST_MODULE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_lotrinh = resultSet.getInt("id_lotrinh");
                String name = resultSet.getString("name");
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
    public List<Module> findAllLoTrinh1() {
        List<Module> moduleList1 = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(LIST_MODULE_LOTRINH1);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_lotrinh = resultSet.getInt("id_lotrinh");
                String name = resultSet.getString("name_module");
                Module module = new Module(id, id_lotrinh, name);
                moduleList1.add(module);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return moduleList1;
    }

}
