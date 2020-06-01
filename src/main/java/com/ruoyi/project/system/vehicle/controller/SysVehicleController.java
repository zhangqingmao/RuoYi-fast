package com.ruoyi.project.system.vehicle.controller;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.vehicle.domain.SysVehicle;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.project.system.vehicle.service.SysVehicleService;

import java.util.List;

/**
 * @author zhangqm
 * @version 1.0
 * @email zqmyouxiang@foxmail.com
 * @date 2020/4/20 0020 15:14
 * @project RuoYi-fast
 */
@Controller
@RequestMapping("/system/vehicle")
public class SysVehicleController extends BaseController {

    private String prefix = "system/vehicle";

    @Autowired
    private SysVehicleService sysVehicleService;

    @RequiresPermissions("system:vehicle:view")
    @GetMapping()
    public String vehicle()
    {
        return prefix + "/vehicle";
    }

    @RequiresPermissions("system:vehicle:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysVehicle vehicle) {
        startPage();
        List<SysVehicle> sysVehicles = sysVehicleService.selectVehicleList(vehicle);
        return getDataTable(sysVehicles);
    }

    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    @Log(title = "车辆管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:vehicle:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysVehicle vehicle) {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(sysVehicleService.checkClientTypeNameUnique(vehicle)))
        {
            return error("新增车辆'" + vehicle.getCarCode() + "'失败，车辆编号已存在");
        }
        vehicle.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(sysVehicleService.insertVehicle(vehicle));
    }

    @Log(title = "车辆管理信息", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:vehicle:remove")
    @PostMapping("/remove/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable String ids) {
        Integer[] carIds = Convert.toIntArray(ids);
        String str = "";
        for (int i = 0; i < carIds.length; i++) {
            if (carIds.length-1 == carIds.length){
                str = carIds[i] + ",";
            } else {
                str = carIds[i] + "";
            }
        }
        return toAjax(sysVehicleService.deleteById(str));
    }

    @GetMapping("/edit/{carId}")
    public String edit(@PathVariable Integer carId, ModelMap mmap) {
        SysVehicle vehicle = sysVehicleService.selectVehicleById(carId);
        if (StringUtils.isNull(vehicle)) {
            vehicle.setCarName("无");
        }
        mmap.put("vehicle", vehicle);
        return prefix + "/edit";
    }

    @Log(title = "修改车辆信息", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:vehicle:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(@Validated SysVehicle vehicle) {
        if (UserConstants.GENERAL_NOT_UNIQUE.equals(sysVehicleService.checkUniqueCode(vehicle)))
        {
            return error("修改客户'" + vehicle.getCarCode() + "'失败，客户编码已存在");
        }

        if (UserConstants.GENERAL_NOT_UNIQUE.equals(sysVehicleService.checkUniqueName(vehicle)))
        {
            return error("修改客户'" + vehicle.getCarName() + "'失败，客户名称已存在");
        }
        vehicle.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(sysVehicleService.updateDept(vehicle));
    }

}