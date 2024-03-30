package bg.softuni.cardealer.service.impls;

import bg.softuni.cardealer.data.entities.Car;
import bg.softuni.cardealer.data.entities.Customer;
import bg.softuni.cardealer.data.entities.Sale;
import bg.softuni.cardealer.data.repositories.CarRepository;
import bg.softuni.cardealer.data.repositories.CustomerRepository;
import bg.softuni.cardealer.data.repositories.SaleRepository;
import bg.softuni.cardealer.service.SaleService;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {
    private final List<Double> discounts = List.of(1.0, 0.95, 0.9, 0.85, 0.8, 0.7, 0.6, 0.5);
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository,
                           CarRepository carRepository,
                           CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSales() {
        if (this.saleRepository.count() == 0) {
            for (int i = 0; i < 50 ; i++) {
                Sale sale = new Sale();
                sale.setCar(getRandomCar());
                sale.setCustomer(getRandomCustomer());
                sale.setDiscount(getRandomDiscount());
                this.saleRepository.saveAndFlush(sale);
            }
        }
    }

    @Override
    public void exportSales() throws JAXBException {

    }

    private double getRandomDiscount() {
        return discounts.get(ThreadLocalRandom.current().nextInt(1, discounts.size()));
    }

    private Customer getRandomCustomer() {
        return this.customerRepository.findById(
                ThreadLocalRandom.current().nextLong(1, this.customerRepository.count() + 1)).get();
    }

    private Car getRandomCar() {
        return this.carRepository.findById(
                ThreadLocalRandom.current().nextLong(1, this.carRepository.count() + 1)).get();
    }
}
