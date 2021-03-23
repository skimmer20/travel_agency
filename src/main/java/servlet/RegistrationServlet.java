package servlet;

import entity.User;
import entity.enums.Role;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuriismac on 3/17/21.
 * @project travel_agency
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.userService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");

        if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()
                && !phoneNumber.isEmpty() && !password.isEmpty()) {
            userService.create(new User(firstName, lastName, Role.USER.toString(), phoneNumber, email, password));
        }
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Success");
        resp.sendRedirect("index");
    }
}
