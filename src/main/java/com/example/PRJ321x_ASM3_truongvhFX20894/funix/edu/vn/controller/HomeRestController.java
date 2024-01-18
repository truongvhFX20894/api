package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.controller;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.SpecializationsDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.UsersDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.CommentDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error.BadException;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error.NotFoundException;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.*;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.response.ApiSuccessResponse;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class HomeRestController {
    private ClinicsService clinicsService;
    private SpecializationsService specializationsService;
    private JwtUserDetailsService usersService;
    private DoctorUsersService doctorUsersService;
    private CommentService commentService;

    @Autowired
    public HomeRestController(ClinicsService clinicsService, SpecializationsService specializationsService,
                              JwtUserDetailsService usersService, DoctorUsersService doctorUsersService,
                              CommentService commentService) {
        this.clinicsService = clinicsService;
        this.specializationsService = specializationsService;
        this.usersService = usersService;
        this.doctorUsersService = doctorUsersService;
        this.commentService = commentService;
    }

    @GetMapping("/clinic")
    public ResponseEntity<ApiSuccessResponse> getProminentClinics() throws Exception {
        // Lấy danh sách các cơ sở y tế
        List<Clinics> clinicsList = clinicsService.findAll();
        List<ClinicDTO> clinicDTOS = clinicsService.findAllDTO();

        // Tính toán số lượng lịch đặt khám của mỗi chuyên khoa
        List<Integer> num = new ArrayList<>();
        for (Clinics clinic: clinicsList) {
            int i = 0;
            List<DoctorUsers> doctorUsersList = clinic.getDoctors();
            for (DoctorUsers doc:doctorUsersList) {
                i = i+ doc.getComments().size();
            }
            num.add(i);
        }
        // Sắp xếp chuyên khoa theo số lượng lịch đặt khám
        for (int i=0; i<num.size();i++) {
            for (int j=1; j<num.size();j++) {
                if (num.get(i)<num.get(j)) {
                    // đổi chỗ vị trí phần tử trong list num
                    int n = num.get(i);
                    num.set(i, num.get(j));
                    num.set(j, n);
                    // đổi chỗ vị trí tương ứng trong list danh sách chuyên khoa
                    ClinicDTO s = clinicDTOS.get(i);
                    clinicDTOS.set(i, clinicDTOS.get(j));
                    clinicDTOS.set(j, s);
                }
            }
        }

        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(),
                "Các cơ sở y tế nổi bật", clinicDTOS);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/specialization")
    public ResponseEntity<ApiSuccessResponse> getProminentSpecializations() throws Exception {
        // Lấy danh sách các chuyên khoa
        List<Specializations> specializationsList = specializationsService.findAll();
        List<SpecializationsDTO> specializationsDTOS = specializationsService.findAllDTO();

        // Tính toán số lượng lịch đặt khám của mỗi chuyên khoa
        List<Integer> num = new ArrayList<>();
        for (Specializations spec: specializationsList) {
            int i = 0;
            List<DoctorUsers> doctorUsersList = spec.getDoctors();
            for (DoctorUsers doc:doctorUsersList) {
                i = i+ doc.getComments().size();
            }
            num.add(i);
        }
        // Sắp xếp chuyên khoa theo số lượng lịch đặt khám
        for (int i=0; i<num.size();i++) {
            for (int j=1; j<num.size();j++) {
                if (num.get(i)<num.get(j)) {
                    // đổi chỗ vị trí phần tử trong list num
                    int n = num.get(i);
                    num.set(i, num.get(j));
                    num.set(j, n);
                    // đổi chỗ vị trí tương ứng trong list danh sách chuyên khoa
                    SpecializationsDTO s = specializationsDTOS.get(i);
                    specializationsDTOS.set(i, specializationsDTOS.get(j));
                    specializationsDTOS.set(j, s);
                }
            }
        }

        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(),
                "Các chuyên khoa nổi bật", specializationsDTOS);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<ApiSuccessResponse> userInfo(HttpServletRequest request) {
        // lấy thông tin user cần thiết và truyền vào map
        Principal principal = request.getUserPrincipal();
        UsersDTO user = usersService.findUserDTOByEmail(principal.getName());

        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(),
                "Thông tin của người sử dụng", user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/book")
    public ResponseEntity<?> scheduleExamination(@RequestBody CommentDTO dto) {
        // lưu thông tin lịch khám
        Comments comments = new Comments();
        comments.setDoctor(doctorUsersService.findById(dto.getDoctorId()));
        comments.setTimeBooking(dto.getHour());
        comments.setDateBooking(dto.getDate());
        comments.setPrice(dto.getPrice());
        comments.setName(dto.getName());
        comments.setGender(dto.getGender());
        comments.setBirthday(dto.getBirthday());
        comments.setContent(dto.getReason());
        comments.setPhone(dto.getPhone());
        comments.setAddress(dto.getAddress());
        comments.setStatus(0); // trạng thái 0 là trạng thái chưa được bác sĩ xác nhận
        commentService.save(comments);

        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(), "Thông tin đăng ký", dto);
        return ResponseEntity.ok(response);
    }
}
