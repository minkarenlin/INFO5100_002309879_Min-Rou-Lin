package com.example;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 * A base class for all views in the application.
 * All views extending this class will inherit common layout settings (spacing and padding).
 */

public abstract class BaseView extends VBox {
    public BaseView() {
        super(20);             // spacing = 10
        setPadding(new Insets(15));  // Set padding of 15
        initView();           
    }

    // Subclasses must implement this method to add their own UI components (e.g., buttons, images, etc.).
    protected abstract void initView();
}
