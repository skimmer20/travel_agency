package dao;

import abstr.AbstractCrudOperation;
import entity.Room;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/1/21.
 * @project travel_agency
 */
public interface RoomDao extends AbstractCrudOperation<Room> {

    List<Room> getRoomsByHotel(Long hotelId);

    List<Room> getRoomsByHotelAndDate(Long hotelId, Date fromDate, Date toDate);

    Map<Long, Long> getRoomUsage(Long hotelId);

    Map<String, Long> getClientCount();


}
