package com.simple_api.backend.components.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SingleAttrDTO {
    private String attr0;

    public SingleAttrDTO(
        String attr0
    ){
        this.attr0 = attr0;
    }
}
