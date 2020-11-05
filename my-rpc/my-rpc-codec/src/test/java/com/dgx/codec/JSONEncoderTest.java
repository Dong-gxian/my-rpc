package com.dgx.codec;

import junit.framework.TestCase;

public class JSONEncoderTest extends TestCase {

    public void testEncode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean("ququ", 18);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}