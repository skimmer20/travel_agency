package service.impl;

import dao.RoomDao;
import dao.impl.RoomDaoImpl;
import entity.Room;
import service.RoomService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuriismac on 3/6/21.
 * @project travel_agency
 */
public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao;
    private static RoomService roomServiceImpl;

    public static RoomService roomService(){
        if (roomServiceImpl == null){
            roomServiceImpl = new RoomServiceImpl();
        }
        return roomServiceImpl;
    }

    private RoomServiceImpl (){
        roomDao = new RoomDaoImpl();
    }

    @Override
    public Room create(Room room) {
        return this.roomDao.create(room);
    }

    @Override
    public Room read(Integer id) {
        return this.roomDao.read(id);
    }

    @Override
    public Room update(Room room) {
        return this.roomDao.update(room);
    }

    @Override
    public void delete(Integer id) {
        this.roomDao.delete(id);
    }

    @Override
    public List<Room> getAll() {
        return this.roomDao.getAll();
    }

    @Override
    public List<Room> getRoomsByHotel(Integer hotelId) {
        return this.roomDao.getRoomsByHotel(hotelId);
    }

    @Override
    public List<Room> getRoomsByHotelAndDate(Integer hotelId, Date fromDate, Date toDate) {
        return this.roomDao.getRoomsByHotelAndDate(hotelId, fromDate, toDate);
    }

    @Override
    public Map<Integer, Integer> getRoomUsage(Integer hotelId) {
        return this.roomDao.getRoomUsage(hotelId);
    }

    @Override
    public Map<String, Integer> getClientCount() {
        return this.roomDao.getClientCount();
    }
}
