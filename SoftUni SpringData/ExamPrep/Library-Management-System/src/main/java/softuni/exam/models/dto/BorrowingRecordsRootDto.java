package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsRootDto {
    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordDto> borrowingRecords;

    public List<BorrowingRecordDto> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecordDto> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}
