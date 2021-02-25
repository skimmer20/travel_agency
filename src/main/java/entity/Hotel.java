package entity;

import entity.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author yuriismac on 2/22/21.
 * @project travel_agency
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel {

    private long id;
    private String name;
    private int rating;
    private String country;
    private String city;
    private String propertyType;
    private int roomCount;
    private int agencyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                rating == hotel.rating &&
                roomCount == hotel.roomCount &&
                agencyId == hotel.agencyId &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(country, hotel.country) &&
                Objects.equals(city, hotel.city) &&
                Objects.equals(propertyType, hotel.propertyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, country, city, propertyType, roomCount, agencyId);
    }
}
