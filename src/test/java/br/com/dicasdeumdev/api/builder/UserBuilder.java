package br.com.dicasdeumdev.api.builder;

import br.com.dicasdeumdev.api.domain.User;

public class UserBuilder {

    private User user;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.user = new User(1, "User 1", "user@email.com", "123");
        return userBuilder;
    }

    public User now() {
        return user;
    }

    public UserBuilder withId(Integer id) {
        user.setId(id);
        return this;
    }

    public UserBuilder withNome(String name) {
        user.setNome(name);
        return this;
    }

    public UserBuilder whitEmail(String email) {
        user.setEmail(email);
        return this;
    }
}
