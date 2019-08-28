package com.acme.samples;

import javax.portlet.*;
import java.io.IOException;

public class Tip4DayPortlet extends GenericPortlet {
    @RenderMode(name = "view")
    public void showApp(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        PortletRequestDispatcher prDispatcher = getPortletContext().getRequestDispatcher("/jsp/app.jsp");
        prDispatcher.include(request, response);
    }
}
