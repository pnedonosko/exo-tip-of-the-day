package com.acme.samples;

import javax.portlet.*;
import java.io.IOException;

public class HelloPortlet extends GenericPortlet {
    @RenderMode(name = "view")
    public void Hello(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        PortletRequestDispatcher prDispatcher = getPortletContext().getRequestDispatcher("/jsp/hello.jsp");
        prDispatcher.include(request, response);
    }
}
