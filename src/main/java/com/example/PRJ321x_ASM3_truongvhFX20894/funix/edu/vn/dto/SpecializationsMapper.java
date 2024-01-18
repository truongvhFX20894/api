package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.dto;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Specializations;

public class SpecializationsMapper {
    private static SpecializationsMapper INSTANCE;
    public static SpecializationsMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpecializationsMapper();
        }
        return INSTANCE;
    }
    public Specializations toEntity(SpecializationsDTO dto) {
        Specializations specializations = new Specializations();
        specializations.setName(dto.getName());
        specializations.setDescription(dto.getDescription());
        specializations.setImage(dto.getImage());
        return specializations;
    }
    public SpecializationsDTO toDTO(Specializations specializations) {
        SpecializationsDTO dto = new SpecializationsDTO();
        dto.setName(specializations.getName());
        dto.setDescription(specializations.getDescription());
        dto.setImage(specializations.getImage());
//        dto.setRoles(user.getRoles().stream()
//                .map(role -> RoleMapper.getInstance().toDTO(role))
//                .collect(Collectors.toList()));
        return dto;
    }
}
