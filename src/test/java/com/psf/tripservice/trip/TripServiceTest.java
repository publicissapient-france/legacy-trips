package com.psf.tripservice.trip;

import com.psf.tripservice.exception.UserNotLoggedInException;
import com.psf.tripservice.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TripServiceTest {
    @Test
    void should_throw_exception_when_user_not_logged_in() {
        // Given
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return null;
            }
        };
        // When
        // Then
        Assertions.assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(null);
        });
    }

    @Test
    void should_return_empty_trips_when_user_has_no_friends() {
        // Given
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return new User();
            }
        };
        // When
        List<Trip> tripsByUser = tripService.getTripsByUser(new User());
        // Then
        assertThat(tripsByUser).isEmpty();
    }

    @Test
    void should_return_empty_trips_when_user_has_friends() {
        // Given
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return new User();
            }
        };
        User user = new User();
        user.addFriend(new User());
        // When
        List<Trip> tripsByUser = tripService.getTripsByUser(user);
        // Then
        assertThat(tripsByUser).isEmpty();
    }

    @Test
    void should_return_trips_when_user_is_friend_with_loggedUser() {
        // Given
        User loggedUser = new User();
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }

            @Override
            protected List<Trip> findTripsByUser(User user) {
                return List.of(new Trip());
            }
        };
        User user = new User();
        user.addFriend(loggedUser);
        // When
        List<Trip> tripsByUser = tripService.getTripsByUser(user);
        // Then
        assertThat(tripsByUser).isNotEmpty();
    }
}
