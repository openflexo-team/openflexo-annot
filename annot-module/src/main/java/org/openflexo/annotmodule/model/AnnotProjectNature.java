/**
 * 
 * Copyright (c) 2019, Openflexo
 * 
 * This file is part of Annot, a module of the software infrastructure developed at Openflexo.
 * 
 * 
 * Openflexo is dual-licensed under the European Union Public License (EUPL, either version 1.1 of the License, or any later version ),
 * which is available at https://joinup.ec.europa.eu/software/page/eupl/licence-eupl and the GNU General Public License (GPL, either version
 * 3 of the License, or any later version), which is available at http://www.gnu.org/licenses/gpl.html .
 * 
 * You can redistribute it and/or modify under the terms of either of these licenses
 * 
 * If you choose to redistribute it and/or modify under the terms of the GNU GPL, you must include the following additional permission.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or combining it with software containing parts covered by the terms of EPL
 * 1.0, the licensors of this Program grant you additional permission to convey the resulting work. *
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See http://www.openflexo.org/license.html for details.
 * 
 * 
 * Please contact Openflexo (openflexo-contacts@openflexo.org) or visit www.openflexo.org if you need additional information.
 * 
 */

package org.openflexo.annotmodule.model;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.openflexo.annotmodule.AnnotCst;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.FlexoProject;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.nature.ProjectNature;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.pamela.annotations.Getter;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.PropertyIdentifier;
import org.openflexo.pamela.annotations.Setter;
import org.openflexo.pamela.annotations.XMLElement;

/**
 * This API is used to interpret a {@link FlexoProject} as a Annot project <br>
 *
 * 
 * @author Fabien Dagnat
 */
@ModelEntity
@XMLElement
@ImplementationClass(AnnotProjectNature.AnnotProjectNatureImpl.class)
public interface AnnotProjectNature extends ProjectNature<AnnotProjectNature> {

	@PropertyIdentifier(type = AnnotInstance.class)
	public static final String Annot_INSTANCE = "AnnotInstance";

	@Getter(value = Annot_INSTANCE, inverse = AnnotInstance.NATURE)
	@XMLElement
	public AnnotInstance getAnnotInstance();

	@Setter(Annot_INSTANCE)
	public void setAnnotInstance(AnnotInstance annotInstance);

	public VirtualModel getAnnotViewPoint();

	public abstract class AnnotProjectNatureImpl extends ProjectNatureImpl<AnnotProjectNature> implements AnnotProjectNature {
		@SuppressWarnings("unused")
		private static final Logger logger = Logger.getLogger(AnnotProjectNature.class.getPackage().getName());

		private VirtualModel annotViewpoint;

		@Override
		public VirtualModel getAnnotViewPoint() {
			if (annotViewpoint == null && getServiceManager() != null) {
				try {
					annotViewpoint = getServiceManager().getVirtualModelLibrary().getVirtualModel(AnnotCst.VIEWPOINT_URI);
				} catch (FileNotFoundException | ResourceLoadingCancelledException | FlexoException e) {
					e.printStackTrace();
				}
			}
			return annotViewpoint;
		}

	}

}
