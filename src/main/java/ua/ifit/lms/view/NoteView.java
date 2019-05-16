package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.dao.entity.User;
import java.util.List;
import java.util.stream.Collectors;

public class NoteView {

    public String getNote(String title, String text, String date_created) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String show_note = indexSingletonView.getShow_note();
        return show_note
                .replace("<!--### insert title here ### -->", title)
                .replace("<!--### insert text here ### -->", text)
                .replace("<!--### insert date_created here ### -->", date_created);
    }

    public String getNotesList(User user) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        NoteRepository noteRepository = new NoteRepository();

        String indBase = indexSingletonView.getIndexHtml();
        String Menu = indexSingletonView.getMenuHtml();
        String show_note = indexSingletonView.getShow_note();

        List<Note> notes = noteRepository.getNotesByUserID(user.getId());

        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", "<h1>Hello " + user.getName() + "</h1>" +
                        notes.stream()
                                .map(e -> getNote(e.getTitle(), e.getText(), e.getDate_created()))
                                .collect(Collectors.joining(" ")));
    }
}
