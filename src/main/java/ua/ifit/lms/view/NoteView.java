package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.dao.entity.User;
import java.util.List;
import java.util.stream.Collectors;

public class NoteView {

    public String getNotePage() {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String Note = indexSingletonView.getNoteHtml();
        String Menu = indexSingletonView.getMenuHtml();
        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", Note);
    }

    /*public String newNoteCreated(Note note) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String Note = indexSingletonView.getNoteHtml();
        return indBase.replace("<!--### insert html here ### -->", "Hello " + note.getTitle());
    }*/

    public String getNotesList(User user) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        NoteRepository noteRepository = new NoteRepository();

        String indBase = indexSingletonView.getIndexHtml();
        String Menu = indexSingletonView.getMenuHtml();


        List<Note> notes = noteRepository.getNotesByUserID(user.getId());

        return indBase
                .replace("<!--### insert html here ### -->", Menu)
                .replace("<!--### insert html here ### -->", "<h1>Hello " + user.getName() + "</h1>" +
                        notes.stream()
                                .map(e -> "<div>" + e.getText() + "</div>")
                                .collect(Collectors.joining(" ")));
    }
}
