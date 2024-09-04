package com.example.LessonPlanSys.Authorize;

import java.util.Objects;

public final class LoginForm {
    private final String username;
    private final String passwordHash;

    public LoginForm(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String username() {
        return username;
    }

    public String password() {
        return passwordHash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LoginForm) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.passwordHash, that.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordHash);
    }

    @Override
    public String toString() {
        return "LoginForm[" +
                "username=" + username + ", " +
                "password=" + passwordHash + ']';
    }

}
