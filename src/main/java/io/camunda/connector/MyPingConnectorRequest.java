package io.camunda.connector;

import io.camunda.connector.api.ConnectorInput;
import io.camunda.connector.api.SecretStore;
import io.camunda.connector.api.Validator;

import java.util.Objects;

public class MyPingConnectorRequest implements ConnectorInput {

    private String message;
    private String messageId;
    private String delay;

    @Override
    public void validateWith(final Validator validator) {
        validator.require(message, "message");
        validator.require(delay, "delay");
        try {
            Integer delayInt = Integer.parseInt(delay);
            if (delayInt < 100) {
                validator.addErrorMessage("Delay too short");
            }
        } catch (Exception e) {
            // Don't care
        }

    }



    @Override
    public void replaceSecrets(final SecretStore secretStore) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, delay);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MyPingConnectorRequest other = (MyPingConnectorRequest) obj;
        return Objects.equals(message, other.message) && Objects.equals(delay, other.delay);
    }

    @Override
    public String toString() {
        return "MyConnectorRequest [message=" + message + ", delay=" + delay + "]";
    }

}
