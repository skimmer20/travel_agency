package dao.impl;

import dao.HotelDao;
import entity.Hotel;

import java.util.Date;
import java.util.List;

/**
 * @author yuriismac on 2/24/21.
 * @project travel_agency
 */
public class HotelDaoImpl implements HotelDao {

    private static final String CREATE = "insert into hotel (`id`, `name`, `rating`,`country`, `city`, `property_type`,`room_count`, `agency_id`) values (?,?,?,?,?,?,?,?,?)"
    private static final String READ_BY_ID = "select * from hotel where id = ?";
    private static final String READ_ALL = "select * from hotel";
    private static final String UPDATE_BY_ID = "update hotel set name = ?, rating = ?, country = ?, city = ?, property_type = ?, room_count = ?  where id = ?";
    private static final String DELETE_BY_ID = "delete from hotel where id = ?";
    //private static final String READ_BY_COUNTRY_AND_CITY =

    @Override
    public Hotel create(Hotel hotel) {
        return null;
    }

    @Override
    public Hotel read(Long id) {
        return null;
    }

    @Override
    public Hotel update(Hotel hotel) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Hotel> getAll() {
        return null;
    }

    @Override
    public List<Hotel> getByCountryAndCity(String country, String city) {
        return null;
    }

    @Override
    public List<Hotel> getHotelByDate(Long id, Date date) {
        return null;
    }

    @Override
    public List<Hotel> getHotelByCityAndDate(String city, Date fromDate, Date toDate) {
        return null;
    }
}
