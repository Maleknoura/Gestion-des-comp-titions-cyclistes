package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Cyclist;

import java.util.List;

public interface CyclistRepository extends JpaRepository<Cyclist,Long> {

}
