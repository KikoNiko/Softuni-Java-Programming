package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.PersonImportDto;
import softuni.exam.models.entity.Person;
import softuni.exam.models.entity.StatusType;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/files/json/people.json";
    private final PersonRepository personRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public PersonServiceImpl(PersonRepository personRepository, CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.personRepository = personRepository;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importPeople() throws IOException{
        StringBuilder sb = new StringBuilder();
        PersonImportDto[] personImportDtos = this.gson.fromJson(readPeopleFromFile(), PersonImportDto[].class);
        for (PersonImportDto dto : personImportDtos) {
            Optional<Person> byFirstName = this.personRepository.findByFirstName(dto.getFirstName());
            Optional<Person> byPhone = this.personRepository.findByPhone(dto.getPhone());
            Optional<Person> byEmail = this.personRepository.findByEmail(dto.getEmail());
            if (!this.validationUtil.isValid(dto) || byFirstName.isPresent() || byEmail.isPresent() || byPhone.isPresent()) {
                sb.append("Invalid person\n");
                continue;
            }
            Person person = this.modelMapper.map(dto, Person.class);
            person.setStatusType(StatusType.valueOf(dto.getStatusType()));
            person.setCountry(this.countryRepository.getById(Long.parseLong(dto.getCountry())));
            this.personRepository.saveAndFlush(person);

            sb.append(String.format("Successfully imported person %s - %s%n",
                    person.getFirstName(), person.getLastName()));
        }

        return sb.toString().trim();
    }
}
