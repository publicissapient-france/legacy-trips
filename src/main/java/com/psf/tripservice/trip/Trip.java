package com.psf.tripservice.trip;

public class Trip {

    private final String destination;

    private Trip(final String destination) {
        this.destination = destination;
    }

    public static Trip to(final String destination) {
        return new Trip(destination);
    }

}
