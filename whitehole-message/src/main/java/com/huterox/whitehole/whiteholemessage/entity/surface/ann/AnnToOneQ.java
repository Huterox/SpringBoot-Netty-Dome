package com.huterox.whitehole.whiteholemessage.entity.surface.ann;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnToOneQ implements Serializable {

    private String userid;
    private String toUserid;
    private String Msg;
}
