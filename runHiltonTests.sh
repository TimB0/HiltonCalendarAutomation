#!/bin/sh


# Clean the Project
gradle clean

# Build the Automation Project
gradle build


# Clear the Screenshots From the Phone
adb shell rm /sdcard/TestCase02*.png


# Push the Automation Jar to the Device
adb devices | sed "1 d" | cut -f 1 | xargs -I serial adb  -s serial push ./dist/HiltonCalendarAutomation.jar /data/local/tmp

# Start the Automation
adb shell uiautomator runtest HiltonCalendarAutomation.jar -c tests.TestCase02

# Copy Screenshots to Test Machine
adb pull /sdcard/uiautomator-screenshots $HOME/Public/Automation/Screenshots




# Supposedly this isnt needed for API 18 or higher, so Im trying it without it.
# -c com.mofo.hilton.android.uiautomator.tests.TestCase25_Scenario04


# adb  devices | sed "1 d" | cut -f 1 | xargs -I serial adb  -s serial push /Users/tboland/Dropbox/MoFo/hilton-android-automation-lock/HiltonAutomationLock/dist/HiltonAutomationLock.jar /data/local/tmp