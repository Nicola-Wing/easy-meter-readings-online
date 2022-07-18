package vera.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idR;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private User user;
    private Date dateRequest;
    private Date dateResponse;
    private String infoR;

    public Request() {
    }

    public Request(Admin admin, User user, Date dateRequest, String info) {
        this.admin = admin;
        this.user = user;
        this.dateRequest = dateRequest;
        this.infoR = info;
    }

    public long getIdR() {
        return idR;
    }
    @XmlElement
    public void setIdR(long idR) {
        this.idR = idR;
    }

    public Admin getAdmin() {
        return admin;
    }
    @XmlElement
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }
    @XmlElement
    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateRequest() {
        return dateRequest;
    }
    @XmlElement
    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Date getDateResponse() {
        return dateResponse;
    }
    @XmlElement
    public void setDateResponse(Date dateResponse) {
        this.dateResponse = dateResponse;
    }

    public String getInfoR() {
        return infoR;
    }
    @XmlElement
    public void setInfoR(String infoR) {
        this.infoR = infoR;
    }
}
