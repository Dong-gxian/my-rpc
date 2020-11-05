package com.dgx.codec;
import com.alibaba.fastjson.JSON;

/**
 * serialization based on JSON
 */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
