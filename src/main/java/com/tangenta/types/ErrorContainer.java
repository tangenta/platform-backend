package com.tangenta.types;

import java.util.List;

public class ErrorContainer implements LoginResult {
    private final List<String> messages;

    public ErrorContainer(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
