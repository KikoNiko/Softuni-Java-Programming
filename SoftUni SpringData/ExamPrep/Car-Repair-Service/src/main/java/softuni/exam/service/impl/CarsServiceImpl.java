package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.CarsSeedDto;
import softuni.exam.models.dto.xmls.CarsSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.CarType;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class CarsServiceImpl implements CarsService {
    private static String CARS_FILE_PATH = "src/main/resources/files/xml/cars.xml";
    private final CarsRepository carsRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public CarsServiceImpl(CarsRepository carsRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.carsRepository = carsRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.carsRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(CARS_FILE_PATH)));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        CarsSeedRootDto rootDto = this.xmlParser.parse(CarsSeedRootDto.class, CARS_FILE_PATH);
        List<CarsSeedDto> carsSeedDtos = rootDto.getCars();
        for (CarsSeedDto dto : carsSeedDtos) {
            Optional<Car> optional = this.carsRepository.findByPlateNumber(dto.getPlateNumber());
            if (!this.validationUtil.isValid(dto) || optional.isPresent()) {
                sb.append("Invalid car").append(System.lineSeparator());
                continue;
            }
            Car car = this.modelMapper.map(dto, Car.class);
            car.setCarType(CarType.valueOf(dto.getCarType()));
            this.carsRepository.saveAndFlush(car);
            sb.append(String.format("Successfully imported car %s - %s%n",
                    car.getCarMake(), car.getCarModel()));
        }
        return sb.toString().trim();
    }
}
