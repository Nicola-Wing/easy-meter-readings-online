package vera.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vera.models.User;
import vera.models.UserAuthorities;
import vera.models.Worker;
import vera.repo.UserAuthoritiesRepo;
import vera.repo.UserRepo;
import vera.repo.WorkerRepo;
import vera.security.RegistrationForm;

@Controller
@RequestMapping("/register/{role}")
public class RegisterController {

    private UserRepo userRepo;
    @Autowired
    private UserAuthoritiesRepo userAuthoritiesRepo;

    @Autowired
    private WorkerRepo workerRepo;
    private PasswordEncoder passwordEncoder;

    public RegisterController(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder= passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return"registration";
    }

    @PostMapping
    public String processRegistration(@PathVariable String role, RegistrationForm form){
        switch(role){
            case "worker":
                Worker owt = form.toWorker();
                workerRepo.save(owt);
                Worker win = workerRepo.findByMailW(owt.getMailW());
                UserAuthorities ua = userAuthoritiesRepo.findUserAuthoritiesByAuthority("ROLE_WORKER");
                win.setUserAuthorities(ua);
                userAuthoritiesRepo.save(ua);
                workerRepo.save(win);
                break;

            case"user":
                User out = form.toUser();
                userRepo.save(out);

                User in = userRepo.findByUsername(out.getUsername());

                UserAuthorities authorities = userAuthoritiesRepo.
                        findUserAuthoritiesByAuthority("ROLE_USER");

                in.setUserAuthorities(authorities);
                /*authorities.addUser(in);*/
                userAuthoritiesRepo.save(authorities);
                userRepo.save(in);
                break;
        }



        return"redirect:/login";

    }

}
