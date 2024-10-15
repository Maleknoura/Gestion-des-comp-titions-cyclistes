package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Cyclist;

public interface CyclistRepository extends JpaRepository<Cyclist,Long> {
    Cyclist save(Cyclist cyclist);
}
