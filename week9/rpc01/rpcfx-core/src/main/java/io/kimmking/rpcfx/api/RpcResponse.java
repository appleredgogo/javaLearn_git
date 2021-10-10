package io.kimmking.rpcfx.api;

import lombok.Data;

@Data
public class RpcResponse {
    /**
     * 响应结果
     */
    private Object result;

    /**
     * 判断是否执行成功
     */
    private Boolean status;

    /**
     * 执行失败的异常信息
     */
    private Exception exception;
}
