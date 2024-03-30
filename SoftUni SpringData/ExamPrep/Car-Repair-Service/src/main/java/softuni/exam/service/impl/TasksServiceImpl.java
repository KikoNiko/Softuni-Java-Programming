package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.TasksSeedDto;
import softuni.exam.models.dto.xmls.TasksSeedRootDto;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TasksService {
    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";
    private final TasksRepository tasksRepository;
    private final MechanicsRepository mechanicsRepository;
    private final CarsRepository carsRepository;
    private final PartsRepository partsRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public TasksServiceImpl(TasksRepository tasksRepository, MechanicsRepository mechanicsRepository, CarsRepository carsRepository, PartsRepository partsRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.tasksRepository = tasksRepository;
        this.mechanicsRepository = mechanicsRepository;
        this.carsRepository = carsRepository;
        this.partsRepository = partsRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.tasksRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(TASKS_FILE_PATH)));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        TasksSeedRootDto rootDto = this.xmlParser.parse(TasksSeedRootDto.class, TASKS_FILE_PATH);
        List<TasksSeedDto> tasksSeedDtoList = rootDto.getTasksSeedDtoList();
        for (TasksSeedDto dto : tasksSeedDtoList) {
            Optional<Mechanic> optionalMechanic = this.mechanicsRepository.findByFirstName(dto.getMechanic().getFirstName());
            Optional<Car> optionalCar = this.carsRepository.findById(dto.getCar().getId());
            Optional<Part> optionalPart = this.partsRepository.findById(dto.getPart().getId());
            if (!this.validationUtil.isValid(dto) || optionalMechanic.isEmpty() || optionalCar.isEmpty() || optionalPart.isEmpty()) {
                sb.append("Invalid task").append(System.lineSeparator());
                continue;
            }
            Task task = this.modelMapper.map(dto, Task.class);
            task.setCar(optionalCar.get());
            task.setMechanic(optionalMechanic.get());
            task.setPart(optionalPart.get());
            this.tasksRepository.saveAndFlush(task);
            sb.append(String.format("Successfully imported task %.2f%n", task.getPrice().doubleValue()));
        }

        return sb.toString().trim();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return this.tasksRepository.findAllByOrderByPriceDesc()
                .stream()
                .filter(t -> t.getCar() != null && t.getCar().getCarType().equals(CarType.coupe))
                .map(t -> String.format("Car %s %s with %dkm%n" +
                        "-Mechanic: %s %s - task №%d:%n" +
                        " --Engine: %.1f%n" +
                        "---Price: %.2f$%n",
                        t.getCar().getCarMake(),
                        t.getCar().getCarModel(),
                        t.getCar().getKilometers(),
                        t.getMechanic().getFirstName(),
                        t.getMechanic().getLastName(),
                        t.getId(),
                        t.getCar().getEngine(),
                        t.getPrice()
                        ))
                .collect(Collectors.joining());

    }
}
