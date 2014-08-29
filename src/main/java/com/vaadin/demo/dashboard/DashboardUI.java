package com.vaadin.demo.dashboard;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.demo.dashboard.views.LoginView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import java.util.Locale;

@Theme("dashboard")
@Title("TutorMatch")
public class DashboardUI extends UI {

    //Navigator to control various views in the application.
    private Navigator nav;

    @Override
    protected void init(VaadinRequest request) {
        //Set locale of application to United States
        setLocale(Locale.US);

        nav = new Navigator(UI.getCurrent(), UI.getCurrent());
        
        final LoginView lv = new LoginView();
        nav.addView("",lv);
    }
    
}
