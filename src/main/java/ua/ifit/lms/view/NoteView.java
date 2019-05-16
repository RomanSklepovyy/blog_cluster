package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.dao.entity.User;
import java.util.List;
import java.util.stream.Collectors;


public class NoteView {

    public String getGreetingForm(String name) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String Greeting = indexSingletonView.getGreeting();

        return Greeting
                .replace("<!--### insert user mame here ### -->", name);
    }

    public String getNoteForm() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String NoteForm = indexSingletonView.getNoteHtml();
        String indBase = indexSingletonView.getIndexHtml();
        String Menu = indexSingletonView.getMenuHtml();

        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", NoteForm);
    }


    public String getNote(String title, String text, String date_created) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String show_note = indexSingletonView.getShow_note();
        return show_note
                .replace("<!--### insert title here ### -->", title)
                .replace("<!--### insert text here ### -->", text)
                .replace("<!--### insert date_created here ### -->", date_created);
    }

    public String getUserNotesList(User user) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        NoteRepository noteRepository = new NoteRepository();

        String indBase = indexSingletonView.getIndexHtml();
        String Menu = indexSingletonView.getMenuHtml();
        String show_note = indexSingletonView.getShow_note();
        String NoteForm = indexSingletonView.getNoteHtml();

        List<Note> notes = noteRepository.getNotesByUserID(user.getId());

        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", getGreetingForm(user.getName()))
                .replace("<!--### insert html here ### -->",NoteForm)
                .replace("<!--### insert html here ### -->",
                        notes.stream()
                                .map(e -> getNote(e.getTitle(), e.getText(), e.getDate_created()))
                                .collect(Collectors.joining(" ")));
    }
}
