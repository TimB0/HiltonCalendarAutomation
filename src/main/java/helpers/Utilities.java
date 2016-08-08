package helpers;

import android.os.Build;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


/**
 * Created by tboland on 12/11/14.
 * Functions
 */
public class Utilities extends UiAutomatorTestCase {

    public static final String PACKAGE = "com.hilton.android.hhonors";

    public static final Integer ANDROID_M_VERSION = Build.VERSION_CODES.M;

    public static UiObject signInField = new UiObject(new UiSelector()
            .resourceId(Utilities.PACKAGE + ":id/username_input"));

    public static UiAutomatorTestCase tc = new UiAutomatorTestCase();

    public static int i;

    public static UiObject rateAppClose = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/rate_app_close"));

    public static UiDevice uiDevice = UiDevice.getInstance();





    public static void validateImportantInformation() throws UiObjectNotFoundException {

        final UiObject navigateUpBtn = new UiObject(new UiSelector()
                .description("Navigate up"));

        UiObject openFabBtn = new UiObject(new UiSelector()
                .resourceId(Utilities.PACKAGE + ":id/fab"));


        UiWatcher watcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject toolBar = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/toolbar"));
                try {
                    if (toolBar.exists()) {
                        UiObject screenTitle = toolBar.getChild(new UiSelector().className("android.widget.TextView"));
                        String screenTitleText = screenTitle.getText();
                        if (screenTitleText.equalsIgnoreCase("Important Information")) {
                            navigateUpBtn.clickAndWaitForNewWindow();
                            return true;
                        }
                    }
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };

        UiWatcher signInWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject signInError = new UiObject(new UiSelector().textContains("Oops! You don't seem to have entered enough characters."));
                try {
                    if (signInError.exists()) {
                        System.out.println("Sign In Error");
                        UiObject okBtn = new UiObject(new UiSelector().className("android.widget.Button").textMatches("OK|Ok"));
                        okBtn.click();

                        signInField.click();
                        signInField.clearTextField();

                    }
                } catch (UiObjectNotFoundException e){
                    e.printStackTrace();
                }
                return false;
            }
        };


        UiWatcher hotelUnavailableWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject hotelUnavailableMessage = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/rl_errors_text").text("Call Hotel for Availability"));
                if (hotelUnavailableMessage.exists()) {
                    assertTrue("Call Hotel for Availability Message is displaying", false);
                }
                return false;
            }
        };

        UiWatcher rateAppCloseWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                try {
                    if (rateAppClose.exists()) {
                        rateAppClose.clickAndWaitForNewWindow();
                    }
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };

        UiWatcher attentionAlertWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject attentionAlertTitle = new UiObject(new UiSelector().resourceId("android:id/alertTitle")
                        .textContains("Attention"));
                try {
                    if (attentionAlertTitle.exists()) {
                        System.out.println("Attention Alert");
                        UiObject okBtn = new UiObject(new UiSelector().resourceId("android:id/button1").className("android.widget.Button").textMatches("OK|Ok"));
                        okBtn.click();
                    }
                } catch (UiObjectNotFoundException e){
                    e.printStackTrace();
                }
                return false;
            }
        };

        UiWatcher fingerprintWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject accessFingerprintText = new UiObject(new UiSelector()
                        .text("Access at the tip of your finger"));

                UiObject snackBarMessage = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/snackbar_text"));
                try {
                    if (accessFingerprintText.exists()) {
                        System.out.println("FingerPrint Request Displayed");
                        UiObject noThanksBtn = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/button_no_thanks"));
                        noThanksBtn.click();

                        i = 0;
                        while (snackBarMessage.exists()) {
                            tc.sleep(1000);
                            i++;
                            if (i == 60) {
                                assertTrue("Snack Bar has taken longer than 60 seconds to close", false);
                            }
                        }
                    }
                } catch (UiObjectNotFoundException e){
                    e.printStackTrace();
                }
                return false;
            }
        };

        UiWatcher scanForLocksWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject scanForLocksBtn = new UiObject(new UiSelector()
                        .resourceId(Utilities.PACKAGE + ":id/tv_tap_area_text").textContains("Scan for Locks"));
                try {
                    if (scanForLocksBtn.exists()) {
                        System.out.println("Scan For Locks Displayed");
                        scanForLocksBtn.clickAndWaitForNewWindow();
                    }
                } catch (UiObjectNotFoundException e){
                    e.printStackTrace();
                }
                return false;
            }
        };

        UiWatcher dumpingMemoryWatcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject dumpingMemoryText = new UiObject(new UiSelector()
                        .textContains("Dumping memory"));
                i = 0;
                    while (dumpingMemoryText.exists()) {
                        System.out.println("Dumping Memory Displayed");
                        tc.sleep(1000);
                        i++;
                        if (i == 60) {
                            assertTrue("Dumping Memory displayed for longer than ", false);
                        }
                    }
                return false;
            }
        };

        UiDevice.getInstance().registerWatcher("Dumping Memory Watcher", dumpingMemoryWatcher);
        UiDevice.getInstance().registerWatcher("Scan For Locks Watcher", scanForLocksWatcher);
        UiDevice.getInstance().registerWatcher("FingerPrint Watcher", fingerprintWatcher);
        UiDevice.getInstance().registerWatcher("Hotel Unavailable", hotelUnavailableWatcher);
        UiDevice.getInstance().registerWatcher("Important Information", watcher);
        UiDevice.getInstance().registerWatcher("Sign In Error", signInWatcher);
        UiDevice.getInstance().registerWatcher("Rate App Popup", rateAppCloseWatcher);
        UiDevice.getInstance().registerWatcher("Attention Alert", attentionAlertWatcher);
        UiDevice.getInstance().runWatchers();

        int z = 0;
        while (!openFabBtn.exists()) {
            tc.sleep(1000);
            System.out.println("Waiting " + z + " seconds for HHonors Splash Page");
            z++;
            if (z == 60) {
                System.out.println("Splash Page has taken longer than 60 seconds....This test may fail on the next step");
                break;
            }
        }
    }
}
