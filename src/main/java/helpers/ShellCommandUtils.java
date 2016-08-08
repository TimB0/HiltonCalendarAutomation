package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommandUtils {

    public static boolean isKeyboardDisplayed() {
        String checkKeyboardCommand = "dumpsys input_method | grep mInputShown";
        try {
            Process process = Runtime.getRuntime().exec(checkKeyboardCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();

            return output.toString().contains("mInputShown=true");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    public static void startWifiSettingsApp() {
        String startWifiSettingsCommand = "system/bin/am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings";
        executeShellCommand(startWifiSettingsCommand);
    }

    public static void startSettingsApp() {
        String startSettingsCommand = "system/bin/am start com.android.settings/.Settings";
        executeShellCommand(startSettingsCommand);
    }

    public static void clearHHonors() {
        String stopCommand = "system/bin/pm clear "+Utilities.PACKAGE;
        executeShellCommand(stopCommand, true);
    }

    public static void launchHHonors() {
        String launchCommand = "/system/bin/am start -a android.intent.action.MAIN -n "+Utilities.PACKAGE+"/com.mofo.android.hilton.core.activity.BootActivity";
        executeShellCommand(launchCommand, true);
    }


    public static boolean executeShellCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();

            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String executeShellCommand(String command, boolean returnString) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();

            if (returnString) {
                return output.toString().trim();
            } else {
                return "String is Empty";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
