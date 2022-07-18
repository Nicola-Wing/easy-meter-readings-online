package vera.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
public class PaidUpTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idP;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tariff tariff;
    private Date start;
    private Date end;

    public PaidUpTariff() {
    }
    public PaidUpTariff(User user, Tariff tariff, int month) {

        this.user = user;
        this.tariff = tariff;
        this.start = new Date();
        long time = new Date().getTime()+month*28*24*60*60*1000;
        this.end  = new Date(time);
    }

    public long getIdP() {
        return idP;
    }
    @XmlElement
    public void setIdP(long idP) {
        this.idP = idP;
    }

    public User getUser() {
        return user;
    }
    @XmlElement
    public void setUser(User user) {
        this.user = user;
    }

    public Tariff getTariff() {
        return tariff;
    }
    @XmlElement
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Date getStart() {
        return start;
    }
    @XmlElement
    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }
    @XmlElement
    public void setEnd(Date end) {
        this.end = end;
    }
}
