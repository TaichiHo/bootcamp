package com.recruit.bootcamp.kinshoku.domain.common;

import lombok.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

/**
 * Created by heyueheng on 5/9/16.
 */
@Value
public class Address {

    String street, city, zip;
    @GeoSpatialIndexed(type = GEO_2DSPHERE)
    Point location;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("%s, %s %s", street, zip, city);
    }
}
