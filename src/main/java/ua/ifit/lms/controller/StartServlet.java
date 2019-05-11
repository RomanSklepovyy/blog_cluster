package ua.ifit.lms.controller;
import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.NoteView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Start servlet
 */
@WebServlet(name = "Start", urlPatterns = {"/"})
public class StartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();

        String Menu = indexSingletonView.getMenuHtml();

        out.println(indexSingletonView.getIndexHtml()
                    .replace("<!--### insert html here ### -->", Menu));

        /*NoteView noteView = new NoteView();

        if (request.getParameter("title") != null &&
                request.getParameter("text") != null) {

            String title = request.getParameter("title");
            String text = request.getParameter("text");

            NoteRepository noteRepository = new NoteRepository();
            Note note = noteRepository.getNoteByTitle(title);
            out.println(noteView.newNoteCreated(note));
        } else {
            out.println(noteView.getNotePage());
        } */
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("html/");
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        indexSingletonView.setPath(path);
    }
}
