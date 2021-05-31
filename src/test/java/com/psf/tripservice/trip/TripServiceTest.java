package com.psf.tripservice.trip;

import com.psf.tripservice.exception.UserNotLoggedInException;
import com.psf.tripservice.user.User;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.psf.tripservice.user.User.guest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TripServiceTest {

    private static final Trip TO_CHATEAUDUN = Trip.to("Châteaudun");
    private static final Trip TO_CHICAGO = Trip.to("Chicago");

    private final TripDAO tripDAO = mock(TripDAO.class);
    private final TripService tripService = new TripService(tripDAO);

    @Test
    void should_fail_to_get_friend_trips_when_user_is_not_logged_in() {
        // Given
        final User loggedUser = guest();
        final User user = aUserWhoWent(TO_CHATEAUDUN);
        // When
        final ThrowingCallable call = () -> tripService.getFriendTrips(user, loggedUser);
        // Then
        assertThatExceptionOfType(UserNotLoggedInException.class)
                .isThrownBy(call);
    }

    @Test
    void should_get_no_friend_trips_when_user_is_not_friends_with_logged_user() {
        // Given
        final User loggedUser = aUserWhoWent(TO_CHICAGO);
        final User notAFriend = aUserWhoWent(TO_CHATEAUDUN);
        // When
        final List<Trip> trips = tripService.getFriendTrips(notAFriend, loggedUser);
        // Then
        assertThat(trips).isEmpty();
    }

    @Test
    void should_get_friend_trips_when_user_is_friend_with_logged_user() {
        // Given
        final User loggedUser = aUserWhoWent(TO_CHICAGO);
        final User friend = aFriendOf(loggedUser);
        given(tripDAO.findTripsBy(friend)).willReturn(List.of(TO_CHATEAUDUN));
        // When
        final List<Trip> trips = tripService.getFriendTrips(friend, loggedUser);
        // Then
        assertThat(trips).containsExactly(TO_CHATEAUDUN);
    }

    private static User aUserWhoWent(final Trip toDestination) {
        final User user = new User();
        user.addTrip(toDestination);
        return user;
    }

    private static User aFriendOf(final User friend) {
        final User user = new User();
        user.addFriend(friend);
        return user;
    }

}
