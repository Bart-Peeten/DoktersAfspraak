package be.pxl.s2it.Interfaces;

import be.pxl.s2it.Domain.User;

public interface LoginManager {

    Iterable<User> getAllUsers();
}
