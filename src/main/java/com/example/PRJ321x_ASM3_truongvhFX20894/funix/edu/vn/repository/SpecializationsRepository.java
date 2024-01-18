package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationsRepository extends JpaRepository<Specializations, Integer> {
    @Query(value = "SELECT s FROM Specializations s WHERE "+
            "s.name LIKE CONCAT('%',:keyword,'%')"+
            "Or s.description LIKE CONCAT('%',:keyword,'%')")
    List<Specializations> searchSpecializations(String keyword);
}
