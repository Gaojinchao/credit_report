package com.saipaisi.project.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.common.utils.ServletUtils;
import com.saipaisi.framework.security.service.TokenService;
import com.saipaisi.project.system.domain.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.system.mapper.SysCrmConfigMapper;
import com.saipaisi.project.system.domain.SysCrmConfig;
import com.saipaisi.project.system.service.ISysCrmConfigService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * crm系统配置Service业务层处理
 *
 * @author saipaisi
 * @date 2020-08-19
 */
@Service
public class SysCrmConfigServiceImpl implements ISysCrmConfigService {
    @Autowired
    private SysCrmConfigMapper sysCrmConfigMapper;

    @Resource
    private TokenService tokenService;

    /**
     * 查询crm系统配置
     *
     * @param id crm系统配置ID
     * @return crm系统配置
     */
    @Override
    public SysCrmConfig selectSysCrmConfigById(Long id) {
        return sysCrmConfigMapper.selectSysCrmConfigById(id);
    }

    /**
     * 查询crm系统配置列表
     *
     * @param sysCrmConfig crm系统配置
     * @return crm系统配置
     */
    @Override
    public List<SysCrmConfig> selectSysCrmConfigList(SysCrmConfig sysCrmConfig) {
        //获取当前登陆人的公司id ,从而取到属于当前公司的配置信息
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
//        sysCrmConfig.setCompanyId(user.getCompanyId());
        return sysCrmConfigMapper.selectSysCrmConfigList(sysCrmConfig);
    }

    /**
     * 新增crm系统配置
     *
     * @param sysCrmConfig crm系统配置
     * @return 结果
     */
    @Override
    public int insertSysCrmConfig(SysCrmConfig sysCrmConfig) {
        sysCrmConfig.setCreateTime(DateUtils.getNowDate());
        return sysCrmConfigMapper.insertSysCrmConfig(sysCrmConfig);
    }

    /**
     * 修改crm系统配置
     *
     * @param sysCrmConfig crm系统配置
     * @return 结果
     */
    @Override
    public int updateSysCrmConfig(SysCrmConfig sysCrmConfig) {
        sysCrmConfig.setUpdateTime(DateUtils.getNowDate());
        return sysCrmConfigMapper.updateSysCrmConfig(sysCrmConfig);
    }

    /**
     * 批量删除crm系统配置
     *
     * @param ids 需要删除的crm系统配置ID
     * @return 结果
     */
    @Override
    public int deleteSysCrmConfigByIds(Long[] ids) {
        return sysCrmConfigMapper.deleteSysCrmConfigByIds(ids);
    }

    /**
     * 删除crm系统配置信息
     *
     * @param id crm系统配置ID
     * @return 结果
     */
    @Override
    public int deleteSysCrmConfigById(Long id) {
        return sysCrmConfigMapper.deleteSysCrmConfigById(id);
    }

    @Override
    public boolean loginTimeLimit() {
        //查看当前登陆人信息
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        //如果是超级管理员，直接跳过时间限制
        if (user.isAdmin()) {
            return true;
        }
        //构建请求参数

        return true;
//        sysCrmConfigMapper.selectSysCrmConfigList();
//        //判断在不在登陆时间
//        SysConfig sysConfig = sysConfigService.selectOne(new EntityWrapper<SysConfig>()
//                .eq("name", LOGIN_TIME_CONFIG.getName())
//                .eq("companyId", sysAdminUser.getCompanyId()));
//        if (sysConfig == null) {
//            throw new BixApiException("配置项为空");
//        }
//        if (sysAdminUser.getRoleId() != 1 && sysAdminUser.getRoleId() != 0) {
//            if (StringUtils.equals("1", sysConfig.getField3())) {
//                throw new BixApiException("当前时间段内不允许登陆");
//            }
//            String start = sysConfig.getField1();
//            String end = sysConfig.getField2();
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
//            String date = sf1.format(new Date());
//            try {
//                Date startDate = sf.parse(date.concat(" ").concat(start));
//                Date endDate = sf.parse(date.concat(" ").concat(end));
//                if (new Date().before(startDate) || new Date().after(endDate)) {
//                    throw new BixApiException("当前时间段内不允许登陆");
//                }
//            } catch (ParseException e) {
//                throw new BixApiException("时间转换有误");
//            }
//        }
    }
    }
