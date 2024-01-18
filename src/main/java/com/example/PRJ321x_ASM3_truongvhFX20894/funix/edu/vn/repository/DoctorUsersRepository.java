package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.DoctorUsers;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorUsersRepository extends JpaRepository<DoctorUsers, Integer> {
}
