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
package org.exoplatform.tipoftheday;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.tipoftheday.entity.Tip;

/**
 * Rest User Service.
 */
@Path("/tipoftheday")
@Produces(MediaType.APPLICATION_JSON)
public class RESTTipOfTheDayService implements ResourceContainer {

  private final TipOfTheDayService tips;

  public RESTTipOfTheDayService(TipOfTheDayService tips) {
    this.tips = tips;
  }

  /**
   * Adds the new tip.
   *
   * @param tip the tip
   * @return the map
   */
  @POST
  @Path("/tip")
  @RolesAllowed("users")
  public Response addNewTip(@FormParam("text") String text) {
    if (text == null || text.length() == 0) {
      return Response.status(Status.BAD_REQUEST).entity("{\"error\": \"\"Tip text required\"\"}").build();
    }
    Tip tip = tips.getAllTips().stream().filter(t -> t.getText().equals(text)).findFirst().orElse(null);
    if (tip == null) {
      ConversationState convo = ConversationState.getCurrent();
      if (convo != null) {
        return Response.ok().entity(tips.addTip(convo.getIdentity().getUserId(), text)).build();
      } else {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"\"User not found\"\"}").build();
      }
    } else {
      return Response.status(Status.BAD_REQUEST).entity("{\"error\": \"\"Tip already exists\"\"}").build();
    }
  }

  /**
   * Gets the all tip.
   *
   * @return the all tip
   */
  @GET
  @Path("/tip")
  @RolesAllowed("users")
  public Response getAllTips() {
    return Response.ok().entity(tips.getAllTips()).build();
  }

  /**
   * Gets the tip.
   *
   * @param id the id
   * @return the tip
   */
  @GET
  @Path("/tip/{id}")
  @RolesAllowed("users")
  public Response getTip(@PathParam("id") String id) {
    Tip tip = tips.getTip(id);
    if (tip != null) {
      return Response.ok().entity(tip).build();
    } else {
      return Response.status(Status.BAD_REQUEST).entity("{\"error\": \"\"Tip not found\"\"}").build();
    }
  }

  /**
   * Update tip.
   *
   * @param id the id
   * @param tip the tip
   * @return the map
   */
  @PUT
  @Path("/tip/{id}")
  @RolesAllowed("users")
  public Response updateTip(@PathParam("id") String id, @FormParam("text") String text) {
    ConversationState convo = ConversationState.getCurrent();
    if (convo != null) {
      String username = convo.getIdentity().getUserId();
      try {
        return Response.ok().entity(tips.updateTip(id, username, text)).build();
      } catch (WrongPosterException e) {
        return Response.status(Status.BAD_REQUEST).entity("{\"error\": \"\"Poster does not match\"\"}").build();
      }
    } else {
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"\"User not found\"\"}").build();
    }
  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @DELETE
  @Path("/tip/{id}")
  @RolesAllowed("users")
  public Response delete(@PathParam("id") String id) {
    Tip tip = tips.deleteTip(id);
    if (tip != null) {
      return Response.ok().entity(tip).build();
    } else {
      return Response.status(Status.BAD_REQUEST).entity("{\"error\": \"\"Tip not found\"\"}").build();
    }
  }

  /**
   * Gets the some random tip.
   *
   * @return the some tip
   */
  @GET
  @Path("/random")
  @RolesAllowed("users")
  public Response getRandomTip() {
    return Response.ok().entity(tips.getRandomTip()).build();
  }
}
