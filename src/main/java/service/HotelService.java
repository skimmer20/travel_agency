package service;

import abstr.AbstractCrudOperation;
import entity.Hotel;

import java.sql.Date;
import java.util.List;

/**
 * @author yuriismac on 3/4/21.
 * @project travel_agency
 */
public interface HotelService extends AbstractCrudOperation<Hotel> {

    List<Hotel> getByCountryAndCity(String country, String city);

    List<Hotel> getHotelByDate(Integer id, Date date);

    List<Hotel> getHotelByCityAndDate(String city, Date fromDate, Date toDate);
}
