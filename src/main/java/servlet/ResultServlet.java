package servlet;

import entity.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yuriismac on 3/24/21.
 * @project travel_agency
 */
@WebServlet("/resultSearch")
public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hotel> hotelList = (List<Hotel>) req.getSession().getAttribute("hotelList");
        req.setAttribute("hotelList", hotelList);
        req.getRequestDispatcher("pages/resultSearch,jsp").forward(req, resp);

    }
}
