package com.ruoyi.project.system.vehicle.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.vehicle.domain.SysVehicle;
import com.ruoyi.project.system.vehicle.mapper.SysVehicleMapper;
import com.ruoyi.project.system.vehicle.service.SysVehicleService;
import com.ruoyi.project.tool.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangqm
 * @version 1.0
 * @email zqmyouxiang@foxmail.com
 * @date 2020/4/20 0020 15:19
 * @project RuoYi-fast
 */
@Service
public class SysVehicleServiceImpl implements SysVehicleService {

    @Autowired
    private SysVehicleMapper sysVehicleMapper;

    @Override
    public List<SysVehicle> selectVehicleList(SysVehicle vehicle) {
        return sysVehicleMapper.selectVehicleList(vehicle);
    }

    @Override
    public String checkClientTypeNameUnique(SysVehicle vehicle) {
        Long typeID = StringUtils.isNull(vehicle.getCarId()) ? -1L : vehicle.getCarId();
        SysVehicle info = sysVehicleMapper.checkClientTypeNameUnique(vehicle.getCarId(), vehicle.getCarCode());
        if (StringUtils.isNotNull(info) && info.getCarId().longValue() != typeID.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public int insertVehicle(SysVehicle vehicle) {
        //添加车辆编号
        vehicle.setCarCode(UUIDUtils.getUUID());
        // 添加删除状态
        vehicle.setDelFlag(0);
        return sysVehicleMapper.insertVehicle(vehicle);
    }

    /**
     * 删除指定车辆信息
     * @param ids 车辆ID
     * @return 结果
     */
    @Override
    public int deleteById(String ids) {
        return sysVehicleMapper.deleteById(ids);
    }

    /**
     * 查询指定车辆信息
     * @param carId 指定ID
     * @return 结果
     */
    @Override
    public SysVehicle selectVehicleById(Integer carId) {
        return sysVehicleMapper.selectVehicleById(carId);
    }

    @Override
    public String checkUniqueCode(SysVehicle vehicle) {
        SysVehicle info = sysVehicleMapper.checkUniqueCode(vehicle.getCarCode());
        if (StringUtils.isNotNull(info) && !info.getCarId().equals(vehicle.getCarId()))
        {
            return UserConstants.GENERAL_NOT_UNIQUE;
        }
        return UserConstants.GENERAL_UNIQUE;
    }

    @Override
    public String checkUniqueName(SysVehicle vehicle) {
        SysVehicle info = sysVehicleMapper.checkUniqueName(vehicle.getCarName());
        if (StringUtils.isNotNull(info) && !info.getCarId().equals(vehicle.getCarId()))
        {
            return UserConstants.GENERAL_NOT_UNIQUE;
        }
        return UserConstants.GENERAL_UNIQUE;
    }

    @Override
    public int updateDept(SysVehicle vehicle) {
        return sysVehicleMapper.updateVehicle(vehicle);
    }
}
