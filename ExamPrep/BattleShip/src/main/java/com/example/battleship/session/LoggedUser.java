package com.example.battleship.session;

import com.example.battleship.domain.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope//да се пази една сесия
public class LoggedUser {
    private long id;

    private String fullName;

    //ID става равно на логнатия юзър и името му се взема
    public void login(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
    }

    //да изчисти данните, за да са свободни за следващия който се логне
    public void logout() {
        this.id = 0;
        this.fullName = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
