package bg.softuni.cardealer.service.impls;

import bg.softuni.cardealer.data.entities.Part;
import bg.softuni.cardealer.data.entities.Supplier;
import bg.softuni.cardealer.data.repositories.PartRepository;
import bg.softuni.cardealer.data.repositories.SupplierRepository;
import bg.softuni.cardealer.service.PartService;
import bg.softuni.cardealer.service.dtos.imports.PartSeedDto;
import bg.softuni.cardealer.service.dtos.imports.PartSeedRootDto;
import bg.softuni.cardealer.util.ValidationUtil;
import bg.softuni.cardealer.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/xml/parts.xml";
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public PartServiceImpl(PartRepository partRepository,
                           SupplierRepository supplierRepository,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil,
                           XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedParts() throws JAXBException {
        if (this.partRepository.count() == 0) {
            PartSeedRootDto rootDto = this.xmlParser.parse(PartSeedRootDto.class, FILE_IMPORT_PATH);
            for (PartSeedDto partSeedDto : rootDto.getPartSeedDtoList()) {
                if (!this.validationUtil.isValid(partSeedDto)) {
                    this.validationUtil.getViolations(partSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }
                Part part = this.modelMapper.map(partSeedDto, Part.class);
                part.setSupplier(getRandomSupplier());

                this.partRepository.saveAndFlush(part);
            }
        }

    }

    private Supplier getRandomSupplier() {
        return this.supplierRepository
                .findById(ThreadLocalRandom.current().nextLong(1, this.supplierRepository.count() + 1))
                .get();
    }
}
