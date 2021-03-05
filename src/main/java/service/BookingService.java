package service;

import abstr.AbstractCrudOperation;
import entity.Booking;

import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/4/21.
 * @project travel_agency
 */
public interface BookingService extends AbstractCrudOperation<Booking> {

    List<Booking> getByRoom(Integer id);

    List<Booking> getByUser(Integer id);

    void createBooking(List<Booking> bookingList);

    Map<String, List<Integer>> getAllBookedRoomsByHotel();
}
