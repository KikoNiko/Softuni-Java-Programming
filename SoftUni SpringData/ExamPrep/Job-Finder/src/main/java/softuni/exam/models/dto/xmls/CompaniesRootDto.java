package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompaniesRootDto {
    @XmlElement(name = "company")
    private List<CompanyInfoDto> companyInfoDtoList;

    public List<CompanyInfoDto> getCompanyInfoDtoList() {
        return companyInfoDtoList;
    }

    public void setCompanyInfoDtoList(List<CompanyInfoDto> companyInfoDtoList) {
        this.companyInfoDtoList = companyInfoDtoList;
    }
}
