package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.camunda.connector.api.Validator;
import io.camunda.connector.test.ConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class MyRequestTest {

  @Test
  void shouldReplaceTokenSecretWhenReplaceSecrets() {
    // given
    var input = new MyPingConnectorRequest();
    input.setMessage("Hello World!");
    input.setDelay("200");
    var context = ConnectorContextBuilder.create()
      .secret("MY_TOKEN", "token value")
      .build();
    // when
    context.replaceSecrets(input);
    // then
    assertThat(input)
      .extracting("delay")
      .isEqualTo("200");
  }

  @Test
  void shouldFailWhenValidate_NoToken() {
    // given
    var input = new MyPingConnectorRequest();
    input.setMessage("Hello World!");
    var validator = new Validator();
    input.validateWith(validator);
    // when
    assertThatThrownBy(() -> validator.evaluate())
      // then
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("token");
  }

  @Test
  void shouldFailWhenValidate_NoMesage() {
    // given
    var input = new MyPingConnectorRequest();
    input.setDelay("2000");
    var validator = new Validator();
    input.validateWith(validator);
    // when
    assertThatThrownBy(() -> validator.evaluate())
      // then
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("message");
  }

  @Test
  void shouldFailWhenValidate_TokenWrongPattern() {
    // given
    var input = new MyPingConnectorRequest();
    input.setDelay("10");
    input.setMessage("foo");
    var validator = new Validator();
    input.validateWith(validator);
    // when
    assertThatThrownBy(() -> validator.evaluate())
      // then
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Delay too short");
  }
}