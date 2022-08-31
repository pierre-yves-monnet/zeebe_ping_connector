# zeebe_ping_connector
Create a simple ping connector

# Documentation
https://docs.camunda.io/docs/components/integration-framework/connectors/custom-built-connectors/connector-sdk/

# Start it

Environment variables
ZEEBE_CONNECTOR_MYPING_TYPE=mypingtype
ZEEBE_CONNECTOR_MYPING_VARIABLES=MyPingConnectorRequest
ZEEBE_CONNECTOR_MYPING_FUNCTION=io.camunda.connector.MyPingConnectorFunction


ZEEBE_ADDRESS=127.0.0.1:26500
or keep it null (default is localhost:26500)


Start io.camunda.connector.runtime.jobworker.Main


