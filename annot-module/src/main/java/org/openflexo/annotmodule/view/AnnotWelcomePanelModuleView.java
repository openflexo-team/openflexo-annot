/*
 * Copyright (c) 2019 Openflexo
 *
 * This file is part of Annot, a module of the software infrastructure 
 * developed at Openflexo.
 *
 * OpenFlexo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenFlexo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenFlexo. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.openflexo.annotmodule.view;

import org.openflexo.annotmodule.AnnotModule;
import org.openflexo.module.FlexoModule.WelcomePanel;
import org.openflexo.rm.Resource;
import org.openflexo.rm.ResourceLocator;
import org.openflexo.view.FIBModuleView;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.controller.model.FlexoPerspective;

/**
 * WelcomePanel for Annot module
 */
@SuppressWarnings("serial")
public class AnnotWelcomePanelModuleView extends FIBModuleView<WelcomePanel<AnnotModule>> {

	public static Resource WELCOME_MODULE_VIEW_FIB = ResourceLocator.locateResource("Fib/AnnotWelcomePanel.fib");

	private final FlexoPerspective perspective;

	public AnnotWelcomePanelModuleView(WelcomePanel<AnnotModule> welcomePanel, FlexoController controller, FlexoPerspective perspective) {
		super(welcomePanel, controller, WELCOME_MODULE_VIEW_FIB, welcomePanel.getModule().getLocales());
		this.perspective = perspective;
	}

	@Override
	public FlexoPerspective getPerspective() {
		return perspective;
	}
}
