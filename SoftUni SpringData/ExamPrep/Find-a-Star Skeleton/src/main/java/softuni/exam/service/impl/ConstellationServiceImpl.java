package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static final String FILE_IMPORT_PATH = "src/main/resources/files/json/constellations.json";
    private final ConstellationRepository constellationRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();
        ConstellationSeedDto[] constellationSeedDtos =
                this.gson.fromJson(readConstellationsFromFile(), ConstellationSeedDto[].class);
        for (ConstellationSeedDto dto : constellationSeedDtos) {
            Optional<Constellation> optional = this.constellationRepository.findByName(dto.getName());
            if (!this.validationUtil.isValid(dto) || optional.isPresent()) {
                sb.append("Invalid constellation").append(System.lineSeparator());
                continue;
            }
            Constellation constellation = this.modelMapper.map(dto, Constellation.class);
            this.constellationRepository.saveAndFlush(constellation);
            sb.append(String.format("Successfully imported constellation %s - %s%n",
                    constellation.getName(), constellation.getDescription()));

        }
        return sb.toString().trim();
    }
}
