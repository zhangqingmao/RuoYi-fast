package com.ruoyi.project.system.vehicle.mapper;

import com.ruoyi.project.system.vehicle.domain.SysVehicle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangqm
 * @version 1.0
 * @email zqmyouxiang@foxmail.com
 * @date 2020/4/20 0020 15:20
 * @project RuoYi-fast
 */
public interface SysVehicleMapper {

    List<SysVehicle> selectVehicleList(SysVehicle vehicle);

    SysVehicle checkClientTypeNameUnique(Integer carId, String carCode);

    int insertVehicle(SysVehicle vehicle);

    int deleteById(@Param("carId") String carId);

    SysVehicle selectVehicleById(Integer carId);

    SysVehicle checkUniqueCode(String carCode);

    SysVehicle checkUniqueName(String carName);

    int updateVehicle(SysVehicle vehicle);
}
