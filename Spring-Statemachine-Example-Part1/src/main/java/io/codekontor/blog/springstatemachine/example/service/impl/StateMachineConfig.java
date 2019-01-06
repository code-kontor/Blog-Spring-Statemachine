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

import java.util.EnumSet;

import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * The state machine configuration class.
 * 
 * @author Gerd W&uuml;therich (gw@code-kontor.io)
 */
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Trigger> {

	/**
	 * Configure the state machine itself.
	 */
	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Trigger> config) throws Exception {

		config
			.withConfiguration()
			.autoStartup(true);
	}

	/**
	 * Configure the states.
	 */
	@Override
	public void configure(StateMachineStateConfigurer<States, Trigger> states) throws Exception {

		states
			.withStates()
			.initial(States.INITIAL)
			.end(States.TERMINATED)
			.states(EnumSet.allOf(States.class));
	}

	/**
	 * Configure the transitions.
	 */
	@Override
	public void configure(StateMachineTransitionConfigurer<States, Trigger> transitions) throws Exception {

		transitions
			.withExternal()
				.source(States.INITIAL)
				.target(States.OUT_OF_SERVICE).and()
			.withExternal()
				.source(States.OUT_OF_SERVICE)
				.target(States.STARTING)
				.event(Trigger.START)
				.and()
			.withExternal()
				.source(States.STARTING)
				.target(States.IN_SERVICE)
				.and()
			.withExternal()
				.source(States.IN_SERVICE)
				.target(States.STOPPING)
				.event(Trigger.STOP)
				.and()
			.withExternal()
				.source(States.STOPPING)
				.target(States.OUT_OF_SERVICE)
				.and()
			.withExternal()
				.source(States.OUT_OF_SERVICE)
				.target(States.TERMINATED)
				.event(Trigger.TERMINATE);
	}
}
