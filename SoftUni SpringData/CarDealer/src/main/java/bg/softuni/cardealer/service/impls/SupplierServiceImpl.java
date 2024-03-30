package bg.softuni.cardealer.service.impls;

import bg.softuni.cardealer.data.entities.Supplier;
import bg.softuni.cardealer.data.repositories.SupplierRepository;
import bg.softuni.cardealer.service.SupplierService;
import bg.softuni.cardealer.service.dtos.imports.SupplierSeedDto;
import bg.softuni.cardealer.service.dtos.imports.SupplierSeedRootDto;
import bg.softuni.cardealer.util.ValidationUtil;
import bg.softuni.cardealer.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/xml/suppliers.xml";
    private final SupplierRepository supplierRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSuppliers() throws JAXBException {
        if (this.supplierRepository.count() == 0) {
            SupplierSeedRootDto supplierSeedRootDto = xmlParser.parse(SupplierSeedRootDto.class, FILE_IMPORT_PATH);
            for (SupplierSeedDto supplierSeedDto : supplierSeedRootDto.getSupplierSeedDtoList()) {
                if (!this.validationUtil.isValid(supplierSeedDto)) {
                    this.validationUtil.getViolations(supplierSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }
                Supplier supplier = this.modelMapper.map(supplierSeedDto, Supplier.class);
                this.supplierRepository.saveAndFlush(supplier);
            }
        }

    }
}
