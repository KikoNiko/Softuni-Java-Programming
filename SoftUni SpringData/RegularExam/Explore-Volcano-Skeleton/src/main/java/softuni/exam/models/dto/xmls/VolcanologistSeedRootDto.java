package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistSeedRootDto {

    @XmlElement(name = "volcanologist")
    private List<VolcanologistSeedDto> volcanologistList;

    public List<VolcanologistSeedDto> getVolcanologistList() {
        return volcanologistList;
    }

    public void setVolcanologistList(List<VolcanologistSeedDto> volcanologistList) {
        this.volcanologistList = volcanologistList;
    }
}
