package service;

import abstr.AbstractCrudOperation;
import entity.Room;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/4/21.
 * @project travel_agency
 */
public interface RoomService extends AbstractCrudOperation<Room> {

    List<Room> getRoomsByHotel(Integer hotelId);

    List<Room> getRoomsByHotelAndDate(Integer hotelId, Date fromDate, Date toDate);

    Map<Integer, Integer> getRoomUsage(Integer hotelId);

    Map<String, Integer> getClientCount();
}
