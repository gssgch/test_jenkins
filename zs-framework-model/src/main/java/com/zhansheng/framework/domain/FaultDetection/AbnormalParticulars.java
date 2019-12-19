package com.zhansheng.framework.domain.FaultDetection;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author
 * @description 风机模型  故障  如果是新风机数据不够调用
 * @date 2019/8/19
 */

/*@Data
@ToString
@Document(collection = "t_particulars_c222")*/
public class AbnormalParticulars  {
    //mongoDB主键id
    @Id
    private String id;
    // 风机编号
    private Integer fDraughtNumber;
    //时间
    private String fDateAcqTime;
    // 有功功率
    private Integer fGridPower;
    // 发电机转速
    private Integer fGenSpd;
    // 30秒平均风速
    private Double fWindSpd30S;
    // 瞬时风速
    private Double fWindSpeed;
    // 风向角
    private Double fWindDirect;
    // 60秒平均风向角
    private Double fWindDir60s;
    // 10m平均风向角
    private Double fWindDir10m;
    // 桨距角
    private Double fPitPos123;
    // 舱外温度
    private Double fTOutdVisu;
    // 10m舱外温度
    private Double fTOutd10m;
    // 轮毂温度
    private Double fTHubVisu;
    // 机舱湿度
    private Double fNacelleHumidity;
    // 机舱振动有效值
    private Double fVibEffecVal;
    // 机舱振动传感器X
    private Double fNaceVibX;
    // 机舱振动传感器Y
    private Double fNaceVibY;
    // 电机温度1
    private Double fTPitMotorVisu1;
    // 电机温度2
    private Double fTPitMotorVisu2;
    // 电机温度3
    private Double fTPitMotorVisu3;
    // 电机电流1
    private Double fPitDrvI1;
    // 电机电流2
    private Double fPitDrvI2;
    // 电机电流3
    private Double fPitDrvI3;

    //月发电量
    private Integer fGriEnerm;




}
