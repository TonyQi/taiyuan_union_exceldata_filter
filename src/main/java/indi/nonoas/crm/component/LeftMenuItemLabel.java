package indi.nonoas.crm.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class LeftMenuItemLabel extends Label {

    /**
     * 左侧按钮高度
     */
    public static final double LEFT_MENUITEM_SIZE = 30.0;

    /**
     * 最大尺寸数值
     */
    public static final double MAX_VALUE = Double.MAX_VALUE;

    public LeftMenuItemLabel(String text) {
        super(text);
        this.setPrefHeight(LEFT_MENUITEM_SIZE);
        this.setMaxWidth(MAX_VALUE);
        this.setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(0, 0, 0, 20));
    }
}
