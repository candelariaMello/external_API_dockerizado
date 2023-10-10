package ar.edu.utn.frc.tup.lciii.Entities;

import ar.edu.utn.frc.tup.lciii.models.Price;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private String brand;
    private BigDecimal local_price;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
