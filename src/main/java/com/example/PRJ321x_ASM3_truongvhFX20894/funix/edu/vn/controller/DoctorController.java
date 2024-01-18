package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.controller;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error.BadException;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error.NotFoundException;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.*;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.response.ApiSuccessResponse;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.*;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.security.Principal;
import java.util.List;

@RestController
public class DoctorController {
    private JwtUserDetailsService usersService;
    private CommentService commentsService;
    private ScheduleService scheduleService;
    private PatientService patientService;
    private ExtrainfosService extrainfosService;
    private JavaMailSender mailSender;

    @Autowired
    public DoctorController(JwtUserDetailsService usersService, JavaMailSender mailSender,
                            CommentService commentsService, ScheduleService scheduleService,
                            PatientService patientService, ExtrainfosService extrainfosService) {
        this.usersService = usersService;
        this.mailSender = mailSender;
        this.commentsService = commentsService;
        this.scheduleService = scheduleService;
        this.patientService = patientService;
        this.extrainfosService = extrainfosService;
    }

    @GetMapping("/patients")
    public ResponseEntity<?> patientList(HttpServletRequest request) {
        // Tìm thông tin bác sĩ đang sử dụng tài khoản
        Principal principal = request.getUserPrincipal();
        Users users = usersService.findUserByEmail(principal.getName());

        List<Patients> patientsList = patientService.findByDoctorId(users.getId());
        if (patientsList.size()==0) {
            throw new NotFoundException("Danh sách bệnh nhân hiện đang trống");
        }
        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(), "Danh sách bệnh nhân", patientsList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/doctor/schedule")
    public ResponseEntity<?> acceptSchedule(@RequestParam int commentId) {
        // Tìm thông tin về lịch khám mà bệnh nhân đã đăng ký
        Comments comments = commentsService.findById(commentId);
        if (comments.getStatus()==1) {
            throw new BadException("Lịch khám đã được xác nhận");
        }

        // Kiểm tra lịch khám tại thời điểm xác nhận đã đầy chưa
        Schedules oldSchedule = scheduleService.findByDoctorId(comments.getDoctor().getId(), comments.getTimeBooking(), comments.getDateBooking());
        if (oldSchedule==null) {
            // Nếu có giao diện thì phần này không cần thiết vì giá trị của oldSchedule sẽ ko thể null
            throw new BadException("Không có lịch trống tại thời điểm này");
        } else if (oldSchedule.getSumBooking()==oldSchedule.getMaxBooking()) {
            throw new BadException("Lịch khám tại thời điểm này đã đầy, không thể nhận thêm");
        }
        else {
            oldSchedule.setSumBooking(oldSchedule.getSumBooking()+1);
            oldSchedule.setUpdatedAt(java.util.Calendar.getInstance().getTime());
        }

        // Xác nhận lịch khám
        comments.setStatus(1);
        comments.setUpdatedAt(java.util.Calendar.getInstance().getTime());
        commentsService.save(comments);
        scheduleService.save(oldSchedule);

        return ResponseEntity.ok(new ApiSuccessResponse("success", HttpStatus.OK.value()));
    }

    @PostMapping("/cancelSchedule")
    public ResponseEntity<?> cancelSchedule(@RequestParam int commentId, @RequestParam String reason) {
        // Tìm thông tin về lịch khám mà bệnh nhân đã đăng ký
        Comments comments = commentsService.findById(commentId);

        // Hủy lịch khám
        comments.setUpdatedAt(java.util.Calendar.getInstance().getTime());
        comments.setContent(reason);
        commentsService.save(comments);

        return ResponseEntity.ok(new ApiSuccessResponse("success", HttpStatus.OK.value()));
    }

    // (Tạm thời) Bác sĩ tạo thông tin cơ bản về bệnh nhân
    @PostMapping("/doctor/patient")
    public ResponseEntity<?> createPatient(@RequestBody Patients patient) {
        patientService.save(patient);
        return ResponseEntity.ok("ok");
    }

    // (Tạm thời) Bác sĩ tạo thông tin về bệnh lý của bệnh nhân
    @PostMapping("/doctor/extrainfos")
    public ResponseEntity<?> createPatientInfo(@RequestBody Extrainfos extrainfos) {
        extrainfosService.save(extrainfos);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/doctor/sendInfo")
    public ResponseEntity<?> sendPatientInfoMail(@RequestParam int patientId, HttpServletRequest request,
                                                 @RequestParam String bodyMail, @RequestParam String fileLink) throws Exception {
        // Tìm email của bác sĩ
        Principal principal = request.getUserPrincipal();

        // Tìm bệnh nhân
        Patients patient = patientService.findById(patientId);
        sendMail(bodyMail, patient.getEmail(), fileLink);
        return ResponseEntity.ok("ok");
    }

    public void sendMail(String bodyMail, String email, String link) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("vuongtruong861998@gmail.com", "Doctor");
        helper.setTo(email);

        String subject = "Thông tin khám chữa bệnh";
        helper.setSubject(subject);
        helper.setText(bodyMail);

        // Tạo phần gửi file
        File file = new File(link);
        FileDataSource source = new FileDataSource(file);
        helper.addAttachment(file.getName(), source);

        mailSender.send(message);
    }
}
