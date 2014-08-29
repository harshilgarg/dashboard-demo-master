package com.vaadin.demo.dashboard.views;

//Import statements.
import bethpage.ws.authentication.Authentication;
import com.vaadin.demo.dashboard.HelpManager;
import com.vaadin.demo.dashboard.HelpOverlay;
import com.vaadin.demo.dashboard.LoginImageProvider;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends CssLayout implements View {
    
    //Handles the appearance of the help overlay window on the bottom of the Login screen.
    private HelpManager helpManager;
    
    //
    private VerticalLayout loginLayout;
    
    public Label backgroundImageContainer;
    
    public LoginView() {
        buildLogin();
    }
    
    private void buildLogin() {
        //HelpManager to control help overlay windows.
        helpManager = new HelpManager(UI.getCurrent());
        //Full screen unscrollable view component.
        setSizeFull();
        //Sets title of page. Evident in browser tab.
        Page.getCurrent().setTitle("Login to TutorMatch");
        
        int phe = Page.getCurrent().getBrowserWindowHeight();
        double logTrans = (155*phe/444)-(27835/148);
        
        String loginPanelPosition = ".login-layout {position:relative;z-index:2;transform: translate(0px, "
                + logTrans
                + "px);-webkit-transform: translate(0,"
                + logTrans
                + "px); }";
        Page.getCurrent().getStyles().add(loginPanelPosition);
        
        //Login image pipeline
        String imageHTML = LoginImageProvider.getHTML();
        backgroundImageContainer = new Label(imageHTML, ContentMode.HTML);
        backgroundImageContainer.addStyleName("login-bg");
        
        this.addComponent(backgroundImageContainer);

        buildComponents(false);
    }
    
    public void buildComponents(boolean exit) {
        if (exit) {
            this.removeAllComponents();
        }
        helpManager.closeAll();
        HelpOverlay w = helpManager
                .addOverlay(
                        "Welcome to the Bethpage High School TutorMatch application",
                        "<p>This application is designed as a resource to ask questions in academic subjects, answered only by registered BHS tutors.</p>"
                        + "<p>Please login with the school email account and password to either ask or answer questions. Contact administration for help.</p>",
                        "login");
        w.center();
        UI.getCurrent().addWindow(w);
        
        UI.getCurrent().addStyleName("login");
        
        loginLayout = new VerticalLayout();
        //loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");
        
        VerticalLayout k21 = new VerticalLayout();
        int pageWidth = UI.getCurrent().getPage().getBrowserWindowWidth();
        int pageHeight = UI.getCurrent().getPage().getBrowserWindowHeight();

        double transX = (162*pageWidth/319) - (49774/319);
        double transY = (155*pageHeight/444) + (27665/148);
        String p = "<div style=\"-webkit-transform:translate(" + transX +"px,"+ transY +"px);"
                + "transform:translate(" + transX +"px,"+ transY +"px);color: rgb(255,255,255);\n" +
"            opacity: 1;\n" +
"            font-weight: 200;\n" +
"            font-size:16px;\n" +
"            font-style:normal;font-family:'Source Sans Pro';\n" +
"            position:fixed !important;"
                + "\">Sign in using <span style=\"font-weight:400;\">Gmail</span></div>";
        String p2 = "<div>Sign in using <b>Gmail</b></div>";
        Label infoPrompt = new Label(p, ContentMode.HTML);
        
        double forgotX = pageWidth*0.5;
        double forgotY = (155*pageHeight/444) + (27665/148);
        String q = "<div style=\"-webkit-transform:translate(" + forgotX +"px,"+ forgotY +"px);"
                + "transform:translate(" + forgotX +"px,"+ forgotY +"px);color: rgb(255,255,255);\n" +
"            opacity: 1;\n" +
"            font-weight: 200;\n" +
"            font-size:16px;color:rgb(207, 207, 207);\n" +
"            font-style:normal;font-family:'Source Sans Pro';\n" +
"            "
                + "\">Forgot your password?</div>";
        String q2 = "<div>Forogt your password?</div>";
        Label forgotPrompt = new Label(q, ContentMode.HTML);
        //forgotPrompt.addStyleName("forgotPrompt");
        
        k21.addComponent(infoPrompt);
        k21.setComponentAlignment(infoPrompt, Alignment.TOP_CENTER);
        
        this.addComponent(k21);
        
       // VerticalLayout k22 = new VerticalLayout();
        k21.addComponent(forgotPrompt);
        //k22.setComponentAlignment(forgotPrompt, Alignment.TOP_CENTER);
        
       // this.addComponent(k22);
        
        this.addComponent(loginLayout);
        Link link = new Link("Advantegee", new ExternalResource("http://www.vaadin.com"));
        link.addStyleName("badboo");
        
        double uu = (317*pageWidth/680) + (1053/17);
        double vv = 350;
        
        /*Page.getCurrent().getStyles().add(".v-link.badboo {font-family:'Source Sans Pro';font-weight:200;font-size: 16px;color: rgb(207, 207, 207);transform: translate("
                + uu + "px," + vv + "px);-webkit-transform:translate("+ uu + "px,"+ vv + "px);}");*/
        
        loginLayout.addComponent(link);
        
        final CssLayout loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");
        
        final VerticalLayout loginPanelVerticalLayout = new VerticalLayout();
        
        //username horizontal layout
        final HorizontalLayout usernameFieldHorizontalLayout = new HorizontalLayout();
        usernameFieldHorizontalLayout.setSpacing(true);
        usernameFieldHorizontalLayout.setMargin(true);
        usernameFieldHorizontalLayout.addStyleName("fields");
        
        //password horizontal layout
        
        final HorizontalLayout passwordFieldHorizontalLayout = new HorizontalLayout();
        passwordFieldHorizontalLayout.setSpacing(true);
        passwordFieldHorizontalLayout.setMargin(true);
        passwordFieldHorizontalLayout.addStyleName("fields2");
        
        //label creations
        final Label usernamePrompt = new Label(pageWidth+"");
        final Label passwordPrompt = new Label(pageHeight+"");
        
        //start with labels translated into the box
        usernamePrompt.addStyleName("headlabel");
        passwordPrompt.addStyleName("headlabel2");

        //horizontal layouts for each of the labels
        final HorizontalLayout usernamePromptHorizontalLayout
                = new HorizontalLayout();
        usernamePromptHorizontalLayout.addComponent(usernamePrompt);
        
        final HorizontalLayout passwordPromptHorizontalLayout
                = new HorizontalLayout();
        passwordPromptHorizontalLayout.addComponent(passwordPrompt);
        
                //create text fields
        final TextField username = new TextField("");
        username.addStyleName("username");
        
        final PasswordField password = new PasswordField("");
        password.addStyleName("password");

        username.addFocusListener(new FieldEvents.FocusListener() {

            @Override
            public void focus(FieldEvents.FocusEvent event) {
                usernamePrompt.addStyleName("darkening");
            }
        });
        
         password.addFocusListener(new FieldEvents.FocusListener() {

            @Override
            public void focus(FieldEvents.FocusEvent event) {
                passwordPrompt.addStyleName("darkening");
            }
        });

        username.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                /*If the textfield for username input is empty, make the label animate 
                 up. This will return true if the user starts typing, since the old 
                 field value is returned during the text change event trigger. */
                if (username.getValue().isEmpty()) {
                    usernamePrompt.removeStyleName("animationdown");
                    usernamePrompt.addStyleName("animationup");
                }

                /*If the textfield for username is currently empty, after a text 
                 change event has been triggered, it must mean that the user is 
                 erasing the field. Thus, make the label animate down. */
                if (event.getText().isEmpty()) {
                    usernamePrompt.addStyleName("animationdown");
                    usernamePrompt.removeStyleName("animationup");
                    username.setValue("");
                }
            }
        });
        
        password.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                /*If the textfield for username input is empty, make the label animate 
                 up. This will return true if the user starts typing, since the old 
                 field value is returned during the text change event trigger. */
                if (password.getValue().isEmpty()) {
                    passwordPrompt.removeStyleName("animationdown2");
                    passwordPrompt.addStyleName("animationup2");
                }

                /*If the textfield for username is currently empty, after a text 
                 change event has been triggered, it must mean that the user is 
                 erasing the field. Thus, make the label animate down. */
                if (event.getText().isEmpty()) {
                    passwordPrompt.addStyleName("animationdown2");
                    passwordPrompt.removeStyleName("animationup2");
                    password.setValue("");
                }
            }
        });

        username.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        password.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        
        username.addBlurListener(new FieldEvents.BlurListener() {

            @Override
            public void blur(FieldEvents.BlurEvent event) {
                if (!username.getValue().isEmpty()) {
                    usernamePrompt.removeStyleName("darkening");
                    usernamePrompt.removeStyleName("animationdown");
                    //usernamePrompt.removeStyleName("animationup");
                    usernamePrompt.addStyleName("fading");
                }
            }
        });
        
        password.addBlurListener(new FieldEvents.BlurListener() {

            @Override
            public void blur(FieldEvents.BlurEvent event) {
                if (!password.getValue().isEmpty()) {
                    passwordPrompt.removeStyleName("darkening");
                    passwordPrompt.removeStyleName("animationdown2");
                    passwordPrompt.addStyleName("fading");
                }
                
            }
        });
        usernameFieldHorizontalLayout.addComponent(username);
        passwordFieldHorizontalLayout.addComponent(password);
        
        final HorizontalLayout fields3 = new HorizontalLayout();
        fields3.setSpacing(true);
        fields3.setMargin(true);
        fields3.addStyleName("fields");

        final Button signin = new Button("Sign in");
       
        signin.addStyleName("default");
        fields3.addComponent(signin);
        fields3.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("Sign In",
                ShortcutAction.KeyCode.ENTER, null) {
                    @Override
                    public void handleAction(Object sender, Object target) {
                        signin.click();
                    }
                };
        
        final HorizontalLayout errorHorizontalLayout = new HorizontalLayout();
     
        signin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                boolean auth = Authentication.authenticate(username.getValue(), password.getValue());
                boolean isUser = username.getValue().endsWith("@bethpageeagles.ws");
                if (isUser && auth) {
                    //Successful login.
                }
                else if((!isUser && auth) || (!isUser && !auth)) {
                    if (errorHorizontalLayout.getComponentCount() == 1) {
                        // Remove the previous error message
                        errorHorizontalLayout.removeComponent(errorHorizontalLayout.getComponent(0));
                    }
                    Label error = new Label(
                            "Please sign in with Bethpage Eagles account.",
                            ContentMode.HTML);
                    error.addStyleName("error");
                    error.setSizeUndefined();
                    error.addStyleName("light");
                    // Add animation
                    error.addStyleName("v-animate-reveal");
                    errorHorizontalLayout.addComponent(error);
                   
                }
                else if(isUser && !auth) {
                     if (errorHorizontalLayout.getComponentCount() == 1) {
                        // Remove the previous error message
                        errorHorizontalLayout.removeComponent(errorHorizontalLayout.getComponent(0));
                    }
                    Label error = new Label(
                            "Authentication failed. Please try again.",
                            ContentMode.HTML);
                    error.addStyleName("error");
                    error.setSizeUndefined();
                    error.addStyleName("light");
                    // Add animation
                    error.addStyleName("v-animate-reveal");
                    errorHorizontalLayout.addComponent(error);
                }
            }
        });

        signin.addShortcutListener(enter);
        HorizontalLayout welcomeLabel = new HorizontalLayout();
        Label m = new Label("Login to");
        Label n = new Label("TutorMatch");
        m.addStyleName("loginlabel");
        n.addStyleName("tutormatch");
        welcomeLabel.addComponent(m);
        welcomeLabel.addComponent(n);
        
        HorizontalLayout line = new HorizontalLayout();
        Label o = new Label("This text will never be aaaaaaaaaaaaa");
        o.addStyleName("linelabel");
        line.addComponent(o);
        
        loginPanelVerticalLayout.addComponent(welcomeLabel);
        loginPanelVerticalLayout.setComponentAlignment(welcomeLabel, Alignment.TOP_CENTER);
        
         loginPanelVerticalLayout.addComponent(line);
        loginPanelVerticalLayout.setComponentAlignment(line, Alignment.TOP_CENTER);
        
        loginPanelVerticalLayout.addComponent(usernamePromptHorizontalLayout);
        loginPanelVerticalLayout.setComponentAlignment(
                usernamePromptHorizontalLayout, Alignment.TOP_CENTER);
        
        
        loginPanelVerticalLayout.addComponent(usernameFieldHorizontalLayout);
        loginPanelVerticalLayout.addComponent(passwordPromptHorizontalLayout);
        loginPanelVerticalLayout.setComponentAlignment(
               passwordPromptHorizontalLayout, Alignment.MIDDLE_CENTER);
        loginPanelVerticalLayout.addComponent(passwordFieldHorizontalLayout);
        loginPanelVerticalLayout.addComponent(fields3);
        loginPanelVerticalLayout.setComponentAlignment(
                usernameFieldHorizontalLayout, Alignment.TOP_CENTER);
        loginPanelVerticalLayout.setComponentAlignment(passwordFieldHorizontalLayout,
                Alignment.MIDDLE_CENTER);
        loginPanelVerticalLayout.setComponentAlignment(fields3,
                Alignment.MIDDLE_CENTER);
        loginPanelVerticalLayout.addComponent(errorHorizontalLayout);
        loginPanelVerticalLayout.setComponentAlignment(errorHorizontalLayout,
                Alignment.MIDDLE_CENTER);
        
        //Label x22 = new Label("yolo girl");
        //loginPanelVerticalLayout.addComponent(x22);
        
        loginPanel.addComponent(loginPanelVerticalLayout);
        

        loginLayout.addComponent(loginPanel);
        
        //infoPrompt.addStyleName("prompt");
        //this.setComponentAlignment(infoPrompt, Alignment.MIDDLE_CENTER);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    
}
