package ar.edu.utn.frc.tup.lciii.models;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Data
public class ExchangeRates {
    @Nullable
    private Date date;
    private String from;
    private String to;
    private BigDecimal exchange_rate;
}
