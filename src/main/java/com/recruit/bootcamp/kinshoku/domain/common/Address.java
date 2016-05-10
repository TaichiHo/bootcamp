package com.recruit.bootcamp.kinshoku.domain.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.recruit.bootcamp.kinshoku.utils.GeoJsonDeserializer;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

/**
 * Created by heyueheng on 5/9/16.
 */
@Document
public class Address {

    private String street, city, zip;

    // using the cusomized geojsondeserializer so that we can test this in integration tests!
    @NotNull
    @GeoSpatialIndexed(type = GEO_2DSPHERE)
    @JsonDeserialize(using = GeoJsonDeserializer.class)
    private GeoJsonPoint location;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("%s, %s %s", street, zip, city);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }
}
