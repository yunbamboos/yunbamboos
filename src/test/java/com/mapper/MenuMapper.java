package com.mapper;

import com.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单表(menu)表数据库访问层
 */
@Repository
public interface MenuMapper {

    /**查询所有菜单*/
    List<Menu> queryAll();

}
