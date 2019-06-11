package com.acme.samples;

import javax.portlet.*;
import java.io.IOException;

public class HelloPortlet extends GenericPortlet {
    @RenderMode(name = "view")
    public void hello(RenderRequest request, RenderResponse response){
        PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher("/jsp/hello.jsp");
        try {
            portletRequestDispatcher.include(request,response);
        } catch (PortletException e) {
            System.out.println("Portlet Exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }
}
