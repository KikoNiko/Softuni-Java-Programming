package entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_location_id")
    private StoreLocation storeLocation;

    @Column
    private Date date;
}
