package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

/**
 * @author yuriismac on 2/23/21.
 * @project travel_agency
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {

    private Integer id;
    private Date date;
    private Integer roomId;
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(date, booking.date) &&
                Objects.equals(roomId, booking.roomId) &&
                Objects.equals(userId, booking.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, roomId, userId);
    }
}
