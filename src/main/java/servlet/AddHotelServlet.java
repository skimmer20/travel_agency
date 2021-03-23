package servlet;

import entity.Hotel;
import service.HotelService;
import service.impl.HotelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yuriismac on 3/21/21.
 * @project travel_agency
 */
@WebServlet("/add-hotel")
public class AddHotelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HotelService hotelService = HotelServiceImpl.getHotelService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/add-hotel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer rating = Integer.parseInt(req.getParameter("rating"));
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String propertyType = req.getParameter("propertyType");

        Hotel hotel = new Hotel(name, rating, country, city, propertyType, 0, 1);
        hotelService.create(hotel);
        HttpSession session = req.getSession();
        session.setAttribute("hotelId", hotel.getId());
        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null){
            req.setAttribute("userLogged", "no");
        }else {
            req.setAttribute("userLogged", "yes");
        }
        resp.sendRedirect("addRooms");
    }
}
