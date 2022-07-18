package vera.models;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idW;
    private String firstNameW;
    private String lastNameW;
    private String mailW;
    private String phoneNumberW;
    private String photoW;
    private String infoW;
    private String passw;

    @ManyToOne
    private UserAuthorities userAuthorities;

    @OneToMany(mappedBy="worker")
    List<Service> services = new ArrayList<>();

    public Worker() {
    }
    public Worker(String name, String pass) {

        this.mailW=name;
        this.passw= new BCryptPasswordEncoder().encode(pass);
            }

    public Worker(String pass,String fn, String ln, String mail, String phone, String photo, String info) {
        this.passw=new BCryptPasswordEncoder().encode(pass);
        this.firstNameW=fn;
        this.lastNameW=ln;
        this.mailW=mail;
        this.phoneNumberW=phone;
        this.photoW=photo;
        this.infoW=info;
    }
    public Worker(String pass,String fn,String ln,String mail,String phone, String info) {
        this.passw=new BCryptPasswordEncoder().encode(pass);
        this.firstNameW=fn;
        this.lastNameW=ln;
        this.mailW=mail;
        this.phoneNumberW=phone;
        this.photoW="src/main/resources/static/photo/workers/default.jpg";
        this.infoW=info;
    }

    public UserAuthorities getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(UserAuthorities userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public long getIdW() {
        return idW;
    }

    public void preDelete(){
        this.services=null;
        this.userAuthorities=null;
    }

    @XmlElement
    public void setIdW(long idW) {
        this.idW = idW;
    }

    public List<Service> getServices() {
        return services;
    }
    @XmlElement
    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getPhotoW() {
        return photoW;
    }
    @XmlElement
    public void setPhotoW(String photoW) {
        this.photoW = photoW;
    }

    public String getFirstNameW() {
        return firstNameW;
    }
    @XmlElement
    public void setFirstNameW(String firstNameW) {
        this.firstNameW = firstNameW;
    }

    public String getLastNameW() {
        return lastNameW;
    }
    @XmlElement
    public void setLastNameW(String lastNameW) {
        this.lastNameW = lastNameW;
    }

    public String getMailW() {
        return mailW;
    }
    @XmlElement
    public void setMailW(String mailW) {
        this.mailW = mailW;
    }

    public String getPhoneNumberW() {
        return phoneNumberW;
    }
    @XmlElement
    public void setPhoneNumberW(String phoneNumberW) {
        this.phoneNumberW = phoneNumberW;
    }

    public String getInfoW() {
        return infoW;
    }
    @XmlElement
    public void setInfoW(String infoW) {
        this.infoW = infoW;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "idW=" + idW +
                ", firstNameW='" + firstNameW + '\'' +
                ", lastNameW='" + lastNameW + '\'' +
                ", mailW='" + mailW + '\'' +
                ", phoneNumberW='" + phoneNumberW + '\'' +
                ", photoW='" + photoW + '\'' +
                ", infoW='" + infoW + '\'' +
                ", passw='" + passw + '\'' +


                '}';
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = new BCryptPasswordEncoder().encode(passw);
    }
}
