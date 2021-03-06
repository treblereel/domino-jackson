/*
 * Copyright 2013 Nicolas Morel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dominokit.jacksonapt.server.deser;

import org.dominokit.jacksonapt.DefaultJsonDeserializationContext;
import org.dominokit.jacksonapt.JsonDeserializer;
import org.dominokit.jacksonapt.deser.EnumJsonDeserializer;
import org.junit.Test;

import static org.assertj.core.api.Fail.fail;

/**
 * @author Nicolas Morel
 */
public class EnumJsonDeserializerTest extends AbstractJsonDeserializerTest<EnumJsonDeserializerTest.EnumTest> {

    protected static enum EnumTest {
        ONE, TWO, THREE, FOUR
    }

    @Override
    protected JsonDeserializer<EnumTest> createDeserializer() {
        return EnumJsonDeserializer.newInstance(EnumTest.class, EnumTest.values());
    }

    @Override
    @Test
	public void testDeserializeValue() {
        assertDeserialization(EnumTest.ONE, "\"ONE\"");
        assertDeserialization(EnumTest.TWO, "\"TWO\"");
        assertDeserialization(EnumTest.THREE, "\"THREE\"");
        assertDeserialization(EnumTest.FOUR, "\"FOUR\"");
        try {
            assertDeserialization(null, "\"UNKNOWN\"");
            fail("IllegalArgumentException should be thrown!");
        } catch (IllegalArgumentException ex) {
        }
        assertDeserialization(DefaultJsonDeserializationContext.builder().readUnknownEnumValuesAsNull(true).build(), null, "\"UNKNOWN\"");
    }
}
