package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.entity.User;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    public static final String NOTES_TABLE = "note";
    public static final String NOTES_ID = "id";
    public static final String NOTES_USER_ID = "user_id";
    public static final String NOTES_USER_NAME = "user_name";
    public static final String NOTES_TITLE = "title";
    public static final String NOTES_TEXT = "text";
    public static final String NOTES_DATE_CREATED = "date_created";
    public static final String NOTES_DATE_LAST_EDITED = "date_last_edited";

    public List<Note> getNotesByUserID(long userid) {
        DataSource dataSource = new DataSource();
        List<Note> notes = new ArrayList<>();

        String query = "SELECT id, user_id, user_name, text, title, date_created, date_last_edited " +
                "FROM note " +
                "WHERE note.user_id=" + userid;

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        )
        {
            while (resultSet.next())  {
                Note note =  new Note(

                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("text"),
                        resultSet.getString("title"),
                        resultSet.getString("date_created"),
                        resultSet.getString("date_last_edited"));

                        notes.add(note);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public List<Note> getAllNotes() {
        DataSource dataSource = new DataSource();
        List<Note> AllNotes = new ArrayList<>();

        String query = "SELECT id, user_id, user_name, text, title, date_created, date_last_edited " +
                "FROM note ";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        )
        {
            while (resultSet.next())  {
                Note note =  new Note(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("text"),
                        resultSet.getString("title"),
                        resultSet.getString("date_created"),
                        resultSet.getString("date_last_edited")
                );
                AllNotes.add(note);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllNotes;
    }

    public void CreateNewNote(Long user_id, String user_name, String title, String text, String date_created, String date_last_edited) {
        String query = "INSERT INTO " + NOTES_TABLE + "(" + NOTES_USER_ID + "," + NOTES_USER_NAME + "," + NOTES_TITLE + "," + NOTES_TEXT + "," + NOTES_DATE_CREATED
                + "," + NOTES_DATE_LAST_EDITED + ")" + "VALUES(?,?,?,?,?,?)";

        DataSource dataSource = new DataSource();

        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, user_id);
            preparedStatement.setString(2, user_name);
            preparedStatement.setString(3, text);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, date_created);
            preparedStatement.setString(6,  date_last_edited);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void deleteNote(Long id) {

        DataSource dataSource = new DataSource();

        String query = "DROP ROWS " +
                " FROM note " +
                " WHERE note.id='" + id + "'";

        try (// get connection with our database
             Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        )
        {

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}