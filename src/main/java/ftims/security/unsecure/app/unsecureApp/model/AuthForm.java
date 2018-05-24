package ftims.security.unsecure.app.unsecureApp.model;

import javax.validation.constraints.NotEmpty;

/**
 * Created by bryka on 24.05.18.
 */
public class AuthForm {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
