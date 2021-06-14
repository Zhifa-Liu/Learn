package cn.edu.neu.quartz_spring.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 32098
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GenericResponse<T> {
    private String result;
    private Integer code;
    private T data;
    private String info;
}
