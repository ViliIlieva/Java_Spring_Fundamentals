package com.example.coffee_shop.session;

import com.example.coffee_shop.models.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
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

}
