package com.lak.autolayout.attrs;

/**
 * Created by lawrence on 2018/6/7.
 */

public final class Attrs {
    private Attrs() {
    }

    public static final int WIDTH = 1;
    public static final int HEIGHT = WIDTH << 1;

    public static final int MAX_WIDTH = HEIGHT << 1;
    public static final int MAX_HEIGHT = MAX_WIDTH << 1;

    public static final int MIN_WIDTH = MAX_HEIGHT << 1;
    public static final int MIN_HEIGHT = MIN_WIDTH << 1;

    public static final int MARGIN = MIN_HEIGHT << 1;
    public static final int MARGIN_LEFT = MARGIN << 1;
    public static final int MARGIN_TOP = MARGIN_LEFT << 1;
    public static final int MARGIN_RIGHT = MARGIN_TOP << 1;
    public static final int MARGIN_BOTTOM = MARGIN_RIGHT << 1;

    public static final int PADDING = MARGIN_BOTTOM << 1;
    public static final int PADDING_LEFT = PADDING << 1;
    public static final int PADDING_TOP = PADDING_LEFT << 1;
    public static final int PADDING_RIGHT = PADDING_TOP << 1;
    public static final int PADDING_BOTTOM = PADDING_RIGHT << 1;

    public static final int TEXT_SIZE = PADDING_BOTTOM << 1;
}
