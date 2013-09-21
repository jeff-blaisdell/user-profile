package profile

import org.bson.types.ObjectId;

class Address {

    ObjectId id
    String line1
    String line2
    String line3
    String city
    String state
    String country
    String postalCode
    Map location

    static mapping = {
        compoundIndex country:1, state:1, city:1
        location geoIndex:true, indexAttributes:[min:-500, max:500]
    }

    static constraints = {
        line1 blank:true, nullable:true, maxSize:100
        line2 blank:true, nullable:true, maxSize:100
        line3 blank:true, nullable:true,  maxSize:100
        city blank:true, nullable:true,  maxSize:50
        state blank:true, nullable:true,  maxSize:50
        country blank:true, nullable:true,  maxSize:2
        postalCode blank:true, nullable:true,  maxSize:25
        location blank:true, nullable:true, maxSize:100
    }
}