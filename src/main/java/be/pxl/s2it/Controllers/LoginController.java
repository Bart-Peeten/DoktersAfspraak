package be.pxl.s2it.Controllers;

import be.pxl.s2it.Domain.User;
import be.pxl.s2it.Interfaces.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "login/")
public class LoginController {

    @Autowired
    LoginManager manager;

    @GetMapping(path = "all")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> users = manager.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(path = "{id}/{name}")
    public ResponseEntity addNewUser(@PathVariable long id,
                                     @PathVariable String name){
        return null;
    }
}
