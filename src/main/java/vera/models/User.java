package vera.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private UserAuthorities userAuthorities;

    private String username;
    private String password;

    private String mailU;
    private String firstNameU;
    private String lastNameU;
    private Date dateRegU;
    private String photoU;

    @OneToMany(mappedBy = "user")
    List<PaidUpTariff> userTariffs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Indication> indications = new ArrayList<>();

    @OneToMany(mappedBy="user")
    List<Service> services = new ArrayList<>();

    @OneToMany(mappedBy="user",fetch = FetchType.EAGER)
    List<Device> device = new ArrayList<>();

    @OneToMany(mappedBy="user")
    List<Request> requests = new ArrayList<>();
    public User() {
        this.dateRegU=new Date();
    }

    public void preDelete(){
        this.device=null;
        this.userAuthorities=null;
        this.userTariffs=null;
        this.indications=null;
        this.services=null;
        this.requests=null;
    }
    @Transactional
    public List<Device> getListDevice() {
        return device;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userAuthorities=" + userAuthorities.id  +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mailU='" + mailU + '\'' +
                ", firstNameU='" + firstNameU + '\'' +
                ", lastNameU='" + lastNameU + '\'' +
                ", dateRegU=" + dateRegU +
                ", photoU='" + photoU + '\'' +
                '}';
    }

    public boolean deleteDevice(Device deviceNow){
        return device.remove(deviceNow);
    }
    public void addDevice(Device device) {
        this.device.add(device);
    }

    //public void addTariff(Tariff tar){ this.userTariffs.add(tar);}

    public User(String name, String mail, String pass, String firstName, String lastName) {
        this.mailU=mail;
        this.firstNameU=firstName;
        this.lastNameU=lastName;
        this.username=name;
        this.password= new BCryptPasswordEncoder().encode(pass);
        this.dateRegU=new Date();
    }

    public User(String name, String pass) {

        this.username=name;
        this.password= new BCryptPasswordEncoder().encode(pass);
        this.dateRegU=new Date();
    }

    public User(String username, String password, String mailU, String firstNameU, String lastNameU, Date dateRegU, String photoU) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.mailU = mailU;
        this.firstNameU = firstNameU;
        this.lastNameU = lastNameU;
        this.dateRegU = dateRegU;
        this.photoU = photoU;
    }

    public User(String username, String password,UserAuthorities userAuthorities, String mailU, String firstNameU, String lastNameU, Date dateRegU, String photoU) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.userAuthorities = userAuthorities;
        this.mailU = mailU;
        this.firstNameU = firstNameU;
        this.lastNameU = lastNameU;
        this.dateRegU = dateRegU;
        this.photoU = photoU;
    }
    public User(int id,String username, String password,UserAuthorities userAuthorities, String mailU, String firstNameU, String lastNameU, Date dateRegU, String photoU) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.userAuthorities = userAuthorities;
        this.mailU = mailU;
        this.firstNameU = firstNameU;
        this.lastNameU = lastNameU;
        this.dateRegU = dateRegU;
        this.photoU = photoU;
    }


    public String getMailU() {
        return mailU;
    }

    public void setMailU(String mailU) {
        this.mailU = mailU;
    }

    public String getFirstNameU() {
        return firstNameU;
    }

    public void setFirstNameU(String firstNameU) {
        this.firstNameU = firstNameU;
    }

    public String getLastNameU() {
        return lastNameU;
    }

    public void setLastNameU(String lastNameU) {
        this.lastNameU = lastNameU;
    }

    public Date getDateRegU() {
        return dateRegU;
    }

    public void setDateRegU(Date dateRegU) {
        this.dateRegU = dateRegU;
    }

    public String getPhotoU() {
        return photoU;
    }

    public void setPhotoU(String photoU) {
        this.photoU = photoU;
    }

    public UserAuthorities getUserAuthorities() {
        return userAuthorities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserAuthorities(UserAuthorities userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }
    @XmlElement
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public void setUserAuthorities(Object o) {
        this.userAuthorities= (UserAuthorities) o;
    }

}
