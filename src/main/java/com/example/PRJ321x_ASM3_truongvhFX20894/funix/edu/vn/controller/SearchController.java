package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.controller;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.ClinicMapper;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.SpecializationsDTO;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto.SpecializationsMapper;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error.NotFoundException;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Specializations;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.response.ApiSuccessResponse;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.ClinicsService;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service.SpecializationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SearchController {
    private ClinicsService clinicsService;
    private SpecializationsService specializationsService;

    @Autowired
    public SearchController(ClinicsService clinicsService, SpecializationsService specializationsService) {
        this.clinicsService = clinicsService;
        this.specializationsService = specializationsService;
    }


    @GetMapping("/timkiem")
    public ResponseEntity<?> searchByRegion(@RequestParam("tukhoa") String keyword,
                                                               @RequestParam("loai") String loai) {
        if (Objects.equals(loai, "khuvuc")) {
            return searchRegion(keyword);
        } else if (Objects.equals(loai, "cosoyte")) {
            return searchFacilities(keyword);
        } else if (Objects.equals(loai, "chuyenkhoa")) {
            return searchSpecialization(keyword);
        } else if (Objects.equals(loai, "gia")) {
            return searchPrice(keyword);
        }
        else {
            throw new NotFoundException("Phân loại tìm kiếm không phù hợp");
        }
    }

    public ResponseEntity<?> searchRegion(String keyword) {
        // Lấy thông tin về các phòng khám tại khu vực theo keyword
        List<Clinics> clinics = clinicsService.searchByAddress(keyword);

        // Trả về thông báo nếu không tìm thấy kết quả
        if (clinics.size()==0) {
            throw new NotFoundException("Không tìm thấy kết quả thỏa mãn từ khóa "+keyword);
        }

        // Trả về danh sách phòng khám
        List<ClinicDTO> dtoList = new ArrayList<>();
        for (Clinics clinic: clinics) {
            dtoList.add(ClinicMapper.getInstance().toDTO(clinic));
        }
        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(), "Kết quả tìm kiếm", dtoList);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> searchFacilities(String keyword) {
        // Lấy thông tin về các cơ sở y tế theo keyword
        List<Clinics> clinics = clinicsService.searchClinics(keyword);

        // Trả về thông báo nếu không tìm thấy kết quả
        if (clinics.size()==0) {
            throw new NotFoundException("Không tìm thấy kết quả thỏa mãn từ khóa "+keyword);
        }

        // Trả về danh sách phòng khám
        List<ClinicDTO> dtoList = new ArrayList<>();
        for (Clinics clinic: clinics) {
            dtoList.add(ClinicMapper.getInstance().toDTO(clinic));
        }
        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(), "Kết quả tìm kiếm", dtoList);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> searchSpecialization(String keyword) {
        // Lấy thông tin về các cơ sở y tế theo keyword
        List<Specializations> specializations = specializationsService.searchSpecializations(keyword);

        // Trả về thông báo nếu không tìm thấy kết quả
        if (specializations.size()==0) {
            throw new NotFoundException("Không tìm thấy kết quả thỏa mãn từ khóa "+keyword);
        }

        // Trả về danh sách phòng khám
        List<SpecializationsDTO> dtoList = new ArrayList<>();
        for (Specializations spec: specializations) {
            dtoList.add(SpecializationsMapper.getInstance().toDTO(spec));
        }
        ApiSuccessResponse response = new ApiSuccessResponse("success", HttpStatus.OK.value(), "Kết quả tìm kiếm", dtoList);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> searchPrice(String keyword) {
        return ResponseEntity.ok("chưa hoàn thiện");
    }


}
