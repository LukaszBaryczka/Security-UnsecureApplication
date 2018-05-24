package ftims.security.unsecure.app.unsecureApp.service;

/**
 * Created by bryka on 24.05.18.
 */
import ftims.security.unsecure.app.unsecureApp.model.User;

import ftims.security.unsecure.app.unsecureApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionComponent sessionComponent;

    public Boolean authUser(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if(user == null) {
            sessionComponent.setLogged(false);
            return false;
        }
        sessionComponent.setUser(user);
        sessionComponent.setLogged(true);
        return true;
    }
}