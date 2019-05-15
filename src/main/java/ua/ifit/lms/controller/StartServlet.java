package ua.ifit.lms.controller;
import ua.ifit.lms.dao.entity.Note;
import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.LoginView;
import ua.ifit.lms.view.NoteView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.*;

import static java.util.logging.Level.INFO;

/**
 * Start servlet
 */
@WebServlet(name = "Start", urlPatterns = {"/"})
public class StartServlet extends HttpServlet {

    private static Logger log = Logger.getLogger("controller.StartServlet"); // логування

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

        // get user credentials
        LoginView loginView = new LoginView();
        if (request.getParameter("email") != null &&
                request.getParameter("password") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // test repository
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserByEmailByPassword(email, password);
            // check if a user successfully logged in
            if (user != null) {
                log.info("Successfully logged in " + user.toString());
                response.sendRedirect("/notes/index");
            }

            out.println(loginView.getloginPage());
        } else {
            out.println(loginView.getloginPage());
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("html/");
        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();
        indexSingletonView.setPath(path);

        // log file config
        try {
            // set file where to pu logs data
            Handler fh = new FileHandler(getServletContext().getRealPath("/logs/app.log"));
            // create object of formatter
            fh.setFormatter(new SimpleFormatter());
            // assign formatter to logger class
            Logger.getLogger("").addHandler(fh);
            // add copy output to console
            Logger.getLogger("").addHandler(new ConsoleHandler());
            // all levels will be outputted
            Logger.getLogger("").setLevel(INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
