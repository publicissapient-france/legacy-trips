package com.psf.tripservice.user;

import java.util.ArrayList;
import java.util.List;

import com.psf.tripservice.trip.Trip;

public class User {

	private List<Trip> trips = new ArrayList<Trip>();
	private List<User> friends = new ArrayList<User>();

    public static User guest() {
    	return null;
    }

    public List<User> getFriends() {
		return friends;
	}

	public void addFriend(User user) {
		friends.add(user);
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public List<Trip> trips() {
		return trips;
	}

	public boolean isFriendOf(final User user) {
		return friends.contains(user);
	}

}
