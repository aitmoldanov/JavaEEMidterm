package DAO;

import Model.Admin;
import Model.Product;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class DAO{
    private static final String url = "jdbc:postgresql://localhost:5433/JavaEEMid";
    private static final String user = "admin";
    private static final String password = "admin";

    public static boolean getAdminAuthenticate(String adminUsername, String adminPassword) {
        ArrayList<Admin> admins = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM admins");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String username = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    Admin admin = new Admin(id, username, password);
                    admins.add(admin);
                }
            }
            for (Admin admin: admins){
                if (admin.getAdminUsername().equals(adminUsername)
                        && admin.getAdminPassword().equals(adminPassword)){
                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public static boolean getUserAuthenticate(String username, String userPassword) {
        ArrayList<User> users = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String user_username = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String user_email = resultSet.getString(4);
                    User user = new User(id, user_username, password);
                    users.add(user);
                }
            }
            for (User user: users){
                if (user.getUserName().equals(username)
                        && user.getUserPassword().equals(userPassword)){
                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public static boolean addNewUser(User newUser) {
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "INSERT INTO users (username, user_password) Values (?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, newUser.getUserName());
                    preparedStatement.setString(2, newUser.getUserPassword());
                    preparedStatement.executeUpdate();

                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }


    public ArrayList<Product> fetch() {

        ArrayList<Product> products = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    double price = resultSet.getInt(3);
                    Product product = new Product(id, name, price);
                    products.add(product);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return products;
    }
    public Product fetchSingle(int id) {

        Product product = null;
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM movies WHERE movie_id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int movId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        double price = resultSet.getInt(3);
                        product = new Product(movId, name, price);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return product;
    }

    public boolean insert(Product product) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "INSERT INTO movies (movie_name, movie_price, movie_year) Values (?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, product.getProductName());
                    preparedStatement.setDouble(2, product.getProductPrice());
                    preparedStatement.executeUpdate();

                    return true;
//                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public boolean update(Product product) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "UPDATE movies SET movie_name = ?, movie_price = ?, movie_year = ? WHERE movie_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, product.getProductName());
                    preparedStatement.setDouble(2, product.getProductPrice());
                    preparedStatement.setInt(4, product.getProductId());
                    preparedStatement.executeUpdate();

                    return true;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public boolean delete(int id) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "DELETE FROM movies WHERE movie_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                    return true;
//                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public User fetchUser(String user_email) {
        User user1 = null;
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM users WHERE user_email=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user_email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int userId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String userPassword = resultSet.getString(3);
                        user1 = new User(userId, name, userPassword);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return user1;
    }
}
