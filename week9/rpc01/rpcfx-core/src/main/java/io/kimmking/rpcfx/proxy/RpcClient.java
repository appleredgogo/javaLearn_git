package io.kimmking.rpcfx.proxy;

public interface RpcClient {

    /**
     * create proxy
     */
    <T> T create(final Class<T> serviceClass, final String url);
}
