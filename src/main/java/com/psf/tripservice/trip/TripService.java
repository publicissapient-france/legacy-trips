package com.psf.tripservice.trip;

import com.psf.tripservice.exception.UserNotLoggedInException;
import com.psf.tripservice.user.User;
import com.psf.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<>();
        User loggedUser = getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = findTripsByUser(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        User loggedUser = UserSession.getInstance().getLoggedUser();
        return loggedUser;
    }

}
