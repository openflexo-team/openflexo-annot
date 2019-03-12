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

import javax.swing.ImageIcon;

import org.openflexo.annotmodule.AnnotIconLibrary;
import org.openflexo.annotmodule.model.AnnotProjectNature;
import org.openflexo.foundation.fml.rt.FMLRTVirtualModelInstance;
import org.openflexo.gina.model.FIBComponent;
import org.openflexo.gina.view.GinaViewFactory;
import org.openflexo.icon.IconFactory;
import org.openflexo.icon.IconLibrary;
import org.openflexo.rm.Resource;
import org.openflexo.rm.ResourceLocator;
import org.openflexo.technologyadapter.gina.model.FMLFIBBindingFactory;
import org.openflexo.view.FIBModuleView;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.controller.FlexoFIBController;
import org.openflexo.view.controller.model.FlexoPerspective;

/**
 * Main view for Annot module
 */
@SuppressWarnings("serial")
public class AnnotProjectNatureModuleView extends FIBModuleView<AnnotProjectNature> {

	public static Resource Annot_PROJECT_MODULE_VIEW_FIB = ResourceLocator.locateResource("Fib/AnnotProjectNaturePanel.fib");

	private final FlexoPerspective perspective;

	public AnnotProjectNatureModuleView(AnnotProjectNature nature, FlexoController controller, FlexoPerspective perspective) {
		super(nature, controller, Annot_PROJECT_MODULE_VIEW_FIB, controller.getModule().getLocales());
		this.perspective = perspective;

		// FIBBrowserWidget<?, ?> browserView = (FIBBrowserWidget<?, ?>) getFIBView("ElementBrowser");
		// System.out.println("Found browser: " + browserView);
		/*browser = retrieveFIBBrowserNamed((FIBContainer) getFIBComponent(), "ElementBrowser");
		if (browser != null) {
			bindFlexoActionsToBrowser(browser);
		}*/

		setDataObject(nature);

	}

	@Override
	public FlexoPerspective getPerspective() {
		return perspective;
	}

	@Override
	public AnnotProjectNature getDataObject() {
		return (AnnotProjectNature) super.getDataObject();
	}

	@Override
	public void initializeFIBComponent() {

		super.initializeFIBComponent();

		getFIBComponent().setBindingFactory(new FMLFIBBindingFactory(getDataObject().getAnnotViewPoint()));

	}

	public static class AnnotProjectNatureModuleViewFIBController extends FlexoFIBController {
		public AnnotProjectNatureModuleViewFIBController(FIBComponent component, GinaViewFactory<?> viewFactory) {
			super(component, viewFactory);
		}

		public AnnotProjectNatureModuleViewFIBController(FIBComponent component, GinaViewFactory<?> viewFactory,
				FlexoController controller) {
			super(component, viewFactory, controller);
		}

		public ImageIcon getProjectIcon() {
			return IconFactory.getImageIcon(IconLibrary.OPENFLEXO_NOTEXT_64, AnnotIconLibrary.Annot_BIG_MARKER);
		}

		public void openVMI(FMLRTVirtualModelInstance vmi) {
			getFlexoController().selectAndFocusObject(vmi);
		}
	}

}
