package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.dao.entity.User;
import java.util.List;
import java.util.stream.Collectors;


public class NoteView {

    IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();

    NoteRepository noteRepository = new NoteRepository();
    String indBase = indexSingletonView.getIndexHtml();
    String Menu = indexSingletonView.getMenuHtml();
    String show_note = indexSingletonView.getShow_note();
    String NoteForm = indexSingletonView.getNoteHtml();
    String Greeting = indexSingletonView.getGreeting();

    public String getGreetingForm(String name) {

        return Greeting
                .replace("<!--### insert user mame here ### -->", name);
    }


    public String getNote(String title, String text, String date_created) {
        return show_note
                .replace("<!--### insert title here ### -->", text)
                .replace("<!--### insert text here ### -->", title)
                .replace("<!--### insert date_created here ### -->", date_created);
    }

    public String getUserNotesList(User user) {

        List<Note> notes = noteRepository.getNotesByUserID(user.getId());

        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", getGreetingForm(", " + user.getName()))
                .replace("<!--### insert html here ### -->",NoteForm)
                .replace("<!--### insert html here ### -->",
                        notes.stream()
                                .map(e -> getNote(e.getTitle(), e.getText(), e.getDate_created()))
                                .collect(Collectors.joining(" ")));
    }

    public String ShowAllNotes() {

        List<Note> AllNotes = noteRepository.getAllNotes();

        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", getGreetingForm(""))
                .replace("<!--### insert html here ### -->",NoteForm)
                .replace("<!--### insert html here ### -->",
                        AllNotes.stream()
                                .map(e -> getNote(e.getTitle(), e.getText(), e.getDate_created()))
                                .collect(Collectors.joining(" ")));
    }
}
