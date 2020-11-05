package com.dgx.codec;

import com.alibaba.fastjson.JSON;


/**
 * deserialization based on JSON
 */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
