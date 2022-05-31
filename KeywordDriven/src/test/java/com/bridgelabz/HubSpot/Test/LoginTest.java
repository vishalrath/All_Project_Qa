package com.bridgelabz.HubSpot.Test;

import com.bridgelabz.keyword.engine.KeywordEngine;
import org.testng.annotations.Test;

public class LoginTest {
    // create object of KeywordEngine class becouse call the startExecution method
    public KeywordEngine keywordEngine;

    // here we dont nedd to add @beforand after method
    // directly start @ anotetions

    @Test
    public void loginTest() {
        keywordEngine = new KeywordEngine();
        keywordEngine.startExecution("signup");

    }
//    @Test
//    public void signupTest() {
//        keywordEngine = new KeywordEngine();
//        keywordEngine.startExecution("signup");
//
//    }
}
