package service.impl;

import dao.BookingDao;
import dao.impl.BookingDaoImpl;
import entity.Booking;
import service.BookingService;

import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/6/21.
 * @project travel_agency
 */
public class BookingServiceImpl implements BookingService {

    private BookingDao bookingDao;
    private static BookingService bookingServiceImpl;

    public static BookingService bookingService(){
        if (bookingServiceImpl == null){
            bookingServiceImpl = new BookingServiceImpl();
        }
        return bookingServiceImpl;
    }

    private BookingServiceImpl(){
        bookingDao = new BookingDaoImpl();
    }
    @Override
    public Booking create(Booking booking) {
        return this.bookingDao.create(booking);
    }

    @Override
    public Booking read(Integer id) {
        return this.bookingDao.read(id);
    }

    @Override
    public Booking update(Booking booking) {
        return this.bookingDao.update(booking);
    }

    @Override
    public void delete(Integer id) {
        this.bookingDao.delete(id);
    }

    @Override
    public List<Booking> getAll() {
        return this.bookingDao.getAll();
    }

    @Override
    public List<Booking> getByRoom(Integer id) {
        return this.bookingDao.getByRoom(id);
    }

    @Override
    public List<Booking> getByUser(Integer id) {
        return this.bookingDao.getByUser(id);
    }

    @Override
    public void createBooking(List<Booking> bookingList) {
        this.bookingDao.createBooking(bookingList);
    }

    @Override
    public Map<String, List<Integer>> getAllBookedRoomsByHotel() {
        return this.bookingDao.getAllBookedRoomsByHotel();
    }
}
