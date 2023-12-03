package com.travel.agency;

public enum Country {

    POLAND,
    UKRAINE,
    USA,
    GERMANY,
    NORWAY;

    public String getName() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }

}
