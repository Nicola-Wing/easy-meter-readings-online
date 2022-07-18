package vera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vera.models.User;
import vera.repo.TariffRepo;
import vera.repo.UserRepo;

import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TariffRepo tariffRepo;

    User user;
    Date after ;
    Date before;
    Integer counterSeconds = 0;
    Double waluasity = 0d;
    Double electricity = 0d;
    Integer nImpl = 0;

    @GetMapping("/main")
    public String main() {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        user = userRepo.findByUsername(currentUser);
        System.out.println(currentUser);
        return "main";
    }

    /*@GetMapping("/mypage")
    public String mypage(Model model){
        model.addAttribute("user", user);
        model.addAttribute("tariffs", tariffRepo.findAll());
        return "mypage";
    }*/

    /*@GetMapping("/edit/{id}")
    public String editUserData(@PathVariable long id, Model model){
        model.addAttribute("user", userRepo.findById(id).get());
        return "userAccountEdit";
    }

    @PostMapping("/edit/{id}")
    public String editUserDataPost(@PathVariable long id, @RequestParam String pass, @ModelAttribute User u, Model model){
        u.setId(id);
        if(pass == "" || pass == null){
            u.setPassword(user.getPassword());
        } else {
            u.setPassword(pass);
        }
        userRepo.save(user);
        return "redirect:/user/mypage";
    }
*/

    Thread current = new Thread();

    @GetMapping("/electricity/start")
    public String electricityStart(Model model){
        run();
        model.addAttribute("user", user);
        before = new Date();
        return "main";
    }

    @GetMapping("/electricity/stop")
    public String electricityStop(Model model){
        current.interrupt();
        waluasity= Double.valueOf((nImpl*3600)/(6400*counterSeconds));
        electricity+=Double.valueOf(waluasity * counterSeconds/3600);
        System.out.println(electricity);
        System.out.println(nImpl);
        System.out.println(counterSeconds);
        model.addAttribute("e", electricity);
        model.addAttribute("w", waluasity);
        model.addAttribute("time",counterSeconds);
        return "main";
    }

    public void run(){
        System.out.printf("%s started... \n", current.getName());
        counterSeconds = 1;
        Random randN = new Random();

        while(!current.isInterrupted()){
            int temp = randN.nextInt(100)+1;
            System.out.println("Loop " + counterSeconds++); //
            try{
                current.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println(current.getName() + " has been interrupted");
                System.out.println(current.isInterrupted());
                current.interrupt();
            }
            nImpl += temp;
        }
        System.out.printf("%s finished... \n", current.getName());
    }
}
