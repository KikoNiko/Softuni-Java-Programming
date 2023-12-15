package robotService.core;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private SupplementRepository supplements;
    private Map<String, Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service = null;
        switch (type) {
            case "MainService":
                service = new MainService(name);
                break;
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            default:
                throw new NullPointerException(
                        ExceptionMessages.INVALID_SERVICE_TYPE
                );
        }
        this.services.put(name, service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement = null;
        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new IllegalArgumentException (
                        ExceptionMessages.INVALID_SUPPLEMENT_TYPE
                );
        }
        this.supplements.addSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = this.supplements.findFirst(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType)
            );
        }
        this.services.get(serviceName).addSupplement(supplement);
        this.supplements.removeSupplement(supplement);

        return String.format(
                ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName
        );
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot = null;
        switch (robotType) {
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(
                        ExceptionMessages.INVALID_ROBOT_TYPE
                );
        }
        if (typeMatchesService(robotType, serviceName)) {
            this.services.get(serviceName).addRobot(robot);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
        }

        return ConstantMessages.UNSUITABLE_SERVICE;
    }

    private boolean typeMatchesService(String robotType, String serviceName) {
        Service service = services.get(serviceName);
        String serviceType = service.getClass().getSimpleName();
        if (robotType.equals("MaleRobot") && serviceType.equals("MainService")) {
            return true;
        } else if (robotType.equals("FemaleRobot") && serviceType.equals("SecondaryService")) {
            return true;
        }
        return false;
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = this.services.get(serviceName);
        service.feeding();
        return String.format(ConstantMessages.FEEDING_ROBOT, service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = this.services.get(serviceName);
        double sumRobotPrices = service.getRobots()
                .stream()
                .mapToDouble(Robot::getPrice)
                .sum();
        double sumSupplementPrice = service.getSupplements()
                .stream()
                .mapToDouble(Supplement::getPrice)
                .sum();

        return String.format(ConstantMessages.VALUE_SERVICE, serviceName, sumRobotPrices + sumSupplementPrice);
    }

    @Override
    public String getStatistics() {
        return this.services.values()
                .stream()
                .map(Service::getStatistics)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
