package vera.security;

import lombok.Data;
import vera.models.User;
import vera.models.Worker;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    public User toUser(){
        return new User(username,password);
    }



    public Worker toWorker(){return new Worker(username,password);}
}
