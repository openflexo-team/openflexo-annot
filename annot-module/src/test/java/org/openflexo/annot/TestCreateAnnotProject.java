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

package org.openflexo.annot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openflexo.OpenflexoProjectAtRunTimeTestCaseWithGUI;
import org.openflexo.annotmodule.AnnotCst;
import org.openflexo.annotmodule.model.AnnotProjectNature;
import org.openflexo.annotmodule.model.action.GivesAnnotNature;
import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.FlexoProject;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.rm.VirtualModelResource;
import org.openflexo.foundation.resource.FlexoResource;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.test.OrderedRunner;
import org.openflexo.test.TestOrder;

/**
 * We test here the creation of a project
 * 
 * 
 * @author Fabien Dagnat
 *
 */
@RunWith(OrderedRunner.class)
public class TestCreateAnnotProject extends OpenflexoProjectAtRunTimeTestCaseWithGUI {

	private static FlexoProject<?> project;
	private static FlexoEditor editor;

	/**
	 * Instantiate test resource center
	 * 
	 * @throws FlexoException
	 * @throws ResourceLoadingCancelledException
	 * @throws FileNotFoundException
	 */
	@BeforeClass
	public static void testInitializeServiceManager() throws Exception {
		instanciateTestServiceManager();
	}

	@Test
	@TestOrder(1)
	public void loadViewPoint() {
		log("Testing ViewPoint loading: " + AnnotCst.VIEWPOINT_URI);

		// Find the virtual model as a resource
		FlexoResource<VirtualModel> vpRes = serviceManager.getResourceManager().getResource(AnnotCst.VIEWPOINT_URI, VirtualModel.class);

		// Ensure it exists and not loaded
		assertNotNull("the resource of the virtual model is null", vpRes);
		assertFalse("the resource is already loaded", vpRes.isLoaded());

		// Get the virtual model out of its resource and ensure it is not null
		assertNotNull("the virtual model is null", ((VirtualModelResource) vpRes).getVirtualModel());

		// Check that the virtual model is now loaded
		assertTrue("the virtual model is not loaded", vpRes.isLoaded());
	}

	@Test
	@TestOrder(2)
	public void createProject() {
		log("createProject");

		// Create project and its associated session (the editor)
		editor = createStandaloneProject("TestCreateView");
		assertNotNull("The created editor is null", editor);

		// Test that the created project exists
		project = editor.getProject();
		assertNotNull("the project is null", project);

		// Ensure the project is not yet an Annot project
		assertFalse("the project is already of nature Annot", project.hasNature(AnnotProjectNature.class));
	}

	@Test
	@TestOrder(3)
	public void givesProjectAnnotNature() {
		log("givesProjectAnnotNature");

		// Create action to give nature Annot
		GivesAnnotNature givesNatureAction = GivesAnnotNature.actionType.makeNewAction(project, null, editor);

		// Execute action to give nature Annot
		givesNatureAction.doAction();

		// Test success of the action
		assertTrue("gives annot nature action failed", givesNatureAction.hasActionExecutionSucceeded());

		// Ensure the project has the right nature
		assertTrue("the newly created project has the wrong nature", project.hasNature(AnnotProjectNature.class));

		// Ensure the nature object is not null
		AnnotProjectNature annotProjectNature = project.getNature(AnnotProjectNature.class);
		assertNotNull("the nature object within the project is null", annotProjectNature);

		// Check that the FML instance is not null
		assertNotNull("the FML instance is null", annotProjectNature.getAnnotInstance().getAccessedVirtualModelInstance());
	}
}
