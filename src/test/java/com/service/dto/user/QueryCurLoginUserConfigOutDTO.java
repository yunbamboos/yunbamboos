package com.service.dto.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.UserConfig;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.ObjectUtils;

import java.util.List;

public class QueryCurLoginUserConfigOutDTO extends OutDTO {

    @ParamDesc(name = "user_config_list", zn = "用户配置信息列表", isNull = true, type = ParamType.List)
    private List<UserConfig> userConfigList;

    @Override
    public JSONObject encodeData(){
        JSONObject json = new JSONObject();
        if (ObjectUtils.isNotNull(userConfigList)) {
            JSONArray userConfigList = new JSONArray(this.userConfigList.size());
            this.userConfigList.forEach(userConfig -> userConfigList.add(userConfig.encode()));
            json.put("user_config_list", userConfigList);
        }
        return json;
    }

    public void setUserConfigList(List<UserConfig> userConfigList) {
        this.userConfigList = userConfigList;
    }
}
