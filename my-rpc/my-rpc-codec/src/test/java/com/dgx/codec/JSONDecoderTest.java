package com.dgx.codec;

import junit.framework.TestCase;

import java.util.Base64;

public class JSONDecoderTest extends TestCase {

    public void testDecode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean("ququ", 18);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);

        Decoder decoder = new JSONDecoder();
        TestBean obj = decoder.decode(bytes, TestBean.class);
        assertEquals(obj.getAge(), bean.getAge());
        System.out.println(obj.getName()+obj.getAge());
    }
}