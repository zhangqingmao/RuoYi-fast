package com.ruoyi.project.system.vehicle.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhangqm
 * @version 1.0
 * @email zqmyouxiang@foxmail.com
 * @date 2020/4/23 0023 14:34
 * @project RuoYi-fast
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysVehicle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆ID
     */
    private Integer carId;

    /**
     * 车辆编号
     */
    private String carCode;

    /**
     * 车辆状态
     */
    private Integer carStatus;

    /**
     * 车辆品牌
     */
    private String carName;

    /**
     * 删除状态
     */
    private Integer delFlag;

}
