package com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadMessageQ implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userid;
    @Max(200)
    private int max=100;

}
