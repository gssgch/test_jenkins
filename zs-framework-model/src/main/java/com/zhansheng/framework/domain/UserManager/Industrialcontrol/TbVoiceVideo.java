package com.zhansheng.framework.domain.UserManager.Industrialcontrol;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

@Data
public class TbVoiceVideo {
    private BigInteger id;

    private Integer fannum;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private String voiceName;

    private String voiceUrl;

    private Integer voiceState;

    private String videoName;

    private String videoUrl;

    private Integer videoState;

    private String vibrationName;

    private String vibration;

    private Integer vibrationState;

}