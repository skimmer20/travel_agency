package dao.impl;

import connect.ConnectionManager;
import dao.BookingDao;
import entity.Booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/2/21.
 * @project travel_agency
 */
public class BookingDaoImpl implements BookingDao {

    private static final String CREATE = "insert into booking (id, date, room_id, user_id) VALUES (?, ?, ?, ?)";
    private static final String READ_BY_ID = "select * from booking where id = ?";
    private static final String READ_ALL = "select * from booking";
    private static final String UPDATE_BY_ID = "update booking set date = ?, room_id = ?, user_id = ? where id = ?";
    private static final String DELETE_BY_ID = "delete from booking where id = ?";
    private static final String READ_BY_ROOM = "select * from booking where room_id = ?";
    private static final String READ_BY_USER = "select * from booking where user_id = ?";
    private static final String READ_BOOKED_DAYS_COUNT = "select hotel_id, count(booking.user_id) as reserve_count, " +
            "count(distinct room_id) as userCount from booking " +
            "inner join room r on booking.room_id = r.id " +
            "inner join hotel h on r.hotel_id = h.id " +
            "group by hotel_id";///???

    @Override
    public Booking create(Booking booking) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setDate(1, booking.getDate());
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setInt(3, booking.getUserId());
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public Booking read(Integer id) {
        Booking booking = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer bookingId = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                Integer roomdId = resultSet.getInt("room_id");
                Integer userId = resultSet.getInt("user_id");
                booking = new Booking(bookingId, date, roomdId, userId);
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public Booking update(Booking booking) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)){
            preparedStatement.setDate(1, booking.getDate());
            preparedStatement.setInt(2, booking.getRoomId());
            preparedStatement.setInt(3, booking.getUserId());
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> getAll() {
        List<Booking> bookingList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer bookingId = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                Integer roomdId = resultSet.getInt("room_id");
                Integer userId = resultSet.getInt("user_id");
                bookingList.add(new Booking(bookingId, date, roomdId, userId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    @Override
    public List<Booking> getByRoom(Integer id) {
        return null;
    }

    @Override
    public List<Booking> getByUser(Integer id) {
        return null;
    }

    @Override
    public void createBooking(List<Booking> bookingList) {

    }

    @Override
    public Map<String, List<Integer>> getAllBookedRoomsByHotel() {
        return null;
    }
}
