package service.impl;

import dao.HotelDao;
import dao.impl.HotelDaoImpl;
import entity.Hotel;
import service.HotelService;

import java.sql.Date;
import java.util.List;

/**
 * @author yuriismac on 3/6/21.
 * @project travel_agency
 */
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;
    private static HotelService hotelServiceImpl;

    public static HotelService getHotelService(){
        if (hotelServiceImpl == null){
            hotelServiceImpl = new HotelServiceImpl();
        }
        return hotelServiceImpl;
    }

    private HotelServiceImpl(){
        hotelDao = new HotelDaoImpl();
    }

    @Override
    public Hotel create(Hotel hotel) {
        return this.hotelDao.create(hotel);
    }

    @Override
    public Hotel read(Integer id) {
        return this.hotelDao.read(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return this.hotelDao.update(hotel);
    }

    @Override
    public void delete(Integer id) {
        this.hotelDao.delete(id);
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelDao.getAll();
    }

    @Override
    public List<Hotel> getByCountryAndCity(String country, String city) {
        return this.hotelDao.getByCountryAndCity(country, city);
    }

    @Override
    public List<Hotel> getHotelByDate(Integer id, Date date) {
        return this.hotelDao.getHotelByDate(id, date);
    }

    @Override
    public List<Hotel> getHotelByCityAndDate(String city, Date fromDate, Date toDate) {
        return this.hotelDao.getHotelByCityAndDate(city, fromDate, toDate);
    }
}


