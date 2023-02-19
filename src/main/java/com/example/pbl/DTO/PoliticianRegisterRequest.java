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
    Long citizen_id;
    String position;//chuc danh
    String areaManage;
    @NotNull
    String levelManager;
}
