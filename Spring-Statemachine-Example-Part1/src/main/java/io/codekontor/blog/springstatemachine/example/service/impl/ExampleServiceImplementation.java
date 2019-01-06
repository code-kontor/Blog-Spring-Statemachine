/*******************************************************************************
 * Copyright (C) 2019 Code-Kontor GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Code-Kontor GmbH - initial API and implementation
 ******************************************************************************/
package io.codekontor.blog.springstatemachine.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnStateEntry;
import org.springframework.statemachine.annotation.WithStateMachine;

import io.codekontor.blog.springstatemachine.example.service.IExampleService;

/**
 * The implementation of the example service.
 *  
 * @author Gerd W&uuml;therich (gw@code-kontor.io)
 */
@WithStateMachine
public class ExampleServiceImplementation implements IExampleService {

	/** the logger instance */
	private final Logger logger = LoggerFactory.getLogger(ExampleServiceImplementation.class);

	/** the spring generated state machine */
	@Autowired
	private StateMachine<States, Trigger> _stateMachine;
	
	@Override
	public void start() {
		triggerStateChange(Trigger.START);
	}

	@Override
	public void stop() {
		triggerStateChange(Trigger.STOP);
	}

	@Override
	public void terminate() {
		triggerStateChange(Trigger.TERMINATE);
	}

	
	/**
	 * Entry-Action for the STARTING state.
	 *  
	 * @param stateContext
	 */
	@OnStateEntry(target = "STARTING")
	public void onEntrySTARTING(StateContext<States, Trigger> stateContext) {

		logger.info("Entered state: {}", stateContext.getTarget().getId());
	}

	/**
	 * Entry-Action for the STOPPING states.
	 * 
	 * @param stateContext
	 */
	@OnStateEntry(target = "STOPPING")
	public void onEntrySTOPPING(StateContext<States, Trigger> stateContext) {

		logger.info("Entered state: {}", stateContext.getTarget().getId());
	}
	
	/**
	 * Trigger the state machine with the specified trigger. If the trigger is not valid in the current state, a {@link RuntimeException} is thrown.
	 * 
	 * @param trigger the trigger
	 */
	private void triggerStateChange(Trigger trigger) {
		
		if (!_stateMachine.sendEvent(trigger)) {
			String msg = String.format("Trigger '%s' was not accepted. Current state: %s.", trigger.name(), _stateMachine.getState().getId().name());
	    	throw new RuntimeException(msg);
		}
	}
}
