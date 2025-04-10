package com.fiipractic.Util;

import com.fiipractic.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class AppUserContext {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}

