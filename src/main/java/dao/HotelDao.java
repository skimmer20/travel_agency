package dao;

import abstr.AbstractCrudOperation;
import entity.Hotel;

import java.sql.Date;
import java.util.List;


/**
 * @author yuriismac on 2/24/21.
 * @project travel_agency
 */
public interface HotelDao extends AbstractCrudOperation<Hotel> {

    List<Hotel> getByCountryAndCity(String country, String city);

    List<Hotel> getHotelByDate(Long id, Date date);

    List<Hotel> getHotelByCityAndDate(String city, Date fromDate, Date toDate);
}
