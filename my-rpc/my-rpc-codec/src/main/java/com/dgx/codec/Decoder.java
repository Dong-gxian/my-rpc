package com.dgx.codec;
/**
 * deserialization
 * @author dgx
 */
public interface Decoder {
    //Object decode(byte[] bytes):this kind of definition return a Object type which we need to transfer the type later.
    <T> T decode(byte[] bytes, Class<T> clazz);
}
