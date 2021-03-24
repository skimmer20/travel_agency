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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @author yuriismac on 3/23/21.
 * @project travel_agency
 */
@WebServlet("/hotelByCityAndDate")
public class SearchHotelByCityAndDateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");
        java.sql.Date fromDateSql = dateInSql(fromDate);
        java.sql.Date toDateSql = dateInSql(toDate);


        HotelService hotelService = HotelServiceImpl.getHotelService();
        List<Hotel> hotelList = hotelService.getHotelByCityAndDate(city, fromDateSql, toDateSql);

        req.setAttribute("fromDate", fromDate);
        req.setAttribute("toDate", toDate);
        req.setAttribute("hotelList", hotelList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);

        Integer user_id = (Integer) req.getSession().getAttribute("userId");
        if (user_id == null){
            req.setAttribute("userLogged", "no");
        }else {
            req.setAttribute("userLogged", "yes");
        }
        req.getRequestDispatcher("pages/resultSearch.jsp").forward(req, resp);
    }

    private static java.sql.Date dateInSql(String dateSql) {
        java.sql.Date date = null;
        try {
            Date startDate = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH).parse(dateSql);
            date = new java.sql.Date(startDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
