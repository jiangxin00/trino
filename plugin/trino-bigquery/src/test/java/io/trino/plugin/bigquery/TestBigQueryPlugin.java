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
package io.trino.plugin.bigquery;

import io.trino.spi.connector.ConnectorFactory;
import io.trino.testing.TestingConnectorContext;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.google.common.collect.Iterables.getOnlyElement;
import static io.airlift.testing.Assertions.assertInstanceOf;

public class TestBigQueryPlugin
{
    @Test
    public void testCreateConnector()
    {
        BigQueryPlugin plugin = new BigQueryPlugin();
        ConnectorFactory factory = getOnlyElement(plugin.getConnectorFactories());
        assertInstanceOf(factory, BigQueryConnectorFactory.class);

        factory.create(
                        "test",
                        Map.of(
                                "bigquery.project-id", "xxx",
                                "bigquery.credentials-key", "ewogICAgInR5cGUiOiAic2VydmljZV9hY2NvdW50IiwKICAgICJwcm9qZWN0X2lkIjogInByZXN0b3Rlc3QiLAogICAgInByaXZhdGVfa2V5X2lkIjogIngiLAogICAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZRSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2N3Z2dTakFnRUFBb0lCQVFDM3E0NkcrdlRtdllmR1xuUEVCcUZST2MwWllEUFE4Z1VRYlEvaWRiYXBQc0s3TUxIZEx1RUdzQkF4SjMyYkdKV2FXV1pKMlZvKzA2Y3E4UlxuM1VZdVJ5RDBvNVk5OTV3d0t5YUVMdjVHTHFxMlpkQ0U2MGNqbE8yeXM4dUg4MVJMQStOME9zUzI0WXAvTmw1V1xuMHl4bkVuaW5VSW5Hb1VteVJ0K1V1aTIxNTQ3WTZFeDFKMGdqVndoNWtBNTJBcG0xVzVjZ3JKUWgwMlZTNUZERlxudEtnWjlKNFUyZVQvM2RNUkFlN0dLaWtseGpMTktjSzJ6T3JuYVpzb0pBTnNrZ0xMdjhPaDJEdlpQbWd1dmtxL1xuUU4yRVRCSXVLRUxCbEN4ZHZkRVp5L2pPcUVmdW1xcWI4VTVqVk4vdit6Q0pZVHREcVRGVWxLRVZDbEVNV3BCUFxuR3dIUzg5MlRBZ01CQUFFQ2dnRUFVOWxuTE9vZXFjUTIydUlneWcwck1mbGdrY1ByUnVhV3hReHlMVUsvbXg3c1xuRXhRZmVuMVdURlQ1dG10VXFJNmJrTWdJUlF0Y1BzV2lkUFplbHJ2MEtKc1IrT0kwbEt6dVhZUVNvem1reDdZOVxuZHFEdWppanNSeHZidkFuekhuZjgrOC9raEZUODVFeU96dmFERzk4TDQ5NVp0NnRrT0pZd2RmWjA3Y2x6cGtQSVxudVNDMGRTMldFckZnT0JiK3BJZGFwU3dSN1gxOTlROGNsenhjYlVUUUJJaGJDMnFhUmUvelFBdHNIS3ArMHRRVFxuWEI0Z3A1bitXc0pGM2lmTlYwdkZ0VWRRUlNCNFBmRzExRW1lczBQTFpxV1ZYb2xGdWpVQW8xS0o3dXFWTVpoUlxuQTF6VEpEbzNaaklHUllvbmRHQWRHR1hrMU1rd1JCcGoxR0FRb08xSm9RS0JnUURjUFhLcjF0MlBVbnJMelN1UFxuNVM2ek9WMUVzNkpJbmpVaGtXNFFhcTQ4RFRYNEg2bkdTYkdyYW5tbVpyQXlWNytEeXZPWGVzaC9ITzJROWtlaFxuRlczaUVtQzBCZE9FeWVuRUJUNThidHR5VzlMVUtBVjhjendYTno4Q3lSQ0xGd292UDIzUjFkS3BZdGtsR0l6NVxuWjJaMEF1SEtzcWd2TC9Jeng2bU1QNU0rQXdLQmdRRFZmZ2ttMUJPVzhad3JvNFFjbE43bnlKN01lQ3BCTFZycVxuUU9OcThqeE5EaGpsT0VDSElZZmFTUUZLYXkwa2pBTndTQjRMYURuTXJTbmRJYWxqV3F1LzBtdThMLzNwQzg4MlxuOUhpNU1Mc1Jjb0trNDY5UnRLRVIxWEwvcE5sb1NTd2dkZWJtUWk3clhNUzFCQks4aFZ0UFFObmV0RE1sLy9JTFxuS3YzbmtycFZNUUtCZ0dnaUdiVU1PK2dIUEk1ZUxRbTFlRFkvbWt6Z2pvdTlXaXZNQW5sNnAzVTNYZHc2eEdBL1xuK2VTdHpHVVVTcDBUQmpkL1gxdXhMMW1DeVFUd25YK1pqVUlHSkhrYUJCL1dCRlN0a2hUdHFZN1J3Y2FVUWJ2TlxuRkkxNWpxNTNlUDM2MzlMbEw3eTJXQXZFOUJ6cEZjYmEwQU5zVld3c3V2N01zYjB2MjRlM2k1d1hBb0dCQUswWlxuL2kyZmN5ckdTRXdSendLbHFuN2c2ZkQ3MWJiM0lXb2lwc0tHR21LWDlaT1ZvcXh1Z1lwNSt6UHQ1ckpsWER4a1xuSFFnK3YrNjIwT1RkY0V5QXJoVmdkYjRtWTRmYjdXMnZsMXNBcWcwaGZkQllWRVM1WW9mbE85TVFSTDhMNVYyRVxuZTIxamFFdXA4a3liT3Qza2V2NnRwSG13UG5Dbk1BZmlHZkR6eFdWaEFvR0FYa1k5bjNsSDFISDJBUDhzMkNnNVxuN3o3NVhLYWtxWE9CMkNhTWFuOWxJd0FCVzhSam1IKzRiU2VVQ0kwM1hRRExrY1R3T0N3QStrL3FvZldBeW1ldVxudzU4Vzh5cGlWVGpDVDErUzh5VjhYL0htTERVa3VsTnUvY2psYlJPdnJmSlRIL2pNbVhhTEQxeVZlYXlxOFlGZFxubnl6SmpiR1BwdGsvYVRTYk5rQmpvdWM9XG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICAgImNsaWVudF9lbWFpbCI6ICJ4IiwKICAgICJjbGllbnRfaWQiOiAieCIsCiAgICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9wcmVzdG90ZXN0JTQwcHJlc3RvdGVzdC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIKfQo="),
                        new TestingConnectorContext())
                .shutdown();
    }
}
