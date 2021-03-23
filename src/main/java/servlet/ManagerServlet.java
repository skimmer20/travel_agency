package servlet;

import entity.Hotel;
import service.HotelService;
import service.impl.HotelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yuriismac on 3/21/21.
 * @project travel_agency
 */
@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {

    private HotelService hotelService = HotelServiceImpl.getHotelService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hotel> hotels = hotelService.getAll();
        req.setAttribute("hotels", hotels);
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null) {
            req.setAttribute("userLogged", "no");
        } else req.setAttribute("userLogged", "yes");
        req.getRequestDispatcher("pages/manager.jsp").forward(req, resp);
    }
}
