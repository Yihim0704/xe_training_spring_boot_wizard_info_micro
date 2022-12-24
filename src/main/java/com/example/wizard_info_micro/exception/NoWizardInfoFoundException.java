package com.example.wizard_info_micro.exception;

public class NoWizardInfoFoundException extends RuntimeException {

    public NoWizardInfoFoundException() {
    }

    public NoWizardInfoFoundException(String message) {
        super(message);
    }
}
