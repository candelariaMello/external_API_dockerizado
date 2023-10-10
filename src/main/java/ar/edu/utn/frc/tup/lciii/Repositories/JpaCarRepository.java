package ar.edu.utn.frc.tup.lciii.Repositories;

import ar.edu.utn.frc.tup.lciii.Entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCarRepository extends JpaRepository<CarEntity, Long> {
}
