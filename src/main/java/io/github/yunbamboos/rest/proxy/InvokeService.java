package io.github.yunbamboos.rest.proxy;

import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.dto.out.OutDTO;

import java.util.Optional;

/**
 * 调用服务接口
 */
public interface InvokeService {

    Optional<OutDTO> invoke(Object bean, InDTO inDTO);
}
