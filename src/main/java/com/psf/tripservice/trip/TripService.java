package com.psf.tripservice.trip;

import com.psf.tripservice.exception.UserNotLoggedInException;
import com.psf.tripservice.user.User;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

    private final TripDAO tripDAO;

    public TripService(final TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getFriendTrips(final User friend, final User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
        return friend.isFriendOf(loggedUser) ? findTripsBy(friend) : noTrips();
    }

    protected List<Trip> findTripsBy(final User user) {
        return tripDAO.findTripsBy(user);
    }

    private static List<Trip> noTrips() {
        return emptyList();
    }

}
