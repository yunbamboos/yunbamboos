package com.service.impl.user;

import com.mapper.UserMapper;
import com.model.User;
import com.service.dto.user.AddUserInDTO;
import com.service.dto.user.AddUserOutDTO;
import com.service.dto.user.DelUserInDTO;
import com.service.dto.user.DelUserOutDTO;
import com.service.dto.user.QueryCurLoginUserInDTO;
import com.service.dto.user.QueryCurLoginUserOutDTO;
import com.service.dto.user.QueryUserByPageInDTO;
import com.service.dto.user.QueryUserByPageOutDTO;
import com.service.dto.user.UpdateUserInDTO;
import com.service.dto.user.UpdateUserOutDTO;
import com.service.inter.IUserService;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.model.Page;
import io.github.yunbamboos.rest.anno.RestServiceType;
import io.github.yunbamboos.rest.anno.Tag;
import io.github.yunbamboos.session.SessionContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关接口实现
 */
@Tag("用户相关接口")
@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/queryCurLoginUser",
            in = QueryCurLoginUserInDTO.class,
            out = QueryCurLoginUserOutDTO.class,
            name = "查询当前登录用户接口"
    )
    @Override
    public QueryCurLoginUserOutDTO queryCurLoginUser(QueryCurLoginUserInDTO in) {
        QueryCurLoginUserOutDTO out = new QueryCurLoginUserOutDTO();
        int userId = SessionContext.getInt("user_id");
        User user = userMapper.queryByPrimaryKey(userId).orElseThrow(() -> {
            throw new AppException("用户不存在");
        });
        out.setUser(user);
        return out;
    }

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/queryByPage",
            in = QueryUserByPageInDTO.class,
            out = QueryUserByPageOutDTO.class,
            name = "条件分页查询用户列表接口"
    )
    @Override
    public QueryUserByPageOutDTO queryByPage(QueryUserByPageInDTO in) {
        Page page = new Page(in.getPageNo(), in.getPageSize());
        int total = userMapper.countByPage(page);
        List<User> list = userMapper.queryByPage(page);
        page.setRows(list);
        page.setTotalNum(total);
        QueryUserByPageOutDTO out = new QueryUserByPageOutDTO();
        out.setPage(page);
        return out;
    }

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/add",
            in = AddUserInDTO.class,
            out = AddUserOutDTO.class,
            name = "添加用户接口"
    )
    @Override
    public AddUserOutDTO add(AddUserInDTO in){

        return new AddUserOutDTO();
    }

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/update",
            in = UpdateUserInDTO.class,
            out = UpdateUserOutDTO.class,
            name = "更新用户信息"
    )
    @Override
    public UpdateUserOutDTO update(UpdateUserInDTO in){
        return new UpdateUserOutDTO();
    }

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/del",
            in = DelUserInDTO.class,
            out = DelUserOutDTO.class,
            name = "删除用户接口"
    )
    @Override
    public DelUserOutDTO del(DelUserInDTO in){
        return new DelUserOutDTO();
    }
}
