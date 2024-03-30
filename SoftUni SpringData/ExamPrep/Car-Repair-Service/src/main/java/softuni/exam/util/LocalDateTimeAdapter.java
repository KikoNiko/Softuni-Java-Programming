package softuni.exam.util;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String string) throws Exception {
        return LocalDateTime.parse(string);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return String.valueOf(localDateTime);
    }
}
