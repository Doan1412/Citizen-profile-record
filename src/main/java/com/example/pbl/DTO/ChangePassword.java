package com.example.pbl.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {
    private String citizen_id;
    private String oldPassword;
    private String newPassword;
}
