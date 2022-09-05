package io.github.yunbamboos.dto.out;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.model.Page;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.ObjectUtils;

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
