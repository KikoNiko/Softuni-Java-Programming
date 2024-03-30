package bg.softuni.cardealer.service.impls;

import bg.softuni.cardealer.data.entities.Car;
import bg.softuni.cardealer.data.entities.Part;
import bg.softuni.cardealer.data.repositories.CarRepository;
import bg.softuni.cardealer.data.repositories.PartRepository;
import bg.softuni.cardealer.service.CarService;
import bg.softuni.cardealer.service.dtos.imports.CarSeedDto;
import bg.softuni.cardealer.service.dtos.imports.CarSeedRootDto;
import bg.softuni.cardealer.util.ValidationUtil;
import bg.softuni.cardealer.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CarServiceImpl implements CarService  {
    private static final String FILE_IMPORT_PATH = "src/main/resources/xml/cars.xml";
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public CarServiceImpl(CarRepository carRepository,
                          PartRepository partRepository,
                          ModelMapper modelMapper,
                          XmlParser xmlParser,
                          ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCars() throws JAXBException {
        if (this.carRepository.count() == 0) {
            CarSeedRootDto rootDto = this.xmlParser.parse(CarSeedRootDto.class, FILE_IMPORT_PATH);
            for (CarSeedDto carSeedDto : rootDto.getCarSeedDtoList()) {
                if (!this.validationUtil.isValid(carSeedDto)) {
                    this.validationUtil.getViolations(carSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }
                Car car = this.modelMapper.map(carSeedDto, Car.class);
                car.setParts(getRandomParts());

                this.carRepository.saveAndFlush(car);
            }
        }

    }

    private Set<Part> getRandomParts() {
        Set<Part> parts = new HashSet<>();
        int count = ThreadLocalRandom.current().nextInt(2, 4);
        for (int i = 0; i < count; i++) {
            parts.add(this.partRepository.
                    findById(ThreadLocalRandom.current().nextLong(1, this.partRepository.count() + 1))
                    .get());
        }
        return parts;
    }
}
