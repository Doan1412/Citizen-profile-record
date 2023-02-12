package com.example.pbl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@Entity
@Data
@TableGenerator(name="location", initialValue=1, allocationSize=1)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long location_id;
    private String quarter; // khu pho
    private String town; //Xa
    private String district; //Huyen
    private String city; //Tinh
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JsonBackReference
    private Collection<Citizen> persons;
    public Location(){}

    public Location(String quarter, String town, String district, String city, Collection<Citizen> persons) {
        this.quarter = quarter;
        this.town = town;
        this.district = district;
        this.city = city;
        this.persons = persons;
    }

    public Location(String quarter, String town, String district, String city) {
        this.quarter = quarter;
        this.town = town;
        this.district = district;
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public Collection<Citizen> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Citizen> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "location{" +
                "location_id=" + location_id +
                ", quarter='" + quarter + '\'' +
                ", town='" + town + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", persons=" + persons +
                '}';
    }
}
