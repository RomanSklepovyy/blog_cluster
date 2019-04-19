package ua.ifit.lms.controller;
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

               out.println("<!doctype html>\n" +
                "<html lang=\"en\">" +
               "<meta charset=\"utf-8\">" +
               "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
                "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">" +
               "<head><title>MyServlet</title></head><body>");
       out.write("<h1>Hello Servlet World!</h1>");
       out.println("<script src=\"js/jquery-3.4.0.min.js\"></script>\n" +
               "    <script src=\"js/popper.min.js\"></script>\n" +
               "    <script src=\"js/bootstrap.min.js\"></script>" +
               "</body>");
        out.println("</html>");

        /*try {
            out.println("<html><head><title>MyServlet</title></head><body>");
            out.write("<H1>Hello Servlet World!</H1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        } */
    }
}