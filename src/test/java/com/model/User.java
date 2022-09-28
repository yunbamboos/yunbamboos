package com.model;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;
import io.github.yunbamboos.constant.DateConst;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.DateUtils;

import java.time.LocalDateTime;

 /**
 * User(用户表)
 */
public class User implements Model {

    /** 用户ID(user_id) */
    @ParamDesc(name = "user_id", zn = "用户ID", isNull = true, type = ParamType.Integer, demo = "1")
    private int userId;
    /** 用户昵称(nick_name) */
    @ParamDesc(name = "nick_name", zn = "用户昵称", isNull = true, type = ParamType.String, demo = "云竹")
    private String nickName;
    /** 登录账户(login_name) */
    @ParamDesc(name = "login_name", zn = "登录账户", isNull = true, type = ParamType.String, demo = "yunzhu")
    private String loginName;
    /** 登录密码(password) */
    @ParamDesc(name = "password", zn = "登录密码", isNull = true, type = ParamType.String, demo = "123")
    private String password;
    /** 角色ID(role_id) */
    @ParamDesc(name = "role_id", zn = "角色ID", isNull = true, type = ParamType.Integer, demo = "1")
    private int roleId;
    /** 图像ID(image_id) */
    @ParamDesc(name = "image_id", zn = "图像ID", type = ParamType.Integer, demo = "1")
    private int imageId;
    /** 用户状态(user_state) */
    @ParamDesc(name = "user_state", zn = "用户状态", isNull = true, type = ParamType.Integer, demo = "1")
    private int userState;
    /** 创建时间(create_time) */
    @ParamDesc(name = "create_time", zn = "创建时间", isNull = true, type = ParamType.String, demo = "2022-08-22 00:00:00.000")
    private LocalDateTime createTime;
    /** 豆瓣用户ID(douban_user_id) */
    @ParamDesc(name = "douban_user_id", zn = "豆瓣用户ID", isNull = true, type = ParamType.String)
    private String doubanUserId;

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("nick_name", nickName);
        json.put("login_name", loginName);
        json.put("image_id", imageId);
        json.put("user_state", userState);
        json.put("create_time", DateUtils.parseDateToStr(createTime, DateConst.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS_SSS));
        json.put("douban_user_id", doubanUserId);
        return json;
    }
    
    @Override
    public void decode(JSONObject json){
        if(json.containsKey("user_id")) this.userId = json.getInteger("user_id");
        if(json.containsKey("nick_name")) this.nickName = json.getString("nick_name");
        if(json.containsKey("login_name")) this.loginName = json.getString("login_name");
        if(json.containsKey("password")) this.password = json.getString("password");
        if(json.containsKey("role_id")) {
            this.roleId = json.getInteger("role_id");
        } else {
            this.roleId = 1;
        }
        if(json.containsKey("image_id")) {
            this.imageId = json.getInteger("image_id");
        } else {
            this.imageId = 0;
        }
        if(json.containsKey("user_state")) {
            this.userState = json.getInteger("user_state");
        } else {
            this.userState = 1;
        }
        if(json.containsKey("create_time")) {
            this.createTime = DateUtils.stringToLocalDateTime(json.getString("create_time"), DateConst.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS_SSS);
        } else {
            this.createTime = LocalDateTime.now();
        }
	}

    /** 用户ID(user_id) */
    public int getUserId(){
        return this.userId;
    }
    /** 用户ID(user_id) */
    public void setUserId(int userId){
        this.userId=userId;
    }
    /** 用户昵称(nick_name) */
    public String getNickName(){
        return this.nickName;
    }
    /** 用户昵称(nick_name) */
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    /** 登录账户(login_name) */
    public String getLoginName(){
        return this.loginName;
    }
    /** 登录账户(login_name) */
    public void setLoginName(String loginName){
        this.loginName=loginName;
    }
    /** 登录密码(password) */
    public String getPassword(){
        return this.password;
    }
    /** 登录密码(password) */
    public void setPassword(String password){
        this.password=password;
    }
    /** 角色ID(role_id) */
    public int getRoleId(){
        return this.roleId;
    }
    /** 角色ID(role_id) */
    public void setRoleId(int roleId){
        this.roleId=roleId;
    }
    /** 图像ID(image_id) */
    public int getImageId(){
        return this.imageId;
    }
    /** 图像ID(image_id) */
    public void setImageId(int imageId){
        this.imageId=imageId;
    }
    /** 用户状态(user_state) */
    public int getUserState(){
        return this.userState;
    }
    /** 用户状态(user_state) */
    public void setUserState(int userState){
        this.userState=userState;
    }
    /** 创建时间(create_time) */
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    /** 创建时间(create_time) */
    public void setCreateTime(LocalDateTime createTime){
        this.createTime=createTime;
    }
    /** 豆瓣用户ID(douban_user_id) */
    public String getDoubanUserId(){
        return this.doubanUserId;
    }
    /** 豆瓣用户ID(douban_user_id) */
    public void setDoubanUserId(String doubanUserId){
        this.doubanUserId=doubanUserId;
    }
}