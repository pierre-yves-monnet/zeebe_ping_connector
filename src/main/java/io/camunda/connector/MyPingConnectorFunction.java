package io.camunda.connector;

import io.camunda.connector.api.ConnectorContext;
import io.camunda.connector.api.ConnectorFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPingConnectorFunction implements ConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyPingConnectorFunction.class);

  @Override
  public Object execute(ConnectorContext context) throws Exception {
    String allVariables=context.getVariables();
    var connectorRequest = context.getVariablesAsType(MyPingConnectorRequest.class);

    context.validate(connectorRequest);
    context.replaceSecrets(connectorRequest);

    return executeConnector(connectorRequest);
  }

  private MyConnectorResult executeConnector(final MyPingConnectorRequest connectorRequest) {
    // TODO: implement connector logic
    LOGGER.info("Executing my connector with request {}", connectorRequest);
    var result = new MyConnectorResult();
    result.setMyProperty("Message received: " + connectorRequest.getMessage());
    return result;
  }
}
