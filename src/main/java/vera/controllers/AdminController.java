package vera.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vera.models.*;
import vera.repo.*;
import vera.utils.BackUpRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserAuthoritiesRepo userAuthoritiesRepo;

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    DeviceRepo deviceRepo;
    @Autowired
    IndicationRepo indicationRepo;
    @Autowired
    PaidUpTariffsRepo paidUpTariffsRepo;
    @Autowired
    RequestRepo requestRepo;
    @Autowired
    ServiceRepo serviceRepo;
    @Autowired
    TariffRepo tariffRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    WorkerRepo workerRepo;

    long tempIdU = 0;


    @GetMapping("/main")
    public String main() {
        return "redirect:/admin/list";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        List<User> users = (List<User>) userRepo.findAll();
        model.addAttribute("users", users);

        List<Worker> workers = (List<Worker>) workerRepo.findAllByOrderByInfoW();
        model.addAttribute("workers", workers);

        model.addAttribute("services", serviceRepo.findAllByOrderByDateS());

        model.addAttribute("requests",requestRepo.findAll());

        model.addAttribute("tariffs", tariffRepo.findAll());

        return "list";
    }

    @GetMapping("/backUp")
    public String backup(){
        String text = BackUpRepo.repoToString(userRepo);
        BackUpRepo.writeString(text,"src/main/resources/static/files/user.txt");
        text = BackUpRepo.repoToString(adminRepo);
        BackUpRepo.writeString(text,"src/main/resources/static/files/admin.txt");
        return "redirect:/admin/list";
    }

    @GetMapping("/restore")
    public String restore(){

        List<User> users = (List<User>) BackUpRepo.textToList("src/main/resources/static/files/user.txt",User.class);
        users.forEach(u->userRepo.save(u));
        List<Admin> admins = (List<Admin>) BackUpRepo.textToList("src/main/resources/static/files/admin.txt",Admin.class);
        admins.forEach(a->adminRepo.save(a));
        return "redirect:/admin/list";
    }

    @GetMapping("/restoreUser")
    public String restoreUser(){

        List<User> users = (List<User>) BackUpRepo.textToList("src/main/resources/static/files/user.txt",User.class);
        users.forEach(u->userRepo.save(u));
        return "redirect:/admin/list";
    }
    @GetMapping("/restoreAdmin")
    public String restoreAdmin(){

        List<Admin> admins = (List<Admin>) BackUpRepo.textToList("src/main/resources/static/files/admin.txt",Admin.class);
        admins.forEach(a->adminRepo.save(a));
        return "redirect:/admin/list";
    }

    @GetMapping("/user/id={id}")
    public String userPage(@PathVariable long id, Model model) throws ChangeSetPersister.NotFoundException {
        User user = userRepo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        model.addAttribute("user", user);
        List<Device> dev =deviceRepo.findAll().stream().filter(d-> d.getUser() == null).collect(Collectors.toList());
        model.addAttribute("freeDev", dev);
        model.addAttribute("usersDevices",user.getListDevice());
        return "user";
    }

    @GetMapping("/user/deviceOn/{idDev}/{idU}")
    public String deviceOn(@PathVariable long idDev, @PathVariable long idU){
        Optional<User> userOptional = userRepo.findById(idU);
        User user = userOptional.get();
        Optional<Device> deviceOptional= deviceRepo.findById(idDev);
        Device device = deviceOptional.get();
        user.addDevice(device);
        device.setUser(user);
        deviceRepo.save(device);
        userRepo.save(user);
        return "redirect:/admin/user(id="+idU+")";
    }
    @GetMapping("/user/edit/{id}")
    public String userEdit(@PathVariable long id, Model model) {
        Optional<User> uo = userRepo.findById(id);

        if (uo.isPresent()) {
            model.addAttribute("user", uo.get());
        }
        return "userEdit";
    }

    @PostMapping("/user/edit/{id}")
    public String userEditPost(@PathVariable long id, @ModelAttribute User user, Model model){
        System.out.println(user.toString());
        userRepo.save(user);
        return "redirect:/admin/list";
    }

    @GetMapping("user/delete/{id}")
    public String userDelete(@PathVariable long id){
        Optional<User> u = userRepo.findById(id);
        User user = u.get();
        user.preDelete();
        userRepo.save(user);

        userRepo.deleteById(id);

        return "redirect:/admin/list";
    }


    @GetMapping("user/deviceEdit/{id}")
    public String userDeviceEdit(@PathVariable long id, Model model){
        Optional<Device> deviceOptional = deviceRepo.findById(id);
        if (deviceOptional.isPresent()) {
            model.addAttribute("device", deviceOptional.get());
        }
        return "deviceEdit";
    }
    @PostMapping("/user/deviceEdit/{id}")
    public String userDeviceEditPost(@PathVariable long id, @ModelAttribute Device device, Model model){
        System.out.println(device.toString());
        deviceRepo.save(device);
        return "userEdit";
    }

    @GetMapping("user/deviceOff/{id}")
    public String deviceOff(@PathVariable long id){
        Optional<Device> optionalDevice=deviceRepo.findById(id);
        Device d = optionalDevice.get();
        User u = d.getUser();
        u.deleteDevice(d);
        d.setUser(null);
        userRepo.save(u);
        deviceRepo.save(d);
        return"redirect:/admin/list";
    }

    //For Worker.

    @GetMapping("/worker/edit/{id}")
    public String workerEdit(@PathVariable long id, Model model) {
        Optional<Worker> wo = workerRepo.findById(id);

        if (wo.isPresent()) {
            model.addAttribute("worker", wo.get());
        }
        System.out.println(wo.toString());
        return "workerEdit";
    }

    @PostMapping("/worker/edit/{id}")
    public String workerEditPost(@PathVariable long id, @ModelAttribute Worker worker, Model model){

        System.out.println(id);
        System.out.println(worker.toString());
        worker.setIdW(id);
        workerRepo.save(worker);
        return "redirect:/admin/list";
    }

    @GetMapping("worker/delete/{id}")
    public String workerDelete(@PathVariable long id){
        Optional<Worker> w = workerRepo.findById(id);
        Worker worker = w.get();
        worker.preDelete();
        workerRepo.save(worker);

        workerRepo.deleteById(id);

        return "redirect:/admin/list";
    }

    @GetMapping("/worker/add")
    public String workerAdd(Model model) {
        model.addAttribute("worker", new Worker());
        return "addWorker";
    }

    @PostMapping("/worker/add")
    public String workerAddPost(@ModelAttribute Worker worker, Model model){
        workerRepo.save(worker);
        Worker w2 = workerRepo.findByMailW(worker.getMailW());
        UserAuthorities ua = userAuthoritiesRepo.findUserAuthoritiesByAuthority("ROLE_WORKER");
        w2.setUserAuthorities(ua);
        userAuthoritiesRepo.save(ua);
        workerRepo.save(w2);

        return "redirect:/admin/list";
    }

    //End Worker.
    //Requests

    @GetMapping("/request/add")
    public String requestAdd(Model model){
        model.addAttribute("request", new Request());
        return "requestAdd";
    }

    @PostMapping("/request/add")
    public String requestAddPost(@ModelAttribute Request request, @RequestParam String usermail, Model model){
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepo.findByMailA(currentUser);
        request.setAdmin(admin);
        request.setUser(userRepo.findByMailU(usermail));
        request.setDateRequest(new Date());
        requestRepo.save(request);
        return "redirect:/admin/list";
    }

    @GetMapping("/request/done/{id}")
    public String requestDone(@PathVariable long id){
        Optional<Request> req = requestRepo.findById(id);
        Request request = req.get();
        request.setDateResponse(new Date());
        requestRepo.save(request);
        return "redirect:/admin/list";
    }

    //End Requests.
    //Tariff

    @GetMapping("/tariff/add")
    public String tariffAdd(Model model){
        model.addAttribute("tariff", new Tariff());
        return "tariffAdd";
    }

    @PostMapping("/tariff/add")
    public String tariffAddPost(@ModelAttribute Tariff tariff, Model model){
        tariffRepo.save(tariff);
        return "redirect:/admin/list";
    }

    @GetMapping("/tariff/edit/{id}")
    public String tariffEdit(@PathVariable long id, Model model) {
        model.addAttribute("tariff", tariffRepo.findById(id).get());
        return "tariffEdit";
    }

    @PostMapping("/tariff/edit/{id}")
    public String tariffEditPost(@PathVariable long id, @ModelAttribute Tariff tariff, Model model){
        tariff.setIdT(id);
        tariffRepo.save(tariff);
        return "redirect:/admin/list";
    }

    @GetMapping("tariff/delete/{id}")
    public String tariffDelete(@PathVariable long id){
        tariffRepo.deleteById(id);
        return "redirect:/admin/list";
    }

    //End Tariff.
}
