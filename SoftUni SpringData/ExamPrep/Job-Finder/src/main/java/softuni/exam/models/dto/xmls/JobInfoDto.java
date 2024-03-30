package softuni.exam.models.dto.xmls;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "job")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobInfoDto {
    @XmlElement
    @Size(min = 2, max = 40)
    private String jobTitle;
    @XmlElement
    @Min(10)
    private double hoursAWeek;
    @XmlElement
    @Min(300)
    private double salary;
    @XmlElement
    @Length(min = 5)
    private String description;
    @XmlElement
    private long companyId;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
