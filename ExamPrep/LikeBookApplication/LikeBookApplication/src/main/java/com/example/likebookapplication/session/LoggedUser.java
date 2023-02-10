package com.example.likebookapplication.session;

import com.example.likebookapplication.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private long id;

    private String username;

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
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

    public String getFullName() {
        return username;
    }

    public void setFullName(String fullName) {
        this.username = fullName;
    }
}
