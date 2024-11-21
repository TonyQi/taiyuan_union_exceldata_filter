package indi.nonoas.crm;

import indi.jfxmaker.AbstractApp;
import indi.nonoas.crm.component.CustomSplash;
import indi.nonoas.crm.view.WelcomeView;

/**
 * @author QIPF
 * @datetime 2024-10-18 16:10:11
 */
public class Starter {
    public static void main(String[] args) {
        AbstractApp.launch(App.class, WelcomeView.class, new CustomSplash(), args);
    }
}
