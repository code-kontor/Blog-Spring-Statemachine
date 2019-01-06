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
package io.codekontor.blog.springstatemachine.example.service;

/**
 * 
 * @author Gerd W&uuml;therich (gw@code-kontor.io)
 */
public interface IExampleService {

	/**
	 * 
	 */
	void start();
	
	/**
	 * 
	 */
	void stop();
	
	/**
	 * 
	 */
	void terminate();
}
