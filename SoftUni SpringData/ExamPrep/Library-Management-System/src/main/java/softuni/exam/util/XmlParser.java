package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <E> E parse(Class<E> clazz, String path) throws JAXBException;

}
