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

import javax.swing.ImageIcon;

import org.openflexo.icon.IconLibrary;
import org.openflexo.icon.IconMarker;
import org.openflexo.icon.ImageIconResource;
import org.openflexo.rm.ResourceLocator;

/**
 * Utility class containing all icons used in context of AnnotModule
 * 
 * @author Fabien Dagnat
 * 
 */
public class AnnotIconLibrary extends IconLibrary {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AnnotIconLibrary.class.getPackage().getName());

	// Module icons
	public static final ImageIcon Annot_SMALL_ICON = new ImageIconResource(
			ResourceLocator.locateResource("Icons/Annot/module-annot-16.png"));
	public static final ImageIcon Annot_MEDIUM_ICON = new ImageIconResource(
			ResourceLocator.locateResource("Icons/Annot/module-annot-32.png"));
	public static final ImageIcon Annot_MEDIUM_ICON_WITH_HOVER = new ImageIconResource(
			ResourceLocator.locateResource("Icons/Annot/module-annot-hover-32.png"));
	public static final ImageIcon Annot_BIG_ICON = new ImageIconResource(
			ResourceLocator.locateResource("Icons/Annot/module-annot-hover-64.png"));

	public static final IconMarker Annot_BIG_MARKER = new IconMarker(Annot_MEDIUM_ICON, 32, 0);

}
