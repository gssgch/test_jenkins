package com.zhansheng.framework.domain.FaultDetection;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@ToString
@Document(collection = "tb_state")
public class TbState {

    @Id
    private String id;
    private Integer fDraughtNumber;
    private String fCreateTime;
    // 叶根螺栓
    private Integer fState1;
    // 叶片结冰
    private Integer fState2;
    // 偏航对风优化
    private Integer fState4;
    // 振动
    private Integer fState3;
    // 音视频
    private Integer fState5;




    private int f;

    //    private Double fProgressState4;

//    private Double fProgressState1;

//    private Double fProgressState2;
// 机组运行劣化
//private Integer fState3;
//private Double fProgressState3;

}
