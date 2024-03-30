package bg.softuni.cardealer.service.dtos.imports;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedDto implements Serializable {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Double price;
    @XmlAttribute
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
