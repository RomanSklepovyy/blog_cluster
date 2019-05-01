package ua.ifit.lms.dao.repository;

import ua.ifit.lms.dao.entity.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NoteRepository {

    public Note getNoteByTitle(String title) {

        DataSource dataSource = new DataSource();

        String query = "SELECT id, user_id, title, text, date_created, date_last_entered" +
                "From note" + "Where note.title ='" + title +"'";

        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            )
        {
            if (resultSet.next()) {
                Note note = new Note(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getString("date_created"),
                        resultSet.getString("date_last_entered")
                );

                return note;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
