package vera.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
public class Indication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idI;
    @ManyToOne
    private User user;
    @ManyToOne
    private Device device;
    private double valueI;
    private Date startI;

    public Indication() {
    }

    public Indication(User user, Device device, double valueI, Date startI) {
        this.user = user;
        this.device = device;
        this.valueI = valueI;
        this.startI = new Date();
    }

    public long getIdI() {
        return idI;
    }
    @XmlElement
    public void setIdI(long idI) {
        this.idI = idI;
    }

    public User getUser() {
        return user;
    }
    @XmlElement
    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }
    @XmlElement
    public void setDevice(Device device) {
        this.device = device;
    }

    public double getValueI() {
        return valueI;
    }
    @XmlElement
    public void setValueI(double valueI) {
        this.valueI = valueI;
    }

    public Date getStartI() {
        return startI;
    }
    @XmlElement
    public void setStartI(Date startI) {
        this.startI = startI;
    }
}
