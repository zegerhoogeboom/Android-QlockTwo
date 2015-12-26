package org.zegerhoogeboom;

import java.util.List;

/**
 * @author Zeger Hoogeboom
 */
public interface Renderer {
    List<String> getLetters();
    String getLetter(int position);
    boolean shouldShow(int position);
}
