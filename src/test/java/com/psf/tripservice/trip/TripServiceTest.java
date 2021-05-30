package com.psf.tripservice.trip;

import com.psf.tripservice.exception.UserNotLoggedInException;
import com.psf.tripservice.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TripServiceTest {

    TripDAO tripDAO;

    @BeforeEach
    public void setUp() {
        tripDAO = Mockito.mock(TripDAO.class);
    }

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        // Given
        TripService tripService = new TripService(tripDAO);

        // When
        // Then
        Assertions.assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(null, null);
        });
    }

    @Test
    void should_return_empty_trips_when_user_has_no_friends() {
        // Given
        TripService tripService = new TripService(tripDAO);

        // When
        List<Trip> tripsByUser = tripService.getTripsByUser(new User(), new User());
        // Then
        assertThat(tripsByUser).isEmpty();
    }

    @Test
    void should_return_empty_trips_when_user_has_friends() {
        // Given
        TripService tripService = new TripService(tripDAO);

        User user = new User();
        user.addFriend(new User());
        // When
        List<Trip> tripsByUser = tripService.getTripsByUser(user, new User());
        // Then
        assertThat(tripsByUser).isEmpty();
    }

    @Test
    void should_return_trips_when_user_is_friend_with_loggedUser() {
        // Given
        User loggedUser = new User();
        TripService tripService = new TripService(tripDAO);

        User user = new User();
        user.addFriend(loggedUser);
        Mockito.when(tripDAO.findTripsByUser(user)).thenReturn(List.of(new Trip("Miami")));

        // When
        List<Trip> tripsByUser = tripService.getTripsByUser(user, loggedUser);
        // Then
        assertThat(tripsByUser).isNotEmpty();
    }
}