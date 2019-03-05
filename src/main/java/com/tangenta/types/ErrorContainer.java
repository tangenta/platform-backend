package com.tangenta.types;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ErrorContainer implements LoginResult, RegisterResult {
    private final List<String> messages = new LinkedList<>();

    public ErrorContainer(String ...messages) {
        this.messages.addAll(Arrays.asList(messages));
    }

    public void appendMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
