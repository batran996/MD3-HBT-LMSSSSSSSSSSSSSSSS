package rikkei.academy.service.baidoc;

import rikkei.academy.model.BaiDoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaiDocService {
    private static final String URL = "jdbc:mysql://localhost:3306/form_login_jv06";
    private static final String USER = "root";
    private static final String PASS = "haphamlathe";

    private static final String INSERT_BAIDOC_SQL = "INSERT INTO baidoc" + " (name) VALUES " + "(?);";


    private static final String SELECT_BAIDOC_BY_ID = "select id,name from baidoc where id =?";

    private static final String SELECT_ALL_BAIDOC = "select * from baidoc";

    private static final String DELETE_BAIDOC_SQL = "delete from baidoc where id = ?;";

    private static final String UPDATE_BAIDOC_SQL = "update baidoc set name = ? where id = ?;";

    public Connection getConnection() {
        Connection collection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            collection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return collection;
    }

    public void insertBaiDoc(BaiDoc baiDoc) throws SQLException {
        System.out.println(INSERT_BAIDOC_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BAIDOC_SQL)) {
            preparedStatement.setString(1, baiDoc.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public BaiDoc selectLoTrinh(int id) {
        BaiDoc baiDoc = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BAIDOC_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                baiDoc = new BaiDoc(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return baiDoc;
    }

    public List<BaiDoc> selectAllBaiDoc() {
        List<BaiDoc> baiDocList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BAIDOC);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                baiDocList.add(new BaiDoc(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return baiDocList;
    }

    public boolean deleteBaiDoc(int id) throws SQLException {
        boolean howDelete;
        try (Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement(DELETE_BAIDOC_SQL);){
                pr.setInt(1, id);
                howDelete = pr.executeUpdate() > 0;
        }
        return howDelete;
    }

    public boolean updateBaiDoc(BaiDoc baiDoc) throws SQLException {
        boolean howUpdate;
        try (Connection connection = getConnection();
             PreparedStatement pr = connection.prepareStatement(DELETE_BAIDOC_SQL);){
            pr.setString(1, baiDoc.getName());
            pr.setInt(2, baiDoc.getId());

            howUpdate = pr.executeUpdate() > 0;
        }
        return howUpdate;
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
