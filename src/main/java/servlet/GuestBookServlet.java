package servlet;

import database.GuestBookDao;
import domain.GuestBookBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/servlet")
public class GuestBookServlet extends HttpServlet {
    public GuestBookDao guestBookDao;

    @Override
    public void init() throws ServletException {
        System.out.println(Timestamp.from(Instant.now()) + ": Initializing connection");
        try{
            ServletContext context = getServletContext();
            Class.forName(context.getInitParameter("dbDriver"));
            guestBookDao = new GuestBookDao(context.getInitParameter("url"),context.getInitParameter("user"),context.getInitParameter("password"));
            System.out.println(Timestamp.from(Instant.now()) + ": The connection was established successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Timestamp.from(Instant.now()) + ": Server received doGet-request");
        HttpSession session = req.getSession();
        try{
            session.setAttribute("guestBookList", guestBookDao.getGuestBookItems());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/pages/guestbook.jsp").forward(req,resp);
        System.out.println(Timestamp.from(Instant.now()) + ": Server replied to the doGet-request");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Timestamp.from(Instant.now()) + ": Server received doPost-request");
        String nName = req.getParameter("Name");
        String nMessage = req.getParameter("Message");
        try {
            guestBookDao.addGuestBookItem(new GuestBookBean(new Date(),nName, nMessage));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(Timestamp.from(Instant.now()) + ": Server successfully handled the to doPost-request" );
        resp.sendRedirect("");
    }
}
