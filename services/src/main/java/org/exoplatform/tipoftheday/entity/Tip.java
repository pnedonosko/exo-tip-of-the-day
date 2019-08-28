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
package org.exoplatform.tipoftheday.entity;

/**
 * Created by The eXo Platform SAS.
 *
 * @author <a href="mailto:pnedonosko@exoplatform.com">Peter Nedonosko</a>
 * @version $Id: Tip.java 00000 Aug 28, 2019 pnedonosko $
 */
public class Tip {

  /** The id. */
  protected final String id;

  /** The poster. */
  protected final String poster;

  /** The posted. */
  protected final long   posted;

  /** The text. */
  protected final String text;

  /**
   * Instantiates a new tip.
   *
   * @param id the id
   * @param poster the poster
   * @param postedTimestamp the posted timestamp
   * @param text the text
   */
  public Tip(String id, String poster, long postedTimestamp, String text) {
    super();
    this.id = id;
    this.poster = poster;
    this.posted = postedTimestamp;
    this.text = text;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the text.
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Gets the poster.
   *
   * @return the poster
   */
  public String getPoster() {
    return poster;
  }

  /**
   * Gets the posted.
   *
   * @return the posted timestamp
   */
  public long getPosted() {
    return posted;
  }

}
