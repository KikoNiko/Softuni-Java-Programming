package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.PartsSeedDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PartsServiceImpl implements PartsService {
    private static final String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";
    private final PartsRepository partsRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PartsServiceImpl(PartsRepository partsRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.partsRepository = partsRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PARTS_FILE_PATH)));
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder sb = new StringBuilder();
        PartsSeedDto[] partsSeedDtos = this.gson.fromJson(readPartsFileContent(), PartsSeedDto[].class);
        for (PartsSeedDto dto : partsSeedDtos) {
            Optional<Part> optional = this.partsRepository.findByPartName(dto.getPartName());
            if (!this.validationUtil.isValid(dto) || optional.isPresent()) {
                sb.append("Invalid part").append(System.lineSeparator());
                continue;
            }
            Part part = this.modelMapper.map(dto, Part.class);
            this.partsRepository.saveAndFlush(part);
            sb.append(String.format("Successfully imported part %s - %.2f%n",
                    part.getPartName(), part.getPrice()));
        }
        return sb.toString().trim();
    }
}
