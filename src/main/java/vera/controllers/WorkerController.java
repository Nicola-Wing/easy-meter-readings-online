package vera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vera.models.Device;
import vera.models.Service;
import vera.models.User;
import vera.models.Worker;
import vera.repo.DeviceRepo;
import vera.repo.ServiceRepo;
import vera.repo.UserRepo;
import vera.repo.WorkerRepo;

import java.util.Date;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    ServiceRepo serviceRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    DeviceRepo deviceRepo;

    @Autowired
    WorkerRepo workerRepo;

    Worker worker;

    @GetMapping("/main")
    public String main() {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        worker = workerRepo.findByMailW(currentUser);
        System.out.println(currentUser);
        return "redirect:/worker/service";
    }

    @GetMapping("/service")
    public String services(Model model){
        model.addAttribute("worker", worker);
        model.addAttribute("services",serviceRepo.findByWorkerIdW(worker.getIdW()));
        model.addAttribute("serviceForManipulaiting",new Service());
        return "services";
    }

    @GetMapping("/edit/{id}")
    public String editWorkerData(@PathVariable long id, Model model){
        model.addAttribute("worker", workerRepo.findById(id).get());

        return "workerAccountEdit";
    }

    @PostMapping("/edit/{id}")
    public String editWorkerDataPost(@PathVariable long id,@RequestParam String pass, @ModelAttribute Worker worker, Model model){
        worker.setIdW(id);
        System.out.println(id);

        /*worker.setUserAuthorities(this.worker.getUserAuthorities());
        System.out.println(this.worker.getUserAuthorities());*/

        System.out.println(pass);
        if(pass != "") {
            worker.setPassw(pass);
        }else{
            worker.setPassw(this.worker.getPassw());
        }
        workerRepo.save(worker);
        System.out.println(worker);
        return "redirect:/worker/service";
    }


    @PostMapping("/service/add")
    public String services(@ModelAttribute Service service,
                           @RequestParam String usermail,
                           @RequestParam String workermail,
                           @RequestParam String serialnumber,
                           Model model){
        User user = userRepo.findByMailU(usermail);
        Worker worker = workerRepo.findByMailW(workermail);
        Device device = deviceRepo.findBySerialNumber(serialnumber);
        service.setDevice(device);
        service.setUser(user);
        service.setWorker(worker);
        service.setDateS(new Date());
        serviceRepo.save(service);
        System.out.println(usermail+" "+workermail+" "+serialnumber);
        return "redirect:/worker/service";
    }
    @GetMapping("/service/edit/{id}")
    public String editService(@PathVariable long id, Model model){
        model.addAttribute("service",(Service)serviceRepo.findById(id).get());

        return "serviceEdit";
    }
    @PostMapping("/service/edit/{id}")
    public String editServicePost(@ModelAttribute Service service,
                                  @RequestParam String usermail,
                                  @RequestParam String workermail,
                                  @RequestParam String serialnumber,
                                  @PathVariable long id,
                                  Model model){
        User user = userRepo.findByMailU(usermail);
        Worker worker = workerRepo.findByMailW(workermail);
        Device device = deviceRepo.findBySerialNumber(serialnumber);
        service.setDevice(device);
        service.setUser(user);
        service.setWorker(worker);
        service.setIdS(id);
        serviceRepo.save(service);

        return "redirect:/worker/service";
    }
}
