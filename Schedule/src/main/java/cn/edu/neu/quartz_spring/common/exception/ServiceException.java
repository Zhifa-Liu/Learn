package cn.edu.neu.quartz_spring.common.exception;

import lombok.NoArgsConstructor;

/**
 * @author 32098
 */
@NoArgsConstructor
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
}

