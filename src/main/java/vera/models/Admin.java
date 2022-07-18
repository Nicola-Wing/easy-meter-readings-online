package vera.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idA;

    private String firstNameA;
    private String lastNameA;
    private String mailA;
    private String passA;
    private Date dateRegA;
    private String photoA;

    @ManyToOne
    private UserAuthorities userAuthorities;

    @OneToMany(mappedBy = "admin")
    List<Request> requests = new ArrayList<>();
    public Admin() {
    }

    public Admin (String name, String pass){
        this.mailA=name;
        this.passA=new BCryptPasswordEncoder().encode(pass);
        this.dateRegA=new Date();
    }

    public Admin(String firstNameA, String lastNameA, String mailA, String passA, String photoA) {
        this.firstNameA = firstNameA;
        this.lastNameA = lastNameA;
        this.mailA = mailA;
        this.passA = passA;
        this.photoA = "src/main/resources/static/photo/admins/" + photoA;
        this.dateRegA = new Date();
    }
    public Admin(String firstNameA, String lastNameA, String mailA, String passA) {
        this.firstNameA = firstNameA;
        this.lastNameA = lastNameA;
        this.mailA = mailA;
        this.passA = passA;
        this.photoA = "src/main/resources/static/photo/admins/default.jpg";
    }

    public Admin(long idA, String firstNameA, String lastNameA,
                 String mail, String pass, Date dateRegA,
                 String photoA, int userAuthorities) {
        this.idA = idA;
        this.firstNameA = firstNameA;
        this.lastNameA = lastNameA;
        this.mailA=mail;
        this.passA=pass;
        this.dateRegA = dateRegA;
        this.photoA = photoA;
        this.userAuthorities = new UserAuthorities(userAuthorities);


    }

    @XmlElement
    public void setIdA(long idA) {
        this.idA = idA;
    }
    @XmlElement
    public void setDateRegA(Date dateRegA) {
        this.dateRegA = dateRegA;
    }
    @XmlElement
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public String getFirstNameA() {
        return firstNameA;
    }
    @XmlElement
    public void setFirstNameA(String firstNameA) {
        this.firstNameA = firstNameA;
    }

    public String getLastNameA() {
        return lastNameA;
    }
    @XmlElement
    public void setLastNameA(String lastNameA) {
        this.lastNameA = lastNameA;
    }

    public String getMailA() {
        return mailA;
    }
    @XmlElement
    public void setMailA(String mailA) {
        this.mailA = mailA;
    }

    public String getPassA() {
        return passA;
    }
    @XmlElement
    public void setPassA(String passA) {
        this.passA = passA;
    }

    public String getPhotoA() {
        return photoA;
    }
    @XmlElement
    public void setPhotoA(String photoA) {
        this.photoA = photoA;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "idA=" + idA +
                ", firstNameA='" + firstNameA + '\'' +
                ", lastNameA='" + lastNameA + '\'' +
                ", mailA='" + mailA + '\'' +
                ", passA='" + passA + '\'' +
                ", dateRegA=" + dateRegA +
                ", photoA='" + photoA + '\'' +
                ", userAuthorities=" + userAuthorities.id +
                '}';
    }
}
