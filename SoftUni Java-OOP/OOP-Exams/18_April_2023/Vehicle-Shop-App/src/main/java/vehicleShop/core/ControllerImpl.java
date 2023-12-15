package vehicleShop.core;

import vehicleShop.models.shop.Shop;
import vehicleShop.models.shop.ShopImpl;
import vehicleShop.models.tool.Tool;
import vehicleShop.models.tool.ToolImpl;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.vehicle.VehicleImpl;
import vehicleShop.models.worker.FirstShift;
import vehicleShop.models.worker.SecondShift;
import vehicleShop.models.worker.Worker;
import vehicleShop.repositories.VehicleRepository;
import vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static vehicleShop.common.ConstantMessages.*;
import static vehicleShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private WorkerRepository workerRepository;
    private VehicleRepository vehicleRepository;
    private Shop shop;

    public ControllerImpl() {
        this.workerRepository = new WorkerRepository();
        this.vehicleRepository = new VehicleRepository();
        this.shop = new ShopImpl();
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        switch (type) {
            case "FirstShift":
                worker = new FirstShift(workerName);
                break;
            case "SecondShift":
                worker = new SecondShift(workerName);
                break;
            default:
                throw new IllegalArgumentException(WORKER_TYPE_DOESNT_EXIST);
        }
        workerRepository.add(worker);
        return String.format(ADDED_WORKER, type, workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        vehicleRepository.add(vehicle);
        return String.format(SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = workerRepository.findByName(workerName);
        if (worker == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Tool tool = new ToolImpl(power);
        worker.addTool(tool);
        return String.format(SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> readyToWork = workerRepository.getWorkers()
                .stream()
                .filter(w -> w.getStrength() > 70)
                .collect(Collectors.toList());

        if (readyToWork.isEmpty()) {
            throw new IllegalArgumentException(NO_WORKER_READY);
        }

        Vehicle vehicle = vehicleRepository.findByName(vehicleName);
        int brokenTools = 0;
        for (Worker worker : readyToWork) {
            shop.make(vehicle, worker);
            int countBroken = (int) worker.getTools().stream().filter(Tool::isUnfit).count();
            brokenTools += countBroken;
            if (vehicle.reached()) break;
        }
        if (vehicle.reached()) {
            return String.format(VEHICLE_DONE + COUNT_BROKEN_INSTRUMENTS, vehicleName, "done", brokenTools);
        }
        return String.format(VEHICLE_DONE + COUNT_BROKEN_INSTRUMENTS, vehicleName, "not done", brokenTools);
    }

    @Override
    public String statistics() {
        int countMadeVehicles = (int) vehicleRepository.getWorkers().stream().filter(Vehicle::reached).count();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!%n", countMadeVehicles));
        sb.append("Info for workers:").append(System.lineSeparator());
        for (Worker worker : workerRepository.getWorkers()) {
            sb.append(String.format("Name: %s, Strength: %d%n", worker.getName(), worker.getStrength()));
            sb.append(String.format("Tools: %d fit left%n", worker.getTools().stream().filter(t -> !t.isUnfit()).count()));
        }

        return sb.toString().trim();
    }
    //"{countMadeVehicle} vehicles are ready!
    //Info for workers:
    //Name: {workerName1}, Strength: {workerStrength1}
    //Tools: {countTools} fit left"
}
