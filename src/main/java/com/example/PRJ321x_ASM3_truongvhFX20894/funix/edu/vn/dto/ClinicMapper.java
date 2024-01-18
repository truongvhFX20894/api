package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;

public class ClinicMapper {
    private static ClinicMapper INSTANCE;
    public static ClinicMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClinicMapper();
        }
        return INSTANCE;
    }
    public Clinics toEntity(ClinicDTO dto) {
        Clinics clinics = new Clinics();
        clinics.setName(dto.getName());
        clinics.setAddress(dto.getAddress());
        clinics.setPhone(dto.getPhone());
        clinics.setIntroduceHTML(dto.getIntroduceHTML());
        clinics.setIntroduceMarkdown(dto.getIntroduceMarkdown());
        clinics.setDescription(dto.getDescription());
        clinics.setImage(dto.getImage());
        return clinics;
    }
    public ClinicDTO toDTO(Clinics clinics) {
        ClinicDTO dto = new ClinicDTO();
        dto.setName(clinics.getName());
        dto.setAddress(clinics.getAddress());
        dto.setPhone(clinics.getPhone());
        dto.setIntroduceHTML(clinics.getIntroduceHTML());
        dto.setIntroduceMarkdown(clinics.getIntroduceMarkdown());
        dto.setDescription(dto.getDescription());
        dto.setImage(clinics.getImage());
//        dto.setRoles(user.getRoles().stream()
//                .map(role -> RoleMapper.getInstance().toDTO(role))
//                .collect(Collectors.toList()));
        return dto;
    }
}
