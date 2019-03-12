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

package org.openflexo.annotmodule.view.menu;

import org.openflexo.annotmodule.Annot;
import org.openflexo.annotmodule.controller.AnnotController;
import org.openflexo.module.Module;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.menu.EditMenu;
import org.openflexo.view.menu.FileMenu;
import org.openflexo.view.menu.FlexoMenuBar;
import org.openflexo.view.menu.ToolsMenu;
import org.openflexo.view.menu.WindowMenu;

/**
 * Class representing menus related to this module
 * 
 * @author Fabien Dagnat
 */
@SuppressWarnings("serial")
public class AnnotMenuBar extends FlexoMenuBar {

	private AnnotFileMenu fileMenu;
	private AnnotEditMenu editMenu;
	private AnnotToolsMenu toolsMenu;

	public AnnotMenuBar(AnnotController controller) {
		super(controller, controller.getApplicationContext().getModuleLoader().getModule(Annot.class));
	}

	/**
	 * Build if required and return WKF 'File' menu. This method overrides the default one defined on superclass
	 * 
	 * @param controller
	 * @return a AnnotFileMenu instance
	 */
	@Override
	public FileMenu getFileMenu(FlexoController controller) {
		if (fileMenu == null) {
			fileMenu = new AnnotFileMenu((AnnotController) controller);
		}
		return fileMenu;
	}

	/**
	 * Build if required and return WKF 'Edit' menu. This method overrides the default one defined on superclass
	 * 
	 * @param controller
	 * @return a AnnotEditMenu instance
	 */
	@Override
	public EditMenu getEditMenu(FlexoController controller) {
		if (editMenu == null) {
			editMenu = new AnnotEditMenu((AnnotController) controller);
		}
		return editMenu;
	}

	/**
	 * Build if required and return WKF 'Window' menu. This method overrides the default one defined on superclass
	 * 
	 * @param controller
	 * @return a AnnotWindowMenu instance
	 */
	@Override
	public WindowMenu getWindowMenu(FlexoController controller, Module<?> module) {
		if (windowMenu == null) {
			windowMenu = new AnnotWindowMenu((AnnotController) controller);
		}
		return windowMenu;
	}

	/**
	 * Build if required and return WKF 'Tools' menu. This method overrides the default one defined on superclass
	 * 
	 * @param controller
	 * @return a AnnotToolsMenu instance
	 */
	public ToolsMenu getToolsMenu(FlexoController controller, Module<?> module) {
		if (toolsMenu == null) {
			toolsMenu = new AnnotToolsMenu((AnnotController) controller);
		}
		return toolsMenu;
	}

}
