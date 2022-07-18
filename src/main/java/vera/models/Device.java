package vera.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idD;
    @ManyToOne
    User user;
    String serialNumber;
    enum Type{
        ColdWater,
        HotWater,
        Electricity,
        Gas,
        Heating
    }

    Type type;

    @OneToMany(mappedBy="device")
    List<Indication> indications = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    List<Service> services = new ArrayList<>();
    public Device() {
    }

    public Device(String num, User u, Type type) {
        this.serialNumber= num;
        this.user = u;
        this.type=type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Device{" +
                "idD=" + idD +
                ", user=" + user +
                ", serialNumber=" + serialNumber +
                ", type=" + type +
                ", indications=" + indications +
                ", services=" + services +
                '}';
    }

    @XmlElement
    public void setIdD(long idD) {
        this.idD = idD;
    }
    @XmlElement
    public void setIndications(List<Indication> indications) {
        this.indications = indications;
    }
    @XmlElement
    public void setServices(List<Service> services) {
        this.services = services;
    }

    public long getIdD() {
        return idD;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    @XmlElement
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Type getType() {
        return type;
    }
    @XmlElement
    public void setType(Type type) {
        this.type = type;
    }
}
