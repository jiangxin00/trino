/*
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
package io.trino.plugin.deltalake;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;
import static io.trino.plugin.deltalake.DeltaLakeSecurityModule.ALLOW_ALL;
import static io.trino.plugin.deltalake.DeltaLakeSecurityModule.READ_ONLY;

public class TestDeltaLakeSecurityConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(DeltaLakeSecurityConfig.class)
                .setSecuritySystem(ALLOW_ALL));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = ImmutableMap.of("delta.security", "read_only");

        DeltaLakeSecurityConfig expected = new DeltaLakeSecurityConfig()
                .setSecuritySystem(READ_ONLY);

        assertFullMapping(properties, expected);
    }
}
