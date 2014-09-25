package com.sloshydog.eventuate.filesystem;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PayloadSerializerTest {

    @Test
    public void shouldBeAbleToSerializeAPayloadToByteData() {
        SerializedPayload serialize = new PayloadSerializer().serialize("My payload");

        SerializedPayload expectedSerializedPayload = new SimpleSerializedPayload(new byte[] {-84,-19,0,5,116,0,10,77,121,32,112,97,121,108,111,97,100});
        assertThat(serialize).isEqualTo(expectedSerializedPayload);
    }

    @Test
    public void shouldBeAbleToDeserializeAPayloadToItsOriginalObject() {
        Object result = new PayloadSerializer().deserialize(new SimpleSerializedPayload(new byte[] {-84,-19,0,5,116,0,10,77,121,32,112,97,121,108,111,97,100}));

        assertThat(result).isEqualTo("My payload");
    }

}