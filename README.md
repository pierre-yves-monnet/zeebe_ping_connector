# zeebe_ping_connector
Create a simple ping connector

# Documentation
https://docs.camunda.io/docs/components/integration-framework/connectors/custom-built-connectors/connector-sdk/

# What you need to know
## Environment variable
  The SDK use environment variable to detect connector. Pattern is ZEEBE_CONNECTOR_<NAME>_TYPE.
  These are defined in INTELLIJ in the configuration

Link between type, function to execute (Java class) are via these variables.

`````
ZEEBE_CONNECTOR_MYPING_TYPE=mypingtype;
ZEEBE_CONNECTOR_MYPING_FUNCTION=io.camunda.connector.MyPingConnectorFunction
`````

## Input management

A connector can get variable only from a Container class (MyPingConnectorRequest) which declare all variables (message, delay)

These variables must be declared in the environment variable, else it will not be fullfill

`````
ZEEBE_CONNECTOR_MYPING_VARIABLES=message,delay;
`````

then the class can be
`````

public class MyPingConnectorRequest implements ConnectorInput {

    private String message;
    private String delay;
`````

and the connector can use it
`````
    var connectorRequest = context.getVariablesAsType(MyPingConnectorRequest.class);
`````

Note: a call to
`````
String allVariables=context.getVariables();
`````
return a STRING: this is a JSON with variables and content


# Start it

Tp execute it, you must specify the ZEEBE host.
`````
ZEEBE_ADDRESS=127.0.0.1:26500
`````

or keep it null (default is localhost:26500)


Start 
`````
io.camunda.connector.runtime.jobworker.Main
`````

The process pingExample.bpmn is under the src/main/resources

