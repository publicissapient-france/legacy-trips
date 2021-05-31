package com.psf.tripservice.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void should_initialize_guest_user_as_null() {
        assertThat(User.guest()).isNull();
    }

    @Test
    void should_say_when_user_is_not_a_friend() {
        final User user = new User();
        final User notAFriend = new User();

        assertThat(user.isFriendOf(notAFriend)).isFalse();
    }

    @Test
    void should_say_when_user_is_a_friend() {
        final User user = new User();
        final User friend = aFriendOf(user);

        assertThat(user.isFriendOf(friend)).isTrue();
    }

    private static User aFriendOf(final User user) {
        final User friend = new User();
        user.addFriend(friend);
        return friend;
    }

}
