package dao.impl;

import connect.ConnectionManager;
import dao.HotelDao;
import entity.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuriismac on 2/24/21.
 * @project travel_agency
 */
public class HotelDaoImpl implements HotelDao {

    private static final String CREATE = "insert into hotel (`id`, `name`, `rating`,`country`, `city`, `property_type`,`room_count`, `agency_id`) values (?,?,?,?,?,?,?,?,?)";
    private static final String READ_BY_ID = "select * from hotel where id = ?";
    private static final String READ_ALL = "select * from hotel";
    private static final String UPDATE_BY_ID = "update hotel set name = ?, rating = ?, country = ?, city = ?, property_type = ?, room_count = ?  where id = ?";
    private static final String DELETE_BY_ID = "delete from hotel where id = ?";
    private static final String READ_BY_COUNTRY_AND_CITY = "select * from hotel where hotel.country = ? and hotel.city = ?";
    private static final String READ_BY_DATE = "select distinct hotel_id, hotel.name, hotel.rating, hotel.country, hotel.city, hotel.property_type," +
            "hotel.room_count, hotel.agency_id from hotel " +
            "inner join room on room.hotel_id = hotel_id " +
            "left join booking on booking.room_id = room_id AND booking.date = ? " +
            "where hotel_id = ? and booking.room_id is NULL"; // Чи можна забронювати готель на певну дату (чи є вільні номери на певну дату)
    private static final String READ_BY_CITY_AND_DATE = "select distinct hotel_id, hotel.name, hotel.rating, hotel.country, hotel.city, " +
            "hotel.property_type, hotel.room_count, hotel.agency_id from hotel inner join room on room.hotel_id = hotel_id " +
            "left join booking on booking.room_id = room_id and booking.date between ? and ? " +
            "where hotel.city = ? and booking.room_id is null and booking.date between ? and ? is null"; // Подивитись всі доступні готелі у певному місті на певну дату

    @Override
    public Hotel create(Hotel hotel) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setLong(2, hotel.getRating());
            preparedStatement.setString(3, hotel.getCountry());
            preparedStatement.setString(4, hotel.getCity());
            preparedStatement.setString(5, hotel.getPropertyType());
            preparedStatement.setInt(6, hotel.getRoomCount());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    hotel.setId(resultSet.getLong(1));
                }
                ConnectionManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    @Override
    public Hotel read(Long id) {
        Hotel hotel = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long hotelId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int rating = resultSet.getInt("rating");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String propertyType = resultSet.getString("property_type");
                int roomCount = resultSet.getInt("room_count");
                hotel = new Hotel(hotelId, name, rating, country, city, propertyType, roomCount);
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setInt(2, hotel.getRating());
            preparedStatement.setString(3, hotel.getCountry());
            preparedStatement.setString(4, hotel.getCity());
            preparedStatement.setString(5, hotel.getPropertyType());
            preparedStatement.setInt(6, hotel.getRoomCount());
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> hotelList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long hotelId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int rating = resultSet.getInt("rating");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String propertyType = resultSet.getString("property_type");
                int roomCount = resultSet.getInt("room_count");
                hotelList.add(new Hotel(hotelId, name, rating, country, city, propertyType, roomCount));
                preparedStatement.executeUpdate();
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    @Override
    public List<Hotel> getByCountryAndCity(String country, String city) {
        List<Hotel> hotelList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_COUNTRY_AND_CITY)) {
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, city);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long hotelId = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    int rating = resultSet.getInt("rating");
                    String countryL = resultSet.getString("country");
                    String cityL = resultSet.getString("city");
                    String propertyType = resultSet.getString("property_type");
                    int roomCount = resultSet.getInt("room_count");
                    hotelList.add(new Hotel(hotelId, name, rating, countryL, cityL, propertyType, roomCount));
                    preparedStatement.executeUpdate();
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    @Override
    public List<Hotel> getHotelByDate(Long id, Date date) {
        List<Hotel> hotelList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_DATE)) {
            preparedStatement.setDate(1, (java.sql.Date) date);
            preparedStatement.setLong(2, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long hotelId = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    int rating = resultSet.getInt("rating");
                    String country = resultSet.getString("country");
                    String city = resultSet.getString("city");
                    String propertyType = resultSet.getString("property_type");
                    int roomCount = resultSet.getInt("room_count");
                    hotelList.add(new Hotel(hotelId, name, rating, country, city, propertyType, roomCount));
                    preparedStatement.executeUpdate();
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    @Override
    public List<Hotel> getHotelByCityAndDate(String city, Date fromDate, Date toDate) {
        List<Hotel> hotelList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_CITY_AND_DATE)) {
            preparedStatement.setDate(1, (java.sql.Date) fromDate);
            preparedStatement.setDate(2, (java.sql.Date) toDate);
            preparedStatement.setString(3, city);
            preparedStatement.setDate(4, (java.sql.Date) fromDate);
            preparedStatement.setDate(5, (java.sql.Date) toDate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long hotelId = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    int rating = resultSet.getInt("rating");
                    String country = resultSet.getString("country");
                    String cityL = resultSet.getString("city");
                    String propertyType = resultSet.getString("property_type");
                    int roomCount = resultSet.getInt("room_count");
                    hotelList.add(new Hotel(hotelId, name, rating, country, cityL, propertyType, roomCount));
                    preparedStatement.executeUpdate();
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
}
