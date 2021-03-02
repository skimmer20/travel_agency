package entity;

import entity.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author yuriismac on 2/23/21.
 * @project travel_agency
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {

    private Long id;
    private String roomType;
    private boolean isWifi;
    private boolean isBreakfast;
    private double price;
    private Integer hotelId;

    public Room(Long id, String roomType, boolean isWifi, boolean isBreakfast, double price, Integer hotelId) {
        this.id = id;
        this.roomType = roomType;
        this.isWifi = isWifi;
        this.isBreakfast = isBreakfast;
        this.price = price;
        this.hotelId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return isWifi == room.isWifi &&
                isBreakfast == room.isBreakfast &&
                Double.compare(room.price, price) == 0 &&
                Objects.equals(id, room.id) &&
                Objects.equals(roomType, room.roomType) &&
                Objects.equals(hotelId, room.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, isWifi, isBreakfast, price, hotelId);
    }
}
