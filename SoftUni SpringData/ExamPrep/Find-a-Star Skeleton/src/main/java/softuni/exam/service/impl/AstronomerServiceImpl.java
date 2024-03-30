package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.AstronomerSeedDto;
import softuni.exam.models.dto.xmls.AstronomerSeedRootDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private static final String FILE_IMPORT_PATH = "src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        JAXBContext jaxbContext = JAXBContext.newInstance(AstronomerSeedRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        AstronomerSeedRootDto astronomerSeedRootDto =
                (AstronomerSeedRootDto) unmarshaller.unmarshal(new File(FILE_IMPORT_PATH));

        for (AstronomerSeedDto dto : astronomerSeedRootDto.getAstronomerSeedDtoList()) {
            Optional<Astronomer> optionalAstronomer = this.astronomerRepository
                    .findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
            Optional<Star> optionalStar = this.starRepository.findById(dto.getObservingStarId());
            if (!this.validationUtil.isValid(dto) || optionalAstronomer.isPresent() || optionalStar.isEmpty()) {
                sb.append("Invalid astronomer").append(System.lineSeparator());
                continue;
            }
            Astronomer astronomer = this.modelMapper.map(dto, Astronomer.class);
            astronomer.setObservingStar(optionalStar.get());
            this.astronomerRepository.saveAndFlush(astronomer);
            sb.append(String.format("Successfully imported astronomer %s %s - %.2f%n",
                    astronomer.getFirstName(),
                    astronomer.getLastName(),
                    astronomer.getAverageObservationHours()));
        }
        return sb.toString().trim();
    }
}
