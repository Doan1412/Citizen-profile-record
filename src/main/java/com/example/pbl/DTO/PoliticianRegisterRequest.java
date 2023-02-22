package com.example.pbl.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliticianRegisterRequest {
    private Long citizen_id;
    private String position;//chuc danh
    private String areaManage;
    @NotNull
    private String levelManager;
}
