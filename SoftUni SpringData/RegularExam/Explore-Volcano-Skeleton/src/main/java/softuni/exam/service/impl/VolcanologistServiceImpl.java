package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.VolcanologistSeedDto;
import softuni.exam.models.dto.xmls.VolcanologistSeedRootDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/files/xml/volcanologists.xml";

    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoRepository volcanoRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, VolcanoRepository volcanoRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        VolcanologistSeedRootDto volcanologistSeedRootDto =
                this.xmlParser.fromFile(FILE_IMPORT_PATH, VolcanologistSeedRootDto.class);
        List<VolcanologistSeedDto> volcanologistList = volcanologistSeedRootDto.getVolcanologistList();
        for (VolcanologistSeedDto dto : volcanologistList) {
            Optional<Volcanologist> optionalVolcanologist =
                    this.volcanologistRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
            Optional<Volcano> optionalVolcano = this.volcanoRepository.findById(dto.getExploringVolcanoId());
            if (!this.validationUtil.isValid(dto) || optionalVolcanologist.isPresent() || optionalVolcano.isEmpty()) {
                sb.append("Invalid volcanologist").append(System.lineSeparator());
                continue;
            }
            Volcanologist volcanologist = this.modelMapper.map(dto, Volcanologist.class);
            volcanologist.setVolcano(optionalVolcano.get());
            this.volcanologistRepository.saveAndFlush(volcanologist);

            sb.append(String.format("Successfully imported volcanologist %s %s%n",
                    volcanologist.getFirstName(), volcanologist.getLastName()));
        }

        return sb.toString().trim();
    }
}