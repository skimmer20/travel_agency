package dao.impl;

import connect.ConnectionManager;
import dao.RoomDao;
import entity.Room;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/1/21.
 * @project travel_agency
 */
public class RoomDaoImpl implements RoomDao {

    private static final String CREATE = "insert into room (`id`, `wifi`, `room_type`, `breakfast`, `price`, `hotel_id`) values (?,?,?,?,?,?)";
    private static final String READ_BY_ID = "select * from room where id = ? ";
    private static final String READ_ALL = "select * from room ";
    private static final String UPDATE_BY_ID = "update room set wifi = ?, room_type = ?, breakfast = ?, price = ? where id = ?";
    private static final String DELETE_BY_ID = "delete from room where id = ?";
    private static final String READ_ROOMS_BY_HOTEL = "select * from room where room.hotel_id = ?";
    private static final String READ_ROOMS_BY_HOTEL_AND_DATE = "select * from room " +
            "left join booking on booking.room_id = room.id and booking.date between ? and ? " +
            "where room.hotel_id = ? and booking.room_id is null and booking.date between ? and ? GROUP BY room_id";
    private static final String READ_ROOM_USAGE = "select room_id, COUNT(room_id) as room_usage from room " +
            "left join booking b on room.id = b.room_id where hotel_id = ? and b.date between ? and ? GROUP BY room_id";
    private static final String READ_CLIENT_COUNT = "select hotel_id, count(distinct room_id) as client_count from booking " +
            "inner join room r on booking.room_id = r.id " +
            "inner join hotel h on r.hotel_id = h.id " +
            "group by room_id";

    @Override
    public Room create(Room room) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, room.isWifi());
            preparedStatement.setString(2, room.getRoomType());
            preparedStatement.setBoolean(3, room.isBreakfast());
            preparedStatement.setDouble(4, room.getPrice());
            preparedStatement.setInt(5, room.getHotelId());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    room.setId(resultSet.getInt(1));
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public Room read(Integer id) {
        Room room = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer roomId = resultSet.getInt("id");
                String roomType = resultSet.getString("room_type");
                boolean wifi = resultSet.getBoolean("wifi");
                boolean breakfast = resultSet.getBoolean("breakfast");
                double price = resultSet.getDouble("price");
                Integer hotelId = resultSet.getInt("hotel_id");
                room = new Room(roomId, roomType, wifi, breakfast, price, hotelId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public Room update(Room room) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setBoolean(1, room.isWifi());
            preparedStatement.setString(2, room.getRoomType());
            preparedStatement.setBoolean(3, room.isBreakfast());
            preparedStatement.setDouble(4, room.getPrice());
            preparedStatement.setInt(5, room.getHotelId());
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer roomId = resultSet.getInt("id");
                String roomType = resultSet.getString("room_type");
                boolean wifi = resultSet.getBoolean("wifi");
                boolean breakfast = resultSet.getBoolean("breakfast");
                double price = resultSet.getDouble("price");
                Integer hotelId = resultSet.getInt("hotel_id");
                roomList.add(new Room(roomId, roomType, wifi, breakfast, price, hotelId));
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public List<Room> getRoomsByHotel(Integer hotelId) {
        List<Room> roomList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ROOMS_BY_HOTEL)) {
            preparedStatement.setLong(1, hotelId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer roomId = resultSet.getInt("id");
                    String roomType = resultSet.getString("room_type");
                    boolean wifi = resultSet.getBoolean("wifi");
                    boolean breakfast = resultSet.getBoolean("breakfast");
                    double price = resultSet.getDouble("price");
                    Integer _hotelId = resultSet.getInt("hotel_id");
                    roomList.add(new Room(roomId, roomType, wifi, breakfast, price, _hotelId));
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public List<Room> getRoomsByHotelAndDate(Integer hotelId, Date fromDate, Date toDate) {
        List<Room> roomList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ROOMS_BY_HOTEL_AND_DATE)) {
            preparedStatement.setDate(1, fromDate);
            preparedStatement.setDate(2, toDate);
            preparedStatement.setLong(3, hotelId);
            preparedStatement.setDate(4, fromDate);
            preparedStatement.setDate(5, toDate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer roomId = resultSet.getInt("id");
                    String roomType = resultSet.getString("room_type");
                    boolean wifi = resultSet.getBoolean("wifi");
                    boolean breakfast = resultSet.getBoolean("breakfast");
                    double price = resultSet.getDouble("price");
                    Integer _hotelId = resultSet.getInt("hotel_id");
                    roomList.add(new Room(roomId, roomType, wifi, breakfast, price, _hotelId));
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public Map<Integer, Integer> getRoomUsage(Integer hotelId) {////////??????
        Map<Integer, Integer> mapRoomUsage = new HashMap<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ROOM_USAGE)){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapRoomUsage;
    }

    @Override
    public Map<String, Integer> getClientCount() {
        Map<String, Integer> bookedCount = new HashMap<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_CLIENT_COUNT)){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    String hotelName = resultSet.getString("name");
                    Integer userId = resultSet.getInt("clientsCount");
                    bookedCount.put(hotelName, userId);
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedCount;
    }
}
