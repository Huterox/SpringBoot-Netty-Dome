package com.huterox.whitehole.whiteholemessage.entity.surface.loadMessage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadMessageR implements Serializable {
    private static final long serialVersionUID = 1L;

    private List list;

    private int size;
}
