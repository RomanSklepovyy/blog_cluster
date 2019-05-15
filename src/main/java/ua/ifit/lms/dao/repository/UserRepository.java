package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.User;

import java.sql.*;

public class UserRepository {



    public static final String USER_TABLE = "user";
  // public static final String USER_ID ="id";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_DATE_CREATED = "date_created";
    public static final String USER_DATELAST_ENTERED = "date_last_entered";

    public void signUpUser ( String email, String password, String name, String date_created, String date_last_entered){
        String insert = "INSERT INTO " + USER_TABLE +"("
                +","+USER_EMAIL+"" + ","
                +USER_PASSWORD+","+USER_NAME+","+
                USER_DATE_CREATED+ "," +USER_DATELAST_ENTERED+
                ")" + "VALUES(?,?,?,?,?)";

        DataSource dataSource = new DataSource();
        try{
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(insert);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, date_created);
            preparedStatement.setString(5, date_last_entered);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}

        }
    /**
     * Get User By Email and Password from User Table
     */
    public User getUserByEmailByPassword(String email, String password) {

        DataSource dataSource = new DataSource();

        String query = "SELECT id, email, password, name, date_created, date_last_entered" +
                " FROM user " +
                " WHERE user.email='" + email + "' AND password='" + password + "'";

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        )
        {
            if (resultSet.next())  {
                User user =  new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("date_created"),
                        resultSet.getString("date_last_entered")
                );

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
