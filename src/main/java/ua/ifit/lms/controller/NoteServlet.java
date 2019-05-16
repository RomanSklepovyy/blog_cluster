package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.NoteView;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "NoteServlet", urlPatterns = {"/notes/*"})
public class NoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        String NoteForm = indexSingletonView.getNoteHtml();


        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/users/");
        }

        else {

            NoteView noteView = new NoteView();
            out.println(noteView.getUserNotesList(user));

            if (request.getParameter("title")!=null && request.getParameter("text")!=null) {

                String title = request.getParameter("title");
                String text = request.getParameter("text");

                long user_id = user.getId();

                java.util.Date dt = new java.util.Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date_created = sdf.format(dt);
                String date_last_edited = sdf.format(dt);

                NoteRepository noteRepository = new NoteRepository();
                noteRepository.CreateNewNote(user_id, text, title, date_created, date_last_edited );
                response.sendRedirect("/notes/");
            }
        }
    }
}
