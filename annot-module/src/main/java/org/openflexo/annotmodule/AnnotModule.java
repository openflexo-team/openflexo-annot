/**
 * 
 * Copyright (c) 2019, Openflexo
 * 
 * This file is part of Annot, a module of the software infrastructure 
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

package org.openflexo.annotmodule;

import java.util.logging.Logger;

import org.openflexo.ApplicationContext;
import org.openflexo.annotmodule.controller.AnnotController;
import org.openflexo.module.FlexoModule;
import org.openflexo.view.controller.FlexoController;

/**
 * Annot module
 * 
 * @author Fabien Dagnat
 */
public class AnnotModule extends FlexoModule<AnnotModule> {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AnnotModule.class.getPackage().getName());

	public static final String Annot_MODULE_SHORT_NAME = "Annot";
	public static final String Annot_MODULE_NAME = "annot_module_name";

	/*private JDianaInteractiveEditor<?> screenshotController;
	private JDrawingView<?> screenshot = null;
	private boolean drawWorkingArea;
	private FlexoObject screenshotObject;*/

	public AnnotModule(ApplicationContext applicationContext) throws Exception {
		super(applicationContext);
	}

	@Override
	public String getLocalizationDirectory() {
		return "FlexoLocalization/AnnotModule";
	}

	@Override
	protected FlexoController createControllerForModule() {
		return new AnnotController(this);
	}

	@Override
	public Annot getModule() {
		return getApplicationContext().getModuleLoader().getModule(Annot.class);
	}

	public AnnotController getAnnotController() {
		return (AnnotController) getFlexoController();
	}

	@Override
	public AnnotPreferences getPreferences() {
		return (AnnotPreferences) super.getPreferences();
	}

	@Override
	public boolean close() {
		if (getApplicationContext().getResourceManager().getUnsavedResources().size() == 0) {
			return super.close();
		}
		if (getAnnotController().reviewModifiedResources()) {
			return super.close();
		}
		return false;
	}
}
