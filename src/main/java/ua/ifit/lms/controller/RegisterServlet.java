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
 @WebServlet(name = "RegisterServlet", urlPatterns = {"/register/*"})

 public class RegisterServlet extends HttpServlet{
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     }

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         HttpSession session = request.getSession();
         IndexSingletonView indexSingletonView =IndexSingletonView.getInstance();
         String RegisterForm = indexSingletonView.getRegisterForm();
         RegisterView registerView = new RegisterView();

         out.println(registerView.getRegisterForm());

         if (request.getParameter("email")!=null && request.getParameter("password")!=null && request.getParameter("name") !=null) {

             java.util.Date dt = new java.util.Date();
             java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             String date_created = sdf.format(dt);
             String date_last_entered = sdf.format(dt);

             User user = new User(0L, request.getParameter("email"), request.getParameter("password"), request.getParameter("name"), date_created, date_last_entered);

             UserRepository userRepository = new UserRepository();
             userRepository.saveUser(user);
             response.sendRedirect("/users/");
         }

     }
 }



