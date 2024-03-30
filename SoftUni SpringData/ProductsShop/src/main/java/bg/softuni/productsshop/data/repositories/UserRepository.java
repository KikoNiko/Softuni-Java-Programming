package bg.softuni.productsshop.data.repositories;

import bg.softuni.productsshop.data.entities.User;
import bg.softuni.productsshop.services.dtos.exports.UserProductsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstNameAndLastNameOrderByLastName(String firstName, String lastName);


}
