package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
//    @Query(value = "SELECT c.doctor.specializations FROM Comments c " +
//            "GROUP BY c.doctor.specializations ORDER BY COUNT(c.id) DESC LIMIT 10")
//    public List<Specializations> findTopSpecializations();
//
//    @Query(value = "SELECT c.doctor.clinics FROM Comments c " +
//            "GROUP BY c.doctor.clinics ORDER BY COUNT(c.id) DESC LIMIT 10")
//    public List<Clinics> findTopClinics();
}
