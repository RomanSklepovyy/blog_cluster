package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    public static final String NOTES_TABLE = "note";
    public static final String NOTES_ID = "id";
    public static final String NOTES_USER_ID = "user_id";
    public static final String NOTES_TITLE = "title";
    public static final String NOTES_TEXT = "text";
    public static final String NOTES_DATE_CREATED = "date_created";
    public static final String NOTES_DATE_LAST_EDITED = "date_last_edited";

    public List<Note> getNotesByUserID(long userid) {
        DataSource dataSource = new DataSource();
        List<Note> notes = new ArrayList<>();

        String query = "SELECT id, user_id, text, title, date_created, date_last_edited " +
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
                        resultSet.getString("text"),
                        resultSet.getString("title"),
                        resultSet.getString("date_created"),
                        resultSet.getString("date_last_edited")
                );
                notes.add(note);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public void CreateNewNote(Long user_id, String text, String title, String date_created, String date_last_edited) {
        String query = "INSERT INTO" + NOTES_TABLE + "(" + NOTES_USER_ID + "," + NOTES_TEXT + "," + NOTES_TITLE + "," + NOTES_DATE_CREATED
                + "," + NOTES_DATE_LAST_EDITED + ")" + "VALUES(?,?,?,?,?)";

        DataSource dataSource = new DataSource();

        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, user_id);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, date_created);
            preparedStatement.setString(5, date_last_edited);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
    }

}