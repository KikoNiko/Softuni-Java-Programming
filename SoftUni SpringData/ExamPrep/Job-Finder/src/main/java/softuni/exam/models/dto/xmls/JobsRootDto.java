package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobsRootDto {
    @XmlElement(name = "job")
    private List<JobInfoDto> jobsList;

    public List<JobInfoDto> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<JobInfoDto> jobsList) {
        this.jobsList = jobsList;
    }
}
