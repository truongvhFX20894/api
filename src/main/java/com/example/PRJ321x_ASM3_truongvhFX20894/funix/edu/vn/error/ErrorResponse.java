package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
