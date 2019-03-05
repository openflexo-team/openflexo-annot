/**
 * 
 * Copyright (c) 2013-2014, Openflexo
 * Copyright (c) 2011-2012, AgileBirds
 * 
 * This file is part of Flexovieweditor, a component of the software infrastructure 
 * developed at Openflexo.
 * 
 * 
 * Openflexo is dual-licensed under the European Union Public License (EUPL, either 
 * version 1.1 of the License, or any later version ), which is available at 
 * https://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 * and the GNU General Public License (GPL, either version 3 of the License, or any 
 * later version), which is available at http://www.gnu.org/licenses/gpl.html .
 * 
 * You can redistribute it and/or modify under the terms of either of these licenses
 * 
 * If you choose to redistribute it and/or modify under the terms of the GNU GPL, you
 * must include the following additional permission.
 *
 *          Additional permission under GNU GPL version 3 section 7
 *
 *          If you modify this Program, or any covered work, by linking or 
 *          combining it with software containing parts covered by the terms 
 *          of EPL 1.0, the licensors of this Program grant you additional permission
 *          to convey the resulting work. * 
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE. 
 *
 * See http://www.openflexo.org/license.html for details.
 * 
 * 
 * Please contact Openflexo (openflexo-contacts@openflexo.org)
 * or visit www.openflexo.org if you need additional information.
 * 
 */

package org.openflexo.annotmodule.controller.action;

import java.util.logging.Logger;

import org.openflexo.foundation.FlexoObject;
import org.openflexo.foundation.action.FlexoActionRunnable;
import org.openflexo.foundation.action.FlexoExceptionHandler;
import org.openflexo.foundation.action.SetPropertyAction;
import org.openflexo.view.controller.ActionInitializer;
import org.openflexo.view.controller.ControllerActionInitializer;
import org.openflexo.view.controller.FlexoController;

public class AnnotSetPropertyInitializer extends ActionInitializer<SetPropertyAction, FlexoObject, FlexoObject> {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ControllerActionInitializer.class.getPackage().getName());

	public AnnotSetPropertyInitializer(ControllerActionInitializer actionInitializer) {
		super(SetPropertyAction.actionType, actionInitializer);
	}

	@Override
	protected FlexoActionRunnable<SetPropertyAction, FlexoObject, FlexoObject> getDefaultInitializer() {
		return (e, action) -> action.getFocusedObject() != null;
	}

	@Override
	protected FlexoActionRunnable<SetPropertyAction, FlexoObject, FlexoObject> getDefaultFinalizer() {
		return (e, action) -> true;
	}

	@Override
	protected FlexoExceptionHandler<SetPropertyAction, FlexoObject, FlexoObject> getDefaultExceptionHandler() {
		return (exception, action) -> {
			exception.printStackTrace();
			FlexoController.notify(action.getLocales().localizedForKey("could_not_set_property") + " "
					+ (action.getLocalizedPropertyName() != null ? "'" + action.getLocalizedPropertyName() + "' " : "")
					+ action.getLocales().localizedForKey("to") + " "
					+ (action.getValue() == null || action.getValue().equals("") ? action.getLocales().localizedForKey("empty_value")
							: action.getValue())
					+ (exception.getLocalizedMessage() != null
							? "\n(" + action.getLocales().localizedForKey("details: ") + exception.getLocalizedMessage() + ")"
							: ""));
			return true;
		};
	}

}