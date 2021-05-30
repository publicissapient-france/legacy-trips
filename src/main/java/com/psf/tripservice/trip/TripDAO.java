package com.psf.tripservice.trip;

import com.psf.tripservice.exception.CollaboratorCallException;
import com.psf.tripservice.user.User;

import java.util.List;

public class TripDAO {
    public List<Trip> findTripsByUser(User user) {
        throw new CollaboratorCallException(
                "TripDAO should not be invoked on an unit test.");
    }
}