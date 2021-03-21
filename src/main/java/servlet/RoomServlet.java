package servlet;

import entity.Hotel;
import entity.Room;
import service.HotelService;
import service.RoomService;
import service.impl.HotelServiceImpl;
import service.impl.RoomServiceImpl;

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
@WebServlet("/addRooms")
public class RoomServlet extends HttpServlet {

    private RoomService roomService = RoomServiceImpl.roomService();
    private HotelService hotelService = HotelServiceImpl.getHotelService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/rooms.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomType = req.getParameter("roomType");
        boolean isWifi = req.getParameter("wifi").equals("true");
        boolean breakfast = req.getParameter("breakfast").equals("true");
        double price = Double.parseDouble(req.getParameter("price"));

        HttpSession session = req.getSession();
        Integer hotelId = (Integer) session.getAttribute("hotel_id");
        Room room = new Room(roomType, isWifi, breakfast, price, hotelId);
        Hotel hotel = hotelService.read(hotelId);
        Integer roomCount = hotel.getRoomCount();
        hotel.setRoomCount(++roomCount);
        hotelService.update(hotel);
        roomService.create(room);
        resp.sendRedirect("addRooms");

    }
}
