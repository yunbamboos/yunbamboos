package com.github.yunbamboos.dto.in;

import com.alibaba.fastjson.JSONObject;
import com.github.yunbamboos.rest.anno.ParamDesc;
import com.github.yunbamboos.rest.anno.ParamType;

/**
 * 分页入参
 */
public class PageInDTO extends InDTO {

    @ParamDesc(name = "page_no", zn = "当前页码", type = ParamType.Integer, demo = "1")
    protected int pageNo;
    @ParamDesc(name = "page_size", zn = "每页条数", type = ParamType.Integer, demo = "10")
    protected int pageSize;

    @Override
    public void decode(JSONObject json) {
        super.decode(json);
        this.pageNo = this.getInteger(json, "page_no", 1);
        this.pageSize = this.getInteger(json, "page_size", 10);
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }
}
