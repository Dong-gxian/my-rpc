package com.dgx.myrpc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * represent response of rpc
 *
 * @author dgx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int code = 0;//status code,0 = succeed, other = failed
    private String message = "ok";//reason why failed
    private Object[] data;//contents returned
}
