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

    private Integer id;
    private String name;
    private int rating;
    private String country;
    private String city;
    private String propertyType;
    private int roomCount;
    private int agencyId;

    public Hotel(String name, int rating, String country, String city, String propertyType, int roomCount, int agencyId) {
        this.name = name;
        this.rating = rating;
        this.country = country;
        this.city = city;
        this.propertyType = propertyType;
        this.roomCount = roomCount;
        this.agencyId = agencyId;
    }

    public Hotel(Integer id, String name, int rating, String country, String city, String propertyType, int roomCount) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.country = country;
        this.city = city;
        this.propertyType = propertyType;
        this.roomCount = roomCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return rating == hotel.rating &&
                roomCount == hotel.roomCount &&
                agencyId == hotel.agencyId &&
                Objects.equals(id, hotel.id) &&
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
