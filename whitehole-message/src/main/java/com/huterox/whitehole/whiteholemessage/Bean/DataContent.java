package com.huterox.whitehole.whiteholemessage.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * 由于我们这边Nutty处理的消息只有注册，所以话这里只需要
 * 保留action和userid即可
 * */
public class DataContent implements Serializable {
    private Integer action;
    private String userid;
}
