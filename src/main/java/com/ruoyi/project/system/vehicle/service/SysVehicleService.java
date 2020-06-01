package com.ruoyi.project.system.vehicle.service;

import com.ruoyi.project.system.vehicle.domain.SysVehicle;
import java.util.List;

/**
 * @author zhangqm
 * @version 1.0
 * @email zqmyouxiang@foxmail.com
 * @date 2020/4/20 0020 15:18
 * @project RuoYi-fast
 */
public interface SysVehicleService {
    /**
     * 查询车辆管理列表
     * @param vehicle 车辆信息
     * @return 返回列表
     */
    List<SysVehicle> selectVehicleList(SysVehicle vehicle);

    /**
     * 校验车辆编号是否唯一
     *
     * @param vehicle 车辆信息
     * @return 结果
     */
    String checkClientTypeNameUnique(SysVehicle vehicle);

    /**
     * 新增车辆信息
     *
     * @param vehicle 车辆信息
     * @return 结果
     */
    int insertVehicle(SysVehicle vehicle);

    /**
     * 删除车辆信息
     * @param ids 车辆ID
     * @return 结果
     */
    int deleteById(String ids);

    /**
     * 查询指定车辆信息
     * @param carId 指定ID
     * @return 结果
     */
    SysVehicle selectVehicleById(Integer carId);

    /***
     * 根据车辆编码验证是否存在
     * @param vehicle 车辆
     * @return 结果
     */
    String checkUniqueCode(SysVehicle vehicle);

    /**
     * 根据车辆名称验证是否存在
     * @param vehicle 车辆
     * @return 结果
     */
    String checkUniqueName(SysVehicle vehicle);

    /**
     * 更新客户
     * @param vehicle 车辆
     * @return 结果
     */
    int updateDept(SysVehicle vehicle);
}
