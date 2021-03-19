package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yuriismac on 3/18/21.
 * @project travel_agency
 */
@WebServlet("/index")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer user_id = (Integer) req.getSession().getAttribute("userId");
//        if (user_id == null){
//            req.setAttribute("userLogged", "no");
//        }else {
//            req.setAttribute("userLogged", "yes");
//        }
        req.getRequestDispatcher("WEB-INF/pages/index.jsp").forward(req, resp);
        PrintWriter pw = resp.getWriter();
        pw.println("<h2>Hello from Me </h2>");
        pw.close();
    }
}
