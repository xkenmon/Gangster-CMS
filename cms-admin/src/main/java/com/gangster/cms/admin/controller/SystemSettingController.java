package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.service.SettingService;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.SettingEntry;
import com.gangster.cms.common.pojo.SettingEntryExample;
import com.gangster.cms.common.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by Yoke on 2018/2/10
 */
@RestController
@RequestMapping("/setting")
public class SystemSettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/{id}")
    public List<SettingEntry> list(@SessionAttribute(CmsConst.CURRENT_USER) User user, @PathVariable Integer id) {
        return settingService.selectByExample(new SettingEntryExample());
    }

    @PutMapping("")
    public MessageDto<Object> update(@RequestBody Map<String, String> map) {
        if (map == null) {
            return MessageDto.fail(1, "更新配置失败");
        }
        String newPicPath = map.get("picSetting");
        SettingEntry picSetting = new SettingEntry();
        picSetting.setSysKey(CmsConst.PIC_PATH_SETTING);
        picSetting.setSysValue(newPicPath);
        picSetting.setSysUpdateTime(new Date());
        settingService.updateByPrimaryKey(picSetting);
        String newSkinPath = map.get("skinSetting");
        SettingEntry skinPath = new SettingEntry();
        skinPath.setSysKey(CmsConst.SKIN_PATH_SETTING);
        skinPath.setSysValue(newSkinPath);
        skinPath.setSysUpdateTime(new Date());
        settingService.updateByPrimaryKey(skinPath);
        return MessageDto.success(null);
    }
}
