package com.sagar.chrysalis2019.data.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String userId;
    private String displayName;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {

        return user.getEmail();
    }

    public String getDisplayName() {

        return user.getDisplayName();
    }
}
