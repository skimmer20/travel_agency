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
    private RoomType roomType;
    private boolean isWifi;
    private boolean isBreakfast;
    private double price;
    private Integer hotelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return isWifi == room.isWifi &&
                isBreakfast == room.isBreakfast &&
                Double.compare(room.price, price) == 0 &&
                Objects.equals(id, room.id) &&
                roomType == room.roomType &&
                Objects.equals(hotelId, room.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, isWifi, isBreakfast, price, hotelId);
    }
}
