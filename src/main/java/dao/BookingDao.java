package dao;

import abstr.AbstractCrudOperation;
import entity.Booking;

import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/2/21.
 * @project travel_agency
 */
public interface BookingDao extends AbstractCrudOperation<Booking> {

    List<Booking> getByRoom(Integer id);

    List<Booking> getByUser(Integer id);

    void createBooking(List<Booking> bookingList);

    Map<String, List<Integer>> getAllBookedRoomsByHotel();
}
