package vera.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement(name="tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idT;
    String nameT;
    String infoT;
    int priceT;
    @OneToMany(mappedBy = "tariff")
    List<PaidUpTariff> paid = new ArrayList<>();

    @XmlElement (name = "paidUpTariff")
    public void setPaid(List<PaidUpTariff> paid) {
        this.paid = paid;
    }

    public Tariff() {
    }
    public Tariff(String name, String info, int priceT) {
        this.nameT=name;
        this.infoT = info;
        this.priceT=priceT;
    }

    public long getIdT() {
        return idT;
    }
    @XmlElement
    public void setIdT(long idT) {
        this.idT = idT;
    }

    public String getNameT() {
        return nameT;
    }
    @XmlElement
    public void setNameT(String nameT) {
        this.nameT = nameT;
    }

    public String getInfoT() {
        return infoT;
    }
    @XmlElement
    public void setInfoT(String infoT) {
        this.infoT = infoT;
    }

    public int getPriceT() {
        return priceT;
    }
    @XmlElement
    public void setPriceT(int priceT) {
        this.priceT = priceT;
    }

    public List<PaidUpTariff> getPaid() {
        return paid;
    }
}
