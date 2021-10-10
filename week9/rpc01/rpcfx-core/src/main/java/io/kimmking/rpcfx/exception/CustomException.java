
package io.kimmking.rpcfx.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟异常
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    private String message;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
