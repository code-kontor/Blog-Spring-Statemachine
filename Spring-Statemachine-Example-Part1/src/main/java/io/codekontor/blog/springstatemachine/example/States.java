package io.codekontor.blog.springstatemachine.example;

/**
 * Defines the states of the state machine.
 * 
 * @author Gerd W&uuml;therich (gw@code-kontor.io)
 */
public enum States {
	
    INITIAL, STARTING, IN_SERVICE, STOPPING, OUT_OF_SERVICE, TERMINATED
}
