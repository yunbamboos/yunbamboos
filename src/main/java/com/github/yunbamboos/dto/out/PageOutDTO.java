package com.github.yunbamboos.dto.out;

import com.alibaba.fastjson.JSONObject;
import com.github.yunbamboos.model.Page;
import com.github.yunbamboos.rest.anno.ParamDesc;
import com.github.yunbamboos.rest.anno.ParamType;
import com.github.yunbamboos.util.ObjectUtils;

public class PageOutDTO extends OutDTO {

    @ParamDesc(name = "page", zn = "分页数据", isNull = true, type = ParamType.Object)
    protected Page page;

    @Override
    public JSONObject encodeData() {
        JSONObject json = new JSONObject();
        if (ObjectUtils.isNotNull(page)) {
            json.put("page", page.encode());
        }
        return json;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
