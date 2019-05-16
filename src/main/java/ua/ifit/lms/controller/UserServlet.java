package ua.ifit.lms.controller;

import ua.ifit.lms.dao.entity.User;
import ua.ifit.lms.dao.repository.NoteRepository;
import ua.ifit.lms.dao.repository.UserRepository;
import ua.ifit.lms.view.IndexSingletonView;
import ua.ifit.lms.view.LoginView;
import ua.ifit.lms.view.RegisterView;
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

        UserRepository userRepository = new UserRepository();
        LoginView loginView = new LoginView();

        switch (request.getPathInfo()) {

            case "/login/":

                if (request.getParameter("email") != null &&
                    request.getParameter("password") != null) {

                String email = request.getParameter("email");
                String password = request.getParameter("password");

                User user = userRepository.getUserByEmailByPassword(email, password);

                if (user != null) {
                    session.setAttribute("user", user);
                    response.sendRedirect("/notes/index");
                } else {
                    out.println(loginView.getloginPage(false));
                }

                } else {
                out.println(loginView.getloginPage(true));
                }
            break;

            case "/users/logout/":
                session.removeAttribute("user");
                session.invalidate();
                response.sendRedirect("/users/login/");
             break;

             default:
                 response.sendRedirect("/");
        }
    }

}

