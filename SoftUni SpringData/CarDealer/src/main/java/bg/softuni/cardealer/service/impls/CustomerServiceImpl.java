package bg.softuni.cardealer.service.impls;

import bg.softuni.cardealer.data.entities.Customer;
import bg.softuni.cardealer.data.repositories.CustomerRepository;
import bg.softuni.cardealer.service.CustomerService;
import bg.softuni.cardealer.service.dtos.exports.CustomerOrderedDto;
import bg.softuni.cardealer.service.dtos.exports.CustomerOrderedRootDto;
import bg.softuni.cardealer.service.dtos.imports.CustomerSeedRootDto;
import bg.softuni.cardealer.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/xml/customers.xml";
    private static final String FILE_EXPORT_CUSTOMERS_PATH = "src/main/resources/xml/exports/ordered-customers.xml";
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ModelMapper modelMapper,
                               XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers() throws JAXBException {
        if (this.customerRepository.count() == 0) {
            CustomerSeedRootDto rootDto = this.xmlParser.parse(CustomerSeedRootDto.class, FILE_IMPORT_PATH);
            rootDto.getCustomerSeedDtoList()
                    .stream()
                    .forEach(c -> this.customerRepository.saveAndFlush(this.modelMapper.map(c, Customer.class)));
        }
    }

    @Override
    public void exportOrderedCustomers() throws JAXBException {
        List<CustomerOrderedDto> customerOrderedDtos = this.customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerOrderedDto.class))
                .collect(Collectors.toList());

        CustomerOrderedRootDto customerOrderedRootDto = new CustomerOrderedRootDto();
        customerOrderedRootDto.setCustomerOrderedDtoList(customerOrderedDtos);

        this.xmlParser.exportToFile(CustomerOrderedRootDto.class, customerOrderedRootDto, FILE_EXPORT_CUSTOMERS_PATH);
    }
}
