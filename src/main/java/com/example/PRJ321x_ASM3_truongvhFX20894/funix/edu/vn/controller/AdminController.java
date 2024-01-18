package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.controller;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ScheduleDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ScheduleMapper;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Schedules;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Users;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.response.ApiSuccessResponse;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.CommentService;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.JwtUserDetailsService;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private JwtUserDetailsService userService;
    private CommentService commentService;
    private ScheduleService scheduleService;

    @Autowired
    public AdminController(JwtUserDetailsService userService, CommentService commentService, ScheduleService scheduleService) {
        this.userService = userService;
        this.commentService = commentService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/lockAcc")
    public ResponseEntity<?> lockUserAccount(@RequestParam int userId, @RequestParam String reason) {
        Users user = userService.findUserByUserId(userId);

        // Cài đặt lại trạng thái tài khoản thành khóa và lí do khóa
        user.setIsActive(0);
        user.setDescription(reason);
        user.setUpdatedAt(java.util.Calendar.getInstance().getTime());

        return ResponseEntity.ok(userService.update(user));
    }

    @PostMapping("/unlockAcc")
    public ResponseEntity<?> unlockUserAccount(@RequestParam int userId) {
        Users user = userService.findUserByUserId(userId);

        // Cài đặt lại trạng thái tài khoản thành mở
        user.setIsActive(1);
        user.setDescription(null);
        user.setUpdatedAt(java.util.Calendar.getInstance().getTime());

        return ResponseEntity.ok(userService.update(user));
    }

    @GetMapping("/schedule/patient/{patientId}")
    public ResponseEntity<?> patientSchedule(@PathVariable int patientId) {

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/schedule/doctor/{doctorId}")
    public ResponseEntity<?> doctorSchedule(@PathVariable int doctorId) {
        // Lấy danh sách lịch làm việc của bác sĩ theo id
        List<Schedules> schedules = scheduleService.findByDoctorId(doctorId);
        List<ScheduleDTO> dtoList = new ArrayList<>();

        // chuyển danh sách sang dạng dto để xuất ra thông tin
        for (Schedules s: schedules) {
            dtoList.add(ScheduleMapper.getInstance().toDTO(s));
        }
        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(), "Lịch làm việc của bác sĩ: ", dtoList);
        return ResponseEntity.ok(response);
    }

}
