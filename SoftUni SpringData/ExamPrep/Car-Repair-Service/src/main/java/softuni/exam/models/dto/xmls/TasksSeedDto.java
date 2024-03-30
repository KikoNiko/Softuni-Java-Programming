package softuni.exam.models.dto.xmls;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TasksSeedDto {
    @XmlElement
    @NotNull
    private String date;
    @XmlElement
    @Positive
    @NotNull
    private BigDecimal price;
    @XmlElement
    private CarDto car;
    @XmlElement
    private MechanicDto mechanic;
    @XmlElement
    private PartDto part;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public MechanicDto getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicDto mechanic) {
        this.mechanic = mechanic;
    }

    public PartDto getPart() {
        return part;
    }

    public void setPart(PartDto part) {
        this.part = part;
    }
}
