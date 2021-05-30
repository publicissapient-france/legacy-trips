package com.psf.tripservice.trip;

import java.util.List;

import com.psf.tripservice.exception.CollaboratorCallException;
import com.psf.tripservice.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}