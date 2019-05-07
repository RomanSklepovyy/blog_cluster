package ua.ifit.lms.view;

import ua.ifit.lms.dao.entity.Note;

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

    public String newNoteCreated(Note note) {
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String indBase = indexSingletonView.getIndexHtml();
        String Note = indexSingletonView.getNoteHtml();
        return indBase.replace("<!--### insert html here ### -->", "Hello " + note.getTitle());
    }
}
