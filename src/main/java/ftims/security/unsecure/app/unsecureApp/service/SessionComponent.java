package ftims.security.unsecure.app.unsecureApp.service;

import ftims.security.unsecure.app.unsecureApp.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by bryka on 24.05.18.
 */
@Component
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class SessionComponent {

    private Boolean isLogged;

    private User user;

    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
