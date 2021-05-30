package com.psf.tripservice.trip;

import com.psf.tripservice.exception.UserNotLoggedInException;
import com.psf.tripservice.user.User;
import com.psf.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                return tripDAO.findTripsByUser(user);
            }
        }

        return new ArrayList<>();
    }
}
