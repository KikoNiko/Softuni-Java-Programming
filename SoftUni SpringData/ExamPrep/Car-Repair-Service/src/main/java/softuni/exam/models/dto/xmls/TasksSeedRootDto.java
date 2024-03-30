package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class TasksSeedRootDto {
    @XmlElement(name = "task")
    private List<TasksSeedDto> tasksSeedDtoList;

    public List<TasksSeedDto> getTasksSeedDtoList() {
        return tasksSeedDtoList;
    }

    public void setTasksSeedDtoList(List<TasksSeedDto> tasksSeedDtoList) {
        this.tasksSeedDtoList = tasksSeedDtoList;
    }
}
