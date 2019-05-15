package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.LoginView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static ua.ifit.lms.dao.entity.User.*;

@WebServlet(name = "UserServlet", urlPatterns = {"/users/*"})
public class UserServlet<request> extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        LoginView loginView = new LoginView();

        IndexSingletonView indexSingletonView = IndexSingletonView.getInstance();


        if (request.getParameter("email") != null &&
                request.getParameter("password") != null) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserByEmailByPassword(email, password);

            // check if a user successfully logged in
            if (user != null) {
                session.setAttribute("user", user);
                response.sendRedirect("/notes/index");
            } else {
                out.println(indexSingletonView.getIndexHtml()
                        .replace("<!--### insert html here ### -->",
                                loginView.getloginPage(false)));
            }

        } else {
            out.println(indexSingletonView.getIndexHtml()
                    .replace("<!--### insert html here ### -->",
                            loginView.getloginPage(true)));
        }
        if (request.getParameter("name")!= null && request.getParameter("email")!=null && request.getParameter("password")!=null) {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date_created = sdf.format(dt);
            String date_last_entered = sdf.format(dt);

            UserRepository userRepository = new UserRepository();
            userRepository.signUpUser(  email, password, name, date_created, date_last_entered);
        }
    }

}
