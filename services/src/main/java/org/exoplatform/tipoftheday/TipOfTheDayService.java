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

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import org.picocontainer.Startable;

import org.exoplatform.container.xml.InitParams;
import org.exoplatform.container.xml.ValuesParam;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.tipoftheday.entity.Tip;

/**
 * Created by The eXo Platform SAS
 * 
 * @author <a href="mailto:pnedonosko@exoplatform.com">Peter Nedonosko</a>
 * @version $Id: TipOfTheDayService.java 00000 Aug 28, 2019 pnedonosko $
 */
public class TipOfTheDayService implements Startable {

  /** The Constant LOG. */
  protected static final Log  LOG           = ExoLogger.getLogger(TipOfTheDayService.class);

  private static final String SYSTEM_POSTER = "system".intern();

  private AtomicLong          tipIdSequence = new AtomicLong(1);

  private Map<String, Tip>    tips          = new ConcurrentHashMap<>();

  /**
   * Sneaky throw given exception. An idea grabbed from
   * <a href= "https://www.baeldung.com/java-sneaky-throws">https://www.baeldung.com/java-sneaky-throws</a><br>
   *
   * @param <E> the element type
   * @param e the e
   * @throws E the e
   */
  @SuppressWarnings("unchecked")
  static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
    throw (E) e;
  }

  /**
   * 
   */
  public TipOfTheDayService(InitParams config) {
    ValuesParam initTips = config.getValuesParam("initial.tips");
    if (initTips != null) {
      for (String text : initTips.getValues()) {
        addTip(SYSTEM_POSTER, text);
      }
    } else {
      LOG.warn("Initial tips not configured, start with empty database");
    }
  }

  public Tip addTip(String poster, String text) {
    String id = String.valueOf(tipIdSequence.getAndIncrement());
    Tip tip = new Tip(id, poster, Calendar.getInstance().getTimeInMillis(), text);
    tips.put(id, tip);
    return tip;
  }

  /**
   * Update a tip by ID and poster name.
   *
   * @param id the id
   * @param poster the poster
   * @param text the text
   * @return the tip
   * @throws WrongPosterException if attempts to update by another poster
   */
  public Tip updateTip(String id, String poster, String text) throws WrongPosterException {
    return tips.computeIfPresent(id, (tid, oldTip) -> {
      if (oldTip.getPoster().equals(poster)) {
        return new Tip(id, poster, Calendar.getInstance().getTimeInMillis(), text);
      } else {
        // Don't update if posted by another, but throw an exception
        sneakyThrow(new WrongPosterException("Cannot update tip by another poster"));
        return null; // this will not happen
      }
    });
  }

  /**
   * Delete a tip.
   *
   * @param id the id
   * @return the tip
   */
  public Tip deleteTip(String id) {
    Tip existingTip = tips.get(id);
    if (tips.remove(id, existingTip)) {
      // Deleted exactly what we got.
      return existingTip;
    } else {
      return null;
    }
  }

  /**
   * Gets the tip.
   *
   * @param id the id
   * @return the tip
   */
  public Tip getTip(String id) {
    return tips.get(id);
  }

  /**
   * Gets the all tips.
   *
   * @return the all tips
   */
  public Collection<Tip> getAllTips() {
    return Collections.unmodifiableCollection(tips.values());
  }

  /**
   * Gets the tips number.
   *
   * @return the tips number
   */
  public long getTipsNumber() {
    return tips.size();
  }

  /**
   * Gets the random tip.
   *
   * @return the random tip
   */
  public Tip getRandomTip() {
    long rndId = ThreadLocalRandom.current().nextLong(1, tips.size());
    return getTip(String.valueOf(rndId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start() {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop() {
    // TODO Auto-generated method stub
  }

}
