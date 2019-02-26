package be.pxl.s2it.Service;

import be.pxl.s2it.Domain.User;
import be.pxl.s2it.Interfaces.LoginManager;
import org.springframework.stereotype.Service;

@Service
public class LoginManagerImpl implements LoginManager {
    @Override
    public Iterable<User> getAllUsers() {
        return null;
    }
}
