package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();
        CountryImportDto[] countryImportDtos = this.gson.fromJson(readCountriesFileContent(), CountryImportDto[].class);
        for (CountryImportDto dto : countryImportDtos) {
            if (!this.validationUtil.isValid(dto)) {
                sb.append("Invalid country\n");
                continue;
            }
            Country country = this.modelMapper.map(dto, Country.class);
            country.setCode(dto.getCountryCode());
            this.countryRepository.saveAndFlush(country);
            sb.append(String.format("Successfully imported country %s - %s%n",
                    country.getName(), country.getCode()));
        }

        return sb.toString().trim();
    }
}
