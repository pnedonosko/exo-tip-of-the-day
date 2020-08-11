/*
 * Copyright (C) 2003-2019 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.tipoftheday.portlet;

import java.io.IOException;

import javax.portlet.*;

import org.exoplatform.web.application.JavascriptManager;
import org.exoplatform.webui.application.WebuiRequestContext;

/**
 * The Class TipOfTheDayPortlet.
 */
public class TipOfTheDayPortlet extends GenericPortlet {
  
  /**
   * Show portlet app.
   *
   * @param request the request
   * @param response the response
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws PortletException the portlet exception
   */
  @RenderMode(name = "view")
  public void showApp(RenderRequest request, RenderResponse response) throws IOException, PortletException {
    // Base app markup (container)
    PortletRequestDispatcher prDispatcher = getPortletContext().getRequestDispatcher("/jsp/app.jsp");
    prDispatcher.include(request, response);
    // App script module
    JavascriptManager js = ((WebuiRequestContext) WebuiRequestContext.getCurrentInstance()).getJavascriptManager();
    js.require("SHARED/tipofthedayApp", "tipofthedayApp").addScripts("tipofthedayApp.init();");
  }
}
