package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yuriismac on 3/22/21.
 * @project travel_agency
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        HttpSession session = req.getSession();
        if (session != null) {
            req.setAttribute("userLogged", "no");
            session.invalidate();
        }
        resp.sendRedirect("index");
    }
}
