package com.example.spotifyplaylistapp.session;

import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope//да се пази една сесия
public class LoggedUser {
    private long id;

    private String username;

    public void login(User user) {
        this.id = user.getId ();
        this.username = user.getUsername ();
    }

    //да изчисти данните, за да са свободни за следващия който се логне
    public void logout() {
        this.id = 0;
        this.username = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
