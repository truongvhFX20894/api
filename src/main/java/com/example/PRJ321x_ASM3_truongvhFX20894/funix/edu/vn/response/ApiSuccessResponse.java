package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiSuccessResponse {
    private String message;
    private int status;
    private String description;
    private Object data;

    public ApiSuccessResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
