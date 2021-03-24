package servlet;

import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author yuriismac on 3/18/21.
 * @project travel_agency
 */
@WebServlet("/index")
public class MainServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.userService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null){
            req.setAttribute("userLogged", "no");
        }else {
            req.setAttribute("userLogged", "yes");
        }
        req.getRequestDispatcher("pages/index.jsp").forward(req, resp);
    }
}
