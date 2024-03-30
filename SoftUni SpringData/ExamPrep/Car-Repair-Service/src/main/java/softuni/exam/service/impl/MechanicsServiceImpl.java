package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.MechanicsSeedDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class MechanicsServiceImpl implements MechanicsService {
    private static final String MECHANICS_FILE_PATH = "src/main/resources/files/json/mechanics.json";
    private final MechanicsRepository mechanicsRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.mechanicsRepository = mechanicsRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(MECHANICS_FILE_PATH)));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();
        MechanicsSeedDto[] mechanicsSeedDtos = this.gson.fromJson(readMechanicsFromFile(), MechanicsSeedDto[].class);
        for (MechanicsSeedDto dto : mechanicsSeedDtos) {
            Optional<Mechanic> optional = this.mechanicsRepository.findByEmail(dto.getEmail());
            if (!this.validationUtil.isValid(dto) || optional.isPresent()) {
                sb.append("Invalid mechanic").append(System.lineSeparator());
                continue;
            }
            Mechanic mechanic = this.modelMapper.map(dto, Mechanic.class);
            this.mechanicsRepository.saveAndFlush(mechanic);
            sb.append(String.format("Successfully imported mechanic %s %s%n",
                    mechanic.getFirstName(), mechanic.getLastName()));
        }
        return sb.toString().trim();
    }
}
