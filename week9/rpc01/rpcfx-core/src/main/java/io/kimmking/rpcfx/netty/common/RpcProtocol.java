
package io.kimmking.rpcfx.netty.common;

import lombok.Data;

/**
 * Netty 通信的数据格式
 */
@Data
public class RpcProtocol {

    /**
     * 数据大小
     */
    private int len;

    /**
     * 数据内容
     */
    private byte[] content;
}
