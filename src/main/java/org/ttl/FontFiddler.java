package org.ttl;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Make Swing fonts larger to work better on modern screens.
 * To use, put this code somewhere at the top of your main
 * method, before you start making the GUI.
 * <code>
 * int minSize = FontFiddler.getMinFontSizeForScreen();
 * Map<String, FontUIResource> newFonts = FontFiddler.setUIMinFontSize(minSize);
 * newFonts.forEach((k, v) -> {
 *      UIManager.put(k, v);
 * });
 * </code>
 */
public class FontFiddler {

    public static Map<String, FontUIResource> setUIMinFontSize(int minSize) {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        HashMap<String, FontUIResource> result = new HashMap<>();
        Enumeration<?> enumm = defaults.keys();

        while (enumm.hasMoreElements()) {
            String key = enumm.nextElement().toString();
            Object value = defaults.get(key);
            if (key.contains("font")) {
                FontUIResource ofr = (FontUIResource) value;
                FontUIResource nfr = ofr.getSize() >= minSize ? ofr
                        : new FontUIResource(ofr.getFontName(), ofr.getStyle(), minSize);
                result.put(key, nfr);
            }
        }

        return result;
    }

    public static Map<String, FontUIResource> changeUIFontSizes(Map<String, FontUIResource> oldResources,
                                                                      int increment) {
        Map<String, FontUIResource> result = oldResources.entrySet().stream().map(entry -> {
            var key = entry.getKey();
            var ofr = entry.getValue();
            var size = Math.max(1, ofr.getSize() + increment);
            var nfr = new FontUIResource(ofr.getFontName(), ofr.getStyle(), size);
            entry.setValue(nfr);
            return entry;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return result;
    }

    public static Map<String, FontUIResource> getUIFonts() {
        UIDefaults defaults = UIManager.getDefaults();
        HashMap<String, FontUIResource> result = new HashMap<>();
        Enumeration<?> enumm = defaults.keys();

        while (enumm.hasMoreElements()) {
            String key = enumm.nextElement().toString();
            if (key.contains("font")) {
                FontUIResource value = (FontUIResource) defaults.get(key);
                result.put(key, value);
            }
        }

        return result;
    }

    /**
     * Very vague heuristics for getting minimum font sizes.
     * Seems to work for my screens.
     *
     * @return
     */
    public static int getMinFontSizeForScreen() {

        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();

        var width = screenSize.getWidth();
        var minSize = 10;
        if (width > 2000) {
            minSize = 20;
        } else if (width > 1500) {
            minSize = 18;
        } else if (width > 1000) {
            minSize = 14;
        }

//	    System.out.println("screesize: $screenSize, resolution = $screenResolution, minSize = $minSize");
        return minSize;
    }
}