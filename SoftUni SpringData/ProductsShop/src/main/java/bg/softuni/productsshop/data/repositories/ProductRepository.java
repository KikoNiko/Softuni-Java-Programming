package bg.softuni.productsshop.data.repositories;

import bg.softuni.productsshop.data.entities.Product;
import bg.softuni.productsshop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal from, BigDecimal to);

    Set<Product> findAllBySellerFirstNameAndSellerLastNameAndBuyerIsNotNull(String sellerFirstName, String sellerLastName);

    Set<Product> findAllByBuyerIsNotNull();
}
