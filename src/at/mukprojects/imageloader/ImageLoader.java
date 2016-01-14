/**
 * This code is copyright (c) Mathias Markl 2016
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package at.mukprojects.imageloader;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import at.mukprojects.imageloader.image.ImageList;
import processing.core.PApplet;

/**
 * Base class for any ImageLoader.
 *
 * @author Mathias Markl
 */
public abstract class ImageLoader {

    /**
     * Logger Settings
     */
    private static final String PATTERN = "%5p (%C{1}) - %m%n";
    private static final String DEFAULT_APPENDER = "DefaultConsoleAppender";

    /**
     * The Processing PApplet.
     */
    protected PApplet applet;

    /**
     * Constructs a new ImageLoader.
     * 
     * @param applet
     *            The Processing PApplet.
     */
    public ImageLoader(PApplet applet) {
	this.applet = applet;

	/*
	 * Logger configuration
	 */
	if (!Logger.getRootLogger().getAllAppenders().hasMoreElements()) {
	    Logger.getRootLogger().setLevel(Level.DEBUG);

	    ConsoleAppender appender = new ConsoleAppender(new PatternLayout(PATTERN));
	    appender.setName(DEFAULT_APPENDER);
	    Logger.getRootLogger().addAppender(appender);
	}
    }

    /**
     * Starts the loader.
     * 
     * @param searchParam
     *            The search parameter.
     * @return The used ImageList.
     */
    public abstract ImageList start(String searchParam);

    /**
     * Starts the loader.
     * 
     * @param searchParam
     *            The search parameter.
     * @param imageList
     *            The ImageList, which should be used.
     * @return The used ImageList.
     */
    public abstract ImageList start(String searchParam, ImageList imageList);

    /**
     * Restarts the loader.
     * 
     * @param searchParam
     *            The search parameter.
     * @return The used ImageList.
     */
    public abstract ImageList restart(String searchParam);

    /**
     * Restarts the loader.
     * 
     * @param searchParam
     *            The search parameter.
     * @param imageList
     *            The ImageList, which should be used.
     * @return The used ImageList.
     */
    public abstract ImageList restart(String searchParam, ImageList imageList);

    /**
     * Stops the loader.
     */
    public abstract void stop();
}
