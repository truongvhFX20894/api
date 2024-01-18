package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicsRepository extends JpaRepository<Clinics, Integer> {
    @Query(value = "SELECT c FROM Clinics c WHERE "+
            "c.name LIKE CONCAT('%',:keyword,'%')"+
            "Or c.description LIKE CONCAT('%',:keyword,'%')")
    List<Clinics> searchClinics(String keyword);

    @Query(value = "SELECT c FROM Clinics c WHERE "+
            "c.address LIKE CONCAT('%',:keyword,'%')")
    List<Clinics> searchByAddress(String keyword);
}
