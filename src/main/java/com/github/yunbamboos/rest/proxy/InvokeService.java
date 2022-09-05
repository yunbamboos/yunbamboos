package com.github.yunbamboos.rest.proxy;

import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.OutDTO;

import java.util.Optional;

/**
 * 调用服务接口
 */
public interface InvokeService {

    Optional<OutDTO> invoke(Object bean, InDTO inDTO);
}
