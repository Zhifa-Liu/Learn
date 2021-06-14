package cn.edu.neu.quartz_spring.common.http;

import lombok.Getter;

/**
 * @author 32098
 */

@Getter
public enum ResponseCode {
    /**
     *
     */
    SUCCESS(200, "success"), SERVER_ERROR(500, "server error"), CLIENT_ERROR(400, "client error");

    private Integer code;
    private String desc;

    /**
     *
     * @param code http response code
     * @param desc http response code desc
     */
    ResponseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ResponseCode getEnumByCode(int code) {
        for (ResponseCode value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public ResponseCode getEnumByDesc(String desc) {
        for (ResponseCode value : values()) {
            if (value.getDesc().equals(desc)) {
                return value;
            }
        }
        return null;
    }
}
