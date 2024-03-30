package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.CompaniesRootDto;
import softuni.exam.models.dto.xmls.CompanyInfoDto;
import softuni.exam.models.entity.Company;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/files/xml/companies.xml";
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, CountryRepository countryRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importCompanies() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        CompaniesRootDto companiesRootDto = this.xmlParser.parse(CompaniesRootDto.class, FILE_IMPORT_PATH);
        List<CompanyInfoDto> companyInfoDtos = companiesRootDto.getCompanyInfoDtoList();
        for (CompanyInfoDto dto : companyInfoDtos) {
            Optional<Company> byName = this.companyRepository.findByName(dto.getCompanyName());
            if (!this.validationUtil.isValid(dto) || byName.isPresent()) {
                sb.append("Invalid company\n");
                continue;
            }
            Company company = this.modelMapper.map(dto, Company.class);
            company.setDate(dto.getDateEstablished());
            company.setCountry(this.countryRepository.getById(dto.getCountryId()));
            this.companyRepository.saveAndFlush(company);
            sb.append(String.format("Successfully imported company %s - %d%n",
                    company.getName(), company.getCountry().getId()));
        }
        return sb.toString().trim();
    }
}
