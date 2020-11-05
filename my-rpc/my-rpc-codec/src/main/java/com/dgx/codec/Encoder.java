package com.dgx.codec;

/**
 * serialization
 * @author dgx
 */
public interface Encoder {
    byte[] encode(Object obj);
}
