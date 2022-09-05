package io.github.yunbamboos.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 分页数据
 * */
public class Page implements Model {

    public static final int PAGE_NO = 1;

    public static final int PAGE_SIZE = 10;

    /**当前页码*/
    private final int pageNo;
    /**每页条数*/
    private final int pageSize;
    /**分页条件*/
    private final transient Map<String, Object> conditions;

    /**分页数据*/
    private List<? extends Model> rows;
    /**总条数*/
    private int totalNum;
    /**数据起始位置*/
    private int offset;

    public Page(){
        this(PAGE_NO);
    }

    public Page(int pageNo){
        this(pageNo, PAGE_SIZE);
    }

    public Page(int pageNo,int pageSize){
        this(pageNo, pageSize, new ConcurrentHashMap<>(10));
    }

    public Page(int pageNo,int pageSize, Map<String, Object> conditions){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.conditions = conditions;
    }

    public void addCondition(String key, Object value){
        if(key == null || value == null){
            return;
        }
        conditions.put(key,value);
    }

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("page_no", pageNo);
        json.put("page_size", pageSize);
        JSONArray array = new JSONArray();
        if(this.rows!=null){
            this.rows.forEach(model -> array.add(model.encode()));
        }
        json.put("rows", array);
        json.put("total_num", totalNum);
        return json;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public List<? extends Model> getRows() {
        return rows;
    }

    public void setRows(List<? extends Model> rows) {
        this.rows = rows;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
