/**
 * 
 * Copyright (c) 2014-2015, Openflexo
 * 
 * This file is part of Freemodellingeditor, a component of the software infrastructure 
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

package org.openflexo.annotmodule.model.action;

import java.io.FileNotFoundException;
import java.util.Vector;
import java.util.logging.Logger;

import org.openflexo.ApplicationContext;
import org.openflexo.action.ModuleSpecificFlexoAction;
import org.openflexo.annotmodule.Annot;
import org.openflexo.annotmodule.AnnotCst;
import org.openflexo.annotmodule.AnnotModule;
import org.openflexo.annotmodule.model.AnnotInstance;
import org.openflexo.annotmodule.model.AnnotProjectNature;
import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.FlexoObject;
import org.openflexo.foundation.FlexoObject.FlexoObjectImpl;
import org.openflexo.foundation.FlexoProject;
import org.openflexo.foundation.action.FlexoActionFactory;
import org.openflexo.foundation.fml.CreationScheme;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.rt.FMLRTVirtualModelInstance;
import org.openflexo.foundation.fml.rt.action.CreateBasicVirtualModelInstance;
import org.openflexo.foundation.fml.rt.rm.FMLRTVirtualModelInstanceResource;
import org.openflexo.foundation.nature.GivesNatureAction;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.localization.LocalizedDelegate;

/**
 * This action is called to gives Annot nature to a project
 * 
 * @author yourname
 */
@SuppressWarnings("deprecation")
public class GivesAnnotNature extends GivesNatureAction<GivesAnnotNature, AnnotProjectNature>
		implements ModuleSpecificFlexoAction<AnnotModule> {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(GivesAnnotNature.class.getPackage().getName());

	public static FlexoActionFactory<GivesAnnotNature, FlexoProject<?>, FlexoObject> actionType = new FlexoActionFactory<GivesAnnotNature, FlexoProject<?>, FlexoObject>(
			"gives_annot_nature") {

		/**
		 * Factory method
		 */
		@Override
		public GivesAnnotNature makeNewAction(FlexoProject<?> focusedObject, Vector<FlexoObject> globalSelection, FlexoEditor editor) {
			return new GivesAnnotNature(focusedObject, globalSelection, editor);
		}

		@Override
		public boolean isVisibleForSelection(FlexoProject<?> project, Vector<FlexoObject> globalSelection) {
			return true;
		}

		@Override
		public boolean isEnabledForSelection(FlexoProject<?> project, Vector<FlexoObject> globalSelection) {
			return project != null && !project.hasNature(AnnotProjectNature.class);
		}

	};

	static {
		FlexoObjectImpl.addActionForClass(GivesAnnotNature.actionType, FlexoProject.class);
	}

	GivesAnnotNature(FlexoProject<?> focusedObject, Vector<FlexoObject> globalSelection, FlexoEditor editor) {
		super(actionType, focusedObject, globalSelection, editor);
	}

	@Override
	public LocalizedDelegate getLocales() {
		if (getServiceManager() instanceof ApplicationContext) {
			return ((ApplicationContext) getServiceManager()).getModuleLoader().getModule(Annot.class).getLoadedModuleInstance()
					.getLocales();
		}
		return super.getLocales();
	}

	@Override
	public Class<AnnotModule> getFlexoModuleClass() {
		return AnnotModule.class;
	}

	public VirtualModel getAnnotVirtualModel() {
		if (getServiceManager() != null) {
			try {
				return getServiceManager().getVirtualModelLibrary().getVirtualModel(AnnotCst.Annot_VIEWPOINT_URI);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ResourceLoadingCancelledException e) {
				e.printStackTrace();
			} catch (FlexoException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public AnnotProjectNature makeNewNature() {

		AnnotProjectNature nature = getFocusedObject().getModelFactory().newInstance(AnnotProjectNature.class);

		AnnotInstance formoseVMI = retrieveAnnotInstance();
		if (formoseVMI == null) {
			formoseVMI = makeAnnotInstance();
		}

		nature.setAnnotInstance(formoseVMI);

		return nature;
	}

	private AnnotInstance retrieveAnnotInstance() {

		VirtualModel formoseVirtualModel = getAnnotVirtualModel();
		if (formoseVirtualModel == null) {
			return null;
		}
		for (FMLRTVirtualModelInstanceResource viewResource : getFocusedObject().getVirtualModelInstanceRepository().getAllResources()) {
			if (viewResource.getVirtualModelResource() != null
					&& viewResource.getVirtualModelResource() == formoseVirtualModel.getResource()) {
				try {

					AnnotInstance newAnnotVMI = getFocusedObject().getModelFactory().newInstance(AnnotInstance.class);
					newAnnotVMI.setAccessedVirtualModelInstance(viewResource.getResourceData());
					return newAnnotVMI;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ResourceLoadingCancelledException e) {
					e.printStackTrace();
				} catch (FlexoException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	private AnnotInstance makeAnnotInstance() {

		VirtualModel annotVirtualModel = getAnnotVirtualModel();
		if (annotVirtualModel == null) {
			return null;
		}

		CreateBasicVirtualModelInstance action = CreateBasicVirtualModelInstance.actionType
				.makeNewEmbeddedAction(getFocusedObject().getVirtualModelInstanceRepository().getRootFolder(), null, this);
		action.setNewVirtualModelInstanceName(AnnotCst.Annot_VIEW_NAME);
		action.setNewVirtualModelInstanceTitle(AnnotCst.Annot_VIEW_NAME);
		action.setVirtualModel(annotVirtualModel);

		CreationScheme annotViewCreationScheme = (CreationScheme) annotVirtualModel.getFlexoBehaviour("create");

		action.setCreationScheme(annotViewCreationScheme);
		action.doAction();
		FMLRTVirtualModelInstance annotView = action.getNewVirtualModelInstance();

		AnnotInstance newAnnotInstance = getFocusedObject().getModelFactory().newInstance(AnnotInstance.class);
		newAnnotInstance.setAccessedVirtualModelInstance(annotView);
		return newAnnotInstance;

	}

}
