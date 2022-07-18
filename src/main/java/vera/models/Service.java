package vera.models;


import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long idS;
    @NotNull
    @ManyToOne
    private User user;
    @NotNull
    @ManyToOne
    private Worker worker;
    @NotNull
    @ManyToOne
    private Device device;
    private String infoS;
    private Date dateS;
    private int price;

    public Service() {
    }


    public Service(String info, int price) {
        this.infoS = info;
        this.price = price;
        this.dateS = new Date();
    }
    @XmlElement
    public void setDateS(Date dateS) {
        this.dateS = dateS;
    }
    @XmlElement
    public void setPrice(int price) {
        this.price = price;
    }

    @XmlElement
    public void setIdS(long idS) {
        this.idS = idS;
    }

    public long getIdS() {
        return idS;
    }

    public Date getDateS() {
        return dateS;
    }

    public int getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }
    @XmlElement
    public void setUser(User user) {
        this.user = user;
    }

    public Worker getWorker() {
        return worker;
    }
    @XmlElement
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Device getDevice() {
        return device;
    }
    @XmlElement
    public void setDevice(Device device) {
        this.device = device;
    }

    public String getInfoS() {
        return infoS;
    }
    @XmlElement
    public void setInfoS(String infoS) {
        this.infoS = infoS;
    }


}
