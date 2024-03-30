package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.StarSeedDto;
import softuni.exam.models.entity.Star;
import softuni.exam.models.enums.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private static final String FILE_IMPORT_PATH = "src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ConstellationRepository constellationRepository;

    public StarServiceImpl(StarRepository starRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.constellationRepository = constellationRepository;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();
        StarSeedDto[] starSeedDtos = this.gson.fromJson(readStarsFileContent(), StarSeedDto[].class);
        for (StarSeedDto dto : starSeedDtos) {
            Optional<Star> optional = this.starRepository.findByName(dto.getName());
            if (!this.validationUtil.isValid(dto) || optional.isPresent()) {
                sb.append("Invalid star").append(System.lineSeparator());
                continue;
            }
            Star star = this.modelMapper.map(dto, Star.class);
            star.setStarType(StarType.valueOf(dto.getStarType()));
            star.setConstellation(this.constellationRepository.getById(dto.getConstellation()));
            this.starRepository.saveAndFlush(star);
            sb.append(String.format("Successfully imported star %s - %.2f light years%n",
                    star.getName(), star.getLightYears()));
        }

        return sb.toString().trim();
    }

    @Override
    public String exportStars() {
        return this.starRepository.findAllByStarTypeAndObserversIsEmptyOrderByLightYears(StarType.RED_GIANT)
                .stream()
                .map(s -> String.format(
                        "Star: %s%n" +
                        "   *Distance: %.2f light years%n" +
                        "   **Description: %s%n" +
                        "   ***Constellation: %s%n",
                        s.getName(),
                        s.getLightYears(),
                        s.getDescription(),
                        s.getConstellation().getName()))
                .collect(Collectors.joining());
    }
}
