package tests;

import android.os.Build;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import junit.framework.AssertionFailedError;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import helpers.ShellCommandUtils;
import helpers.Utilities;

/**
 * Created by Tim Boland on 10/10/14
 * HHANDTC-110:AutoTC - TC-2 - Find a hotel

 TC Covered:
 HHANDTC-36
 HHANDTC-133
 HHANDTC-15
 HHANDTC-16
 HHANDTC-18

 This was updated in Test Link on 2/2/16
 */


public class TestCase02 extends UiAutomatorTestCase {

    private String mSearchResultDepartureDate;
    public Date tenDaysFromNowDt;
    public UiObject arrivalDateSelected;
    public UiObject departureDateSelected;
    public String day364DaysFromNow;
    private String monthAbbrCAPS365DaysFromNowPlusOneMonth;
    private int correctDayOfWeekOrdinal364DaysFromNow;
    private String day365DaysFromNow;
    private String day366DaysFromNow;
    private int correctDayOfWeekOrdinal365DaysFromNow;
    private int dayOfWeekOrdinal365DaysFromNow;
    private int dayOfWeekOrdinal366DaysFromNow;
    private String monthAbbrCAPSTenDaysFromNowPlusOneMonth;
    private int correctDayOfWeekOrdinal366DaysFromNow;
    private String dayFiveDaysFromNow;
    private String monthAbbrCAPSFiveDaysFromNowPlusOneMonth;
    private String dayFourDaysFromNow;
    private String daySixDaysFromNow;
    private String dayTenDaysFromNow;
    private String dayTommorow;
    private String dayCurrent;


    @Override
    public void setUp() throws UiObjectNotFoundException {

        getUiDevice().pressHome();

        // Clear The App Data
        ShellCommandUtils.clearHHonors();

        // Start The App
        ShellCommandUtils.launchHHonors();

        // Verify you are in the Correct App
        getUiDevice().waitForWindowUpdate(Utilities.PACKAGE + "", 15000);

        // Validate Splash Screen
        UiObject splashPageText = new UiObject(new UiSelector()
                .text("Splash screen..."));
        splashPageText.exists();

        // Get Current Date - // Ex.  10/9/2014 or 10/19/2014
        DateFormat dateFormat = new SimpleDateFormat("MM/d/yyyy");
        String currentDateString = dateFormat.format(Calendar.getInstance().getTime());
        System.out.println("The Current Date is " + currentDateString);

        // Get Current Month
        DateFormat currentMonthFormat = new SimpleDateFormat("MMM");
        Date currentDate = new Date();
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);
        currentDate = currentCalendar.getTime();
        String currentMonthCAPS = currentMonthFormat.format(currentDate).toUpperCase();
        System.out.println("The current month is: " + currentMonthCAPS);

        // Get Tommorows Date
        Date tommorowsDt = new Date();
        currentCalendar.setTime(tommorowsDt);
        currentCalendar.add(Calendar.DATE, 1);
        tommorowsDt = currentCalendar.getTime();
        String dateTommorow = dateFormat.format(tommorowsDt);
        System.out.println("Tommorows Date is: " + dateTommorow);

        // Get Date 5 Days from now
        DateFormat futureDateFormat = new SimpleDateFormat("MM/d/yyyy");
        Date fiveDaysFromNowDt = new Date();
        Calendar fiveDayCalendar = Calendar.getInstance();
        fiveDayCalendar.setTime(fiveDaysFromNowDt);
        fiveDayCalendar.add(Calendar.DATE, 5);   // 25
        fiveDaysFromNowDt = fiveDayCalendar.getTime();
        String dateFiveDaysFromNow = futureDateFormat.format(fiveDaysFromNowDt);
        System.out.println("The date 5 days from now is " + dateFiveDaysFromNow);

        // Get Date 5 days from now plus One Month
        fiveDayCalendar.add(Calendar.MONTH, 1);
        Date fiveDaysFromNowPlusOneMonthDt = fiveDayCalendar.getTime();
        String dateFiveDaysFromNowPlusOneMonth = futureDateFormat.format(fiveDaysFromNowPlusOneMonthDt);
        System.out.println("The date 10 days from now is " + dateFiveDaysFromNowPlusOneMonth);

        // Get Date 6 Days From Now
        Date sixDaysFromNowDt = new Date();
        Calendar sixDayCalendar = Calendar.getInstance();
        sixDayCalendar.setTime(sixDaysFromNowDt);
        sixDayCalendar.add(Calendar.DATE, 6);   // 25
        sixDaysFromNowDt = sixDayCalendar.getTime();
        String dateSixDaysFromNow = futureDateFormat.format(sixDaysFromNowDt);
        System.out.println("The date 6 days from now is " + dateSixDaysFromNow);

        // Get Date 4 Days From
        Date fourDaysFromNowDt = new Date();
        Calendar fourDayCalendar = Calendar.getInstance();
        fourDayCalendar.setTime(fourDaysFromNowDt);
        fourDayCalendar.add(Calendar.DATE, 4);   // 25
        fourDaysFromNowDt = fourDayCalendar.getTime();
        String dateFourDaysFromNow = futureDateFormat.format(fourDaysFromNowDt);
        System.out.println("The date 4 days from now is " + dateFourDaysFromNow);


        // Get Date 10 days from now
        Calendar tenDayCalendar = Calendar.getInstance();
        tenDayCalendar.add(Calendar.DATE, 10);   // 25
        tenDaysFromNowDt = tenDayCalendar.getTime();
        String dateTenDaysFromNow = futureDateFormat.format(tenDaysFromNowDt);
        System.out.println("The date 10 days from now is " + dateTenDaysFromNow);

        // Get Date 10 days from now plus One Month
        tenDayCalendar.add(Calendar.MONTH, 1);
        Date tenDaysFromNowPlusOneMonthDt = tenDayCalendar.getTime();
        String dateTenDaysFromNowPlusOneMonth = futureDateFormat.format(tenDaysFromNowPlusOneMonthDt);
        System.out.println("The date 10 days from now is " + dateTenDaysFromNowPlusOneMonth);

        // Get Date 364 days from now
        Calendar threeHundredSixtyFourCalendar = Calendar.getInstance();
        threeHundredSixtyFourCalendar.add(Calendar.DATE, 364);
        Date threeHundredSixtyFourDaysFromNowDt = threeHundredSixtyFourCalendar.getTime();
        String threeHundredSixtyFourDaysFromNow = futureDateFormat.format(threeHundredSixtyFourDaysFromNowDt);
        System.out.println("The date 364 days from now is " + threeHundredSixtyFourDaysFromNow);

        // Get Date 365 days from now
        threeHundredSixtyFourCalendar.add(Calendar.DATE, 1);
        Date threeHundredSixtyFiveDaysFromNowDt = threeHundredSixtyFourCalendar.getTime();
        String threeHundredSixtyFiveDaysFromNow = futureDateFormat.format(threeHundredSixtyFiveDaysFromNowDt);
        System.out.println("The date 365 days from now is " + threeHundredSixtyFiveDaysFromNow);

        // Get Date 365 days from now plus One Month
        Calendar threeHundredSixtyFiveCalendar = Calendar.getInstance();
        threeHundredSixtyFiveCalendar.add(Calendar.MONTH, 1);
        Date threeHundredSixtyFiveDaysFromNowPlusOneMonthDt = threeHundredSixtyFiveCalendar.getTime();
        String threeHundredSixtyFiveDaysFromNowPlusOneMonth = futureDateFormat.format(threeHundredSixtyFiveDaysFromNowPlusOneMonthDt);
        System.out.println("The date 365 days from now Plus One Month is " + threeHundredSixtyFiveDaysFromNowPlusOneMonth);


        // Get Date 366 days from now
        threeHundredSixtyFourCalendar.add(Calendar.DATE, 1);
        Date threeHundredSixtySixDaysFromNowDt = threeHundredSixtyFourCalendar.getTime();
        String threeHundredSixtySixDaysFromNow = futureDateFormat.format(threeHundredSixtySixDaysFromNowDt);
        System.out.println("The date 366 days from now is " + threeHundredSixtySixDaysFromNow);

        // Get Date 367 days from now
        threeHundredSixtyFourCalendar.add(Calendar.DATE, 1);
        Date threeHundredSixtySevenDaysFromNowDt = threeHundredSixtyFourCalendar.getTime();
        String threeHundredSixtySevenDaysFromNow = futureDateFormat.format(threeHundredSixtySevenDaysFromNowDt);
        System.out.println("The date 367 days from now is " + threeHundredSixtySevenDaysFromNow);

        // Get Date 364 + 90 days from now
        Calendar threeHundredSixtyFourPlusNinetyCalendar = Calendar.getInstance();
        threeHundredSixtyFourPlusNinetyCalendar.add(Calendar.DATE, 364+90);
        Date threeHundredSixtyFourPlusNinetyDaysFromNowDt = threeHundredSixtyFourPlusNinetyCalendar.getTime();
        String threeHundredSixtyFourPlusNinetyDaysFromNow = futureDateFormat.format(threeHundredSixtyFourPlusNinetyDaysFromNowDt);
        System.out.println("The date 364 plus 90 days from now is " + threeHundredSixtyFourPlusNinetyDaysFromNow);


        // Get Year 10 Days From Now // Ex. 2014
        DateFormat tenDaysFromNowYear = new SimpleDateFormat("yyyy");
        String futureYear = tenDaysFromNowYear.format(tenDaysFromNowDt);
        System.out.println("The Year 10 Days From Now is " + futureYear);

        //DateFormat nextYear = tenDaysFromNowYear + 1;
        //System.out.println("Next year is " + );

        // Get Month abbreviation of 10 Days from now // Ex. NOV
        DateFormat futureMonthAbbr = new SimpleDateFormat("MMM");
        String monthAbbrTenDaysFromNow = futureMonthAbbr.format(tenDaysFromNowDt);
        String monthAbbrCAPSTenDaysFromNow = monthAbbrTenDaysFromNow.toUpperCase();
        System.out.println("The Month Abbr 10 days from now is " + monthAbbrTenDaysFromNow);
        System.out.println("The Month Abbr 10 days from now in CAPS is " + monthAbbrCAPSTenDaysFromNow);

        // Get Month abbreviation of 10 days from now Plus One Month
        String monthAbbrTenDaysFromNowPlusOneMonth = futureMonthAbbr.format(tenDaysFromNowPlusOneMonthDt);
        monthAbbrCAPSTenDaysFromNowPlusOneMonth = monthAbbrTenDaysFromNowPlusOneMonth.toUpperCase();
        System.out.println("The Month Abbr 10 days from now Plus One Month is " + monthAbbrTenDaysFromNowPlusOneMonth);
        System.out.println("The Month Abbr 10 days from now Plus One Month in CAPS is " + monthAbbrCAPSTenDaysFromNowPlusOneMonth);

        // Get Month abbreviation of 5 days from now Plus One Month
        String monthAbbrFiveDaysFromNowPlusOneMonth = futureMonthAbbr.format(fiveDaysFromNowPlusOneMonthDt);
        monthAbbrCAPSFiveDaysFromNowPlusOneMonth = monthAbbrFiveDaysFromNowPlusOneMonth.toUpperCase();
        System.out.println("The Month Abbr 5 days from now Plus One Month is " + monthAbbrFiveDaysFromNowPlusOneMonth);
        System.out.println("The Month Abbr 5 days from now Plus One Month in CAPS is " + monthAbbrCAPSFiveDaysFromNowPlusOneMonth);

        // Get Month abbr of 365 days from now Plus One Month
        String monthAbbr365DaysFromNowPlusOneMonth = futureMonthAbbr.format(threeHundredSixtyFiveDaysFromNowPlusOneMonthDt);
        monthAbbrCAPS365DaysFromNowPlusOneMonth = monthAbbr365DaysFromNowPlusOneMonth.toUpperCase();
        System.out.println("The Month Abbr 365 days from now Plus One Month is " + monthAbbr365DaysFromNowPlusOneMonth);
        System.out.println("The Month Abbr 365 days from now Plus One Month in CAPS is " + monthAbbrCAPS365DaysFromNowPlusOneMonth);

        // Get Day of Week abbreviation of 10 days from now //Ex.  WED
        DateFormat futureDayOfWeekAbbr = new SimpleDateFormat("EEE");
        String dayOfWeekAbbrTenDaysFromNow = futureDayOfWeekAbbr.format(tenDaysFromNowDt).toUpperCase();
        System.out.println("The Day Of Week Abbr 10 days from now is " + dayOfWeekAbbrTenDaysFromNow);

        // Get Day number of current Day
        DateFormat futureDayNumberFormat = new SimpleDateFormat("d");
        dayCurrent = futureDayNumberFormat.format(currentDate);
        System.out.println("The Current day is " + dayCurrent);

        // Get Day number of tommorow
        dayTommorow = futureDayNumberFormat.format(tommorowsDt);
        System.out.println("Tommorows Date is " + dayTommorow);

        // Get Day number of 5 days from now
        dayFiveDaysFromNow = futureDayNumberFormat.format(fiveDaysFromNowDt);
        System.out.println("The day 5 days from now is " + dayFiveDaysFromNow);

        // Get Day number of 4 days from now
        dayFourDaysFromNow = futureDayNumberFormat.format(fourDaysFromNowDt);
        System.out.println("The day 4 days from now is " + dayFourDaysFromNow);

        // Get Day number of 6 days from now
        daySixDaysFromNow = futureDayNumberFormat.format(sixDaysFromNowDt);
        System.out.println("The day 6 days from now is " + daySixDaysFromNow);

        // Get DAY number of 10 days from now
        // Ex.  25
        dayTenDaysFromNow = futureDayNumberFormat.format(tenDaysFromNowDt);
        System.out.println("The day 10 days from now is " + dayTenDaysFromNow);

        // Get Day Number of 364 Days from Now
        day364DaysFromNow = futureDayNumberFormat.format(threeHundredSixtyFourDaysFromNowDt);
        System.out.println("The day 364 days from now is " + day364DaysFromNow);

        // Get Day Number of 364 Days from Now
        day365DaysFromNow = futureDayNumberFormat.format(threeHundredSixtyFiveDaysFromNowDt);
        System.out.println("The day 365 days from now is " + day365DaysFromNow);

        // Get Day Of Week Abbr For 365 Days From Now
        String dayOfWeek365DaysFromNow = futureDayOfWeekAbbr.format(threeHundredSixtyFourDaysFromNowDt);
        System.out.println("The Day Of Week Abbr 365 days from now is " + dayOfWeek365DaysFromNow);

        // Get Ordinal for Day Of Week 364 Days From Now
        threeHundredSixtyFourCalendar.setTime(threeHundredSixtyFourDaysFromNowDt);
        int dayOfWeekOrdinal364DaysFromNow = threeHundredSixtyFourCalendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("The Ordinal for the Day Of Week 364 days from now is " + dayOfWeekOrdinal364DaysFromNow);
        correctDayOfWeekOrdinal364DaysFromNow = dayOfWeekOrdinal364DaysFromNow - 1;
        System.out.println("The Correct Ordinal for the Day of Week 364 days from now is " + correctDayOfWeekOrdinal364DaysFromNow);

        // Get Day Number of 366 Days from Now
        day366DaysFromNow = futureDayNumberFormat.format(threeHundredSixtySixDaysFromNowDt);
        System.out.println("The day 366 Days From Now is " + day366DaysFromNow);

        // Get Day Of Week Abbr For 364 Days From Now
        String dayOfWeek364DaysFromNow = futureDayOfWeekAbbr.format(threeHundredSixtyFourDaysFromNowDt);
        System.out.println("The Day Of Week Abbr 364 days from now is " + dayOfWeek364DaysFromNow);

        // Get Ordinal for Day Of Week 365 Days From Now
        threeHundredSixtyFourCalendar.setTime(threeHundredSixtyFiveDaysFromNowDt);
        dayOfWeekOrdinal365DaysFromNow = threeHundredSixtyFourCalendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("The Ordinal for the Day Of Week 365 days from now is " + dayOfWeekOrdinal365DaysFromNow);
        correctDayOfWeekOrdinal365DaysFromNow = dayOfWeekOrdinal365DaysFromNow - 1;
        System.out.println("The Correct Ordinal for the Day of Week 365 days from now is " + correctDayOfWeekOrdinal365DaysFromNow);

        // Get Ordinal for Day Of Week 366 Days From Now
        threeHundredSixtyFourCalendar.setTime(threeHundredSixtySixDaysFromNowDt);
        dayOfWeekOrdinal366DaysFromNow = threeHundredSixtyFourCalendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("The Ordinal for the Day of Week 366 days from now is " + dayOfWeekOrdinal366DaysFromNow);
        correctDayOfWeekOrdinal366DaysFromNow = dayOfWeekOrdinal366DaysFromNow - 1;
        System.out.println("The Correct Ordinal for the Day of Week 366 days from now is " + correctDayOfWeekOrdinal366DaysFromNow);


        // Get Day Number of 367 Days from Now
        String day367DaysFromNow = futureDayNumberFormat.format(threeHundredSixtySevenDaysFromNowDt);
        System.out.println("The day 367 Days From Now is " + day367DaysFromNow);

        // Get 10 days from now format to verify on the Hotel Search Results page
        // Ex. 25 Oct
        DateFormat searchResultDateFormat = new SimpleDateFormat("dd MMM");
        mSearchResultDepartureDate = searchResultDateFormat.format(tenDaysFromNowDt);

    }

    public void testCase02_Find_Hotel_Ten_Days_In_Future_Calendar_Functions() throws UiObjectNotFoundException {

        
        Utilities.validateImportantInformation();

        UiObject openFabBtn = new UiObject(new UiSelector()
                .resourceId(Utilities.PACKAGE + ":id/fab"));

        UiObject toolBar = new UiObject(new UiSelector()
                .resourceId(Utilities.PACKAGE + ":id/toolbar"));

        UiObject screenTitle = toolBar.getChild(new UiSelector().className("android.widget.TextView"));

        UiObject cancelMessage = new UiObject(new UiSelector().resourceId("android:id/message").textContains("Are you sure you want to Cancel the changes you have made?"));

        UiObject yesButton = new UiObject(new UiSelector().resourceId("android:id/button1").textContains("Yes"));

        try {

            // Wait For Sign In Menu Button
            int i = 0;
            while (!openFabBtn.exists()) {
                sleep(1000);
                i++;
                if (i == 60) {
                    System.out.println("Splash Page has taken longer than 240 seconds....This test may fail on the next step");
                    break;
                }
            }

            assertTrue("Splash Page has taken longer than 240 seconds", openFabBtn.exists());

            // Please Wait 15 seconds for Important Information
            System.out.println("// Please Wait 15 seconds for Important Information");
            sleep(15000);

            openFabBtn.clickAndWaitForNewWindow();


        // Step 2:  Tap Arrival Date
            // Verify it is currently set to todays date
            System.out.println("// Step 2:  Tap Arrival Date\n" +
                    "            // Verify it is currently set to todays date");

            UiObject arrivalDate = new UiObject(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/arrival_date"));

            UiObject departureDate = new UiObject(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/departure_date"));

            // Click on Arrival Date
            arrivalDate.clickAndWaitForNewWindow();
            takeScreenshot("ArrivalDateShouldBeHighlightedInGold");

        // Step 3:  Tap Back Button
            System.out.println("// Step 3:  Tap Back Button");
            
            UiObject navigateUpButton = new UiObject(new UiSelector().description("Navigate up"));
            navigateUpButton.clickAndWaitForNewWindow();

            // No Pop Up
            assertTrue("Back Button from Calendar did not take you back to Find a Hotel screen", screenTitle.getText().contains("Find a Hotel"));
            arrivalDate.clickAndWaitForNewWindow();

            // Verify Arrival Date is highlighted by default
            UiObject arrivalDateHighlight = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/left_highlight"));
            assertTrue("Arrival Date did not highlight", arrivalDateHighlight.exists());


            // Get The Year that is currently displayed
            System.out.println("// Get The Year that is currently displayed");
           UiObject yearDisplayed = new UiObject(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/year_title"));
            String yearCurrentlyDisplayed = yearDisplayed.getText();
            System.out.println("The year currently displayed is " + yearCurrentlyDisplayed);

            // Get the Month that is currently displayed
            System.out.println("// Get the Month that is currently displayed");
            UiObject monthDisplayed = new UiObject(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/month_title"));
            String monthCurrentlyDisplayed = monthDisplayed.getText();
            System.out.println("The month currently displayed is " + monthCurrentlyDisplayed);


            // Get the Scrollable Calendar View
            UiScrollable scrollableCalendarView = new UiScrollable(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/calendar_view")
                    .className(android.widget.ListView.class.getName()));

            // Get Calendar Grid for the Arrival Month to Select a Day
            UiCollection firstCalendarGrid = new UiCollection(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/calendar_grid")
                    .instance(0));

            UiCollection secondCalendarGrid = new UiCollection(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/calendar_grid")
                    .instance(1));



            // Click Back and Select Yes, to clear the Calendar
            navigateUpButton.clickAndWaitForNewWindow();


        // Step 7:  Select +10 days arrival
            
            System.out.println("// Step 7:  Select +10 days arrival");

            arrivalDate.clickAndWaitForNewWindow();

            // Scroll to the Desired Arrival Month Plus One Month
            // This value has to be One Month ahead so the the Target Month will be in View on Screen
            // For example, if you want to target NOV, this value must be DEC

            // Select the Arrival Date 10 days from Today
            System.out.println("// Select the Arrival Date 10 days from Today");
            arrivalDate.click();

            System.out.println("Scrolling to " + monthAbbrCAPSTenDaysFromNowPlusOneMonth);
            UiObject desiredArrivalMonthPlusOne = scrollableCalendarView.getChildByText(new UiSelector()
                            .className(android.widget.TextView.class.getName()),
                    monthAbbrCAPSTenDaysFromNowPlusOneMonth);  // "DEC"  //monthAbbrCAPSFiveDaysFromNowPlusOneMonth
            desiredArrivalMonthPlusOne.click();

            try {
                System.out.println("First Arrival Calendar: " + dayTenDaysFromNow);
                arrivalDateSelected = firstCalendarGrid.getChild(new UiSelector()
                        .className("android.widget.TextView")
                        .text(dayTenDaysFromNow));

                arrivalDateSelected.click();
                System.out.println(arrivalDateSelected.getSelector());

            } catch (UiObjectNotFoundException confe) {
                System.out.println("Second Arrival Calendar: " + dayTenDaysFromNow);
                arrivalDateSelected = secondCalendarGrid.getChild(new UiSelector()
                        .className("android.widget.TextView").text(dayTenDaysFromNow));  // "30"  // dayFiveDaysFromNow
                System.out.println(arrivalDateSelected.getSelector());
                arrivalDateSelected.click();
            }

            takeScreenshot("ArrivalGoldCircleDepartureBlackCircle");

        // Step 8:   Select +5 Days Departure
            System.out.println("// Step 8:   Select +5 Days Departure");
            
            departureDate.click();
            takeScreenshot("DepartureDateShouldBeHighlightedInGold");

            System.out.println("Scrolling to " + monthAbbrCAPSFiveDaysFromNowPlusOneMonth);
            UiObject desiredDepartureMonthPlusOne = scrollableCalendarView.getChildByText(new UiSelector()
                            .className(android.widget.TextView.class.getName()),
                    monthAbbrCAPSFiveDaysFromNowPlusOneMonth);  // "DEC"  //monthAbbrCAPSFiveDaysFromNowPlusOneMonth
            desiredDepartureMonthPlusOne.click();

            System.out.println("The day 5 days from now is " + dayFiveDaysFromNow);

            try {
                System.out.println("Trying First Calendar for Departure " + dayFiveDaysFromNow);
                departureDateSelected = firstCalendarGrid.getChild(new UiSelector()
                        .className("android.widget.TextView")
                        .text(dayFiveDaysFromNow));
                departureDateSelected.click();
                assertTrue("Departure Date Not Correct", departureDate.getText().contains(dayFiveDaysFromNow));
                System.out.println("First Grid Selected");
            } catch (UiObjectNotFoundException confe) {
                System.out.println("Second Grid selected " + dayFiveDaysFromNow);
                departureDateSelected = secondCalendarGrid.getChild(new UiSelector()
                        .className("android.widget.TextView").text(dayFiveDaysFromNow));  // "30"  // dayFiveDaysFromNow
                departureDateSelected.click();
                assertTrue("Departure Date Not Correct", departureDate.getText().contains(dayFiveDaysFromNow));
            }

            takeScreenshot("ArrivalGoldCircleDepartureBlackCircle");
            takeScreenshot("DatesBetweenShouldBeHighlightedGold");

            // Both Should be updated to +4+5
            assertTrue("Arrival Date is not +4 - Actual: " + arrivalDate.getText() + " Expected: " + dayFourDaysFromNow, arrivalDate.getText().contains(dayFourDaysFromNow));
            assertTrue("Departure Date is not +5", departureDate.getText().contains(dayFiveDaysFromNow));

        // Step 9:  Tap Back Button
            
            navigateUpButton.clickAndWaitForNewWindow();

            // Popup should be presented
            assertTrue("Cancel Message did not display", cancelMessage.exists());

        // Step 10:  Tap No
            
            UiObject noButton = new UiObject(new UiSelector().resourceId("android:id/button2").textContains("No"));
            noButton.clickAndWaitForNewWindow();

            assertTrue("Did not stay on Select Date after Clicking No on Cancel Message", screenTitle.getText().contains("Select Date"));
            assertTrue("Arrival Date is not +4 - Actual: " + arrivalDate.getText() + " Expected: " + dayFourDaysFromNow, arrivalDate.getText().contains(dayFourDaysFromNow));
            assertTrue("Departure Date is not +5", departureDate.getText().contains(dayFiveDaysFromNow));

        // Verify the Night Counter with a moon icon above it
            System.out.println("// Verify the Night Counter with a moon icon above it");

            UiObject moonContainer = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/moon_containter"));
            assertTrue("Moon Container is not present", moonContainer.exists());

            UiObject moonImage = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/moon_image"));
            assertTrue("Moon Image is not present", moonImage.exists());

            UiObject nightLabel = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/night_label").textContains("1 night"));
            assertTrue("Night Label is not present", nightLabel.exists());


        // Verify the week days are displayed
            
            System.out.println("// Verify the week days are displayed");

            UiObject daysOfWeek = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/days_of_week"));
            UiObject dayLetter;
            String[] daysArray  = {"S", "M", "T", "W", "T", "F", "S"};

            for (i = 0; i < 7; i++) {
                        dayLetter = daysOfWeek.getChild(new UiSelector().className("android.widget.TextView")
                        .index(i));
                assertTrue(daysArray[i] + " " + i + " was not present in Week Days", dayLetter.getText().equals(daysArray[i]));
            }


        // Step 11:  Tap Back Button
            navigateUpButton.clickAndWaitForNewWindow();
            

            // Popup should be presented
            assertTrue("Cancel Message did not display", cancelMessage.exists());

        // Step 12:  Tap Yes
            yesButton.clickAndWaitForNewWindow();
            

        // Step 13:  Tap Arrival Date
            arrivalDate.clickAndWaitForNewWindow();
            

            // Verify Dates Updated to Default
            System.out.println("Arrival Date is: " + arrivalDate.getText());
            System.out.println("The dayCurrent is: " + dayCurrent);
            System.out.println("Does Arrival Date Contain dayCurrent??" + arrivalDate.getText().contains(dayCurrent));
            assertTrue("Arrival Date Not Default - Actual: " + arrivalDate.getText() + " Expected: " + dayCurrent, arrivalDate.getText().contains(dayCurrent));
            assertTrue("Departure Date is not +5", departureDate.getText().contains(dayTommorow));

        // Step 14:  Setup Arival and Departure +10 days from today(same date)
            
            System.out.println("Scrolling to " + monthAbbrCAPSTenDaysFromNowPlusOneMonth);
            desiredArrivalMonthPlusOne = scrollableCalendarView.getChildByText(new UiSelector()
                            .className(android.widget.TextView.class.getName()),
                    monthAbbrCAPSTenDaysFromNowPlusOneMonth);  // "DEC"  //monthAbbrCAPSFiveDaysFromNowPlusOneMonth
            desiredArrivalMonthPlusOne.click();

            // Select the Arrival Date 10 days from Today
            try {
                System.out.println("First Arrival Calendar: " + dayTenDaysFromNow);

                arrivalDateSelected = firstCalendarGrid.getChild(new UiSelector()
                        .className("android.widget.TextView")
                        .text(dayTenDaysFromNow));
                arrivalDateSelected.clickAndWaitForNewWindow();

                // Click a Second Time since the Departure Date will be the same
                arrivalDateSelected.clickAndWaitForNewWindow();

            } catch (UiObjectNotFoundException confe) {
                System.out.println("Second Arrival Calendar: " + dayTenDaysFromNow);
                arrivalDateSelected = secondCalendarGrid.getChild(new UiSelector()
                        .className("android.widget.TextView").text(dayTenDaysFromNow));  // "30"  // dayFiveDaysFromNow
                arrivalDateSelected.click();

                // Click a Second Time since its the same day
                arrivalDateSelected.click();

            }

            System.out.println("3) A gold icon is placed over both the arrival and departure date");
            takeScreenshot("GoldIconOverBothArrivalAndDeparture");


            // Step 15:  Tap Done
            System.out.println("// Step 15:  Tap Done");
            UiObject doneButton = new UiObject(new UiSelector()
                    .descriptionContains("Done"));
            doneButton.clickAndWaitForNewWindow();
            



            // Step 19:  Check Use HHonors Points
            System.out.println("// Step 19:  Check Use HHonors Points");
            UiObject honorsPointsCheckbox = new UiObject(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/cbHhonorsPoints"));
            assertTrue("Honors Points Checkbox does not exist", honorsPointsCheckbox.exists());
            honorsPointsCheckbox.click();
            


            // Verify it is Checked.
            assertTrue("Honors Points Checkbox was not checked.", honorsPointsCheckbox.isChecked());

            // Step 21:  Tap Search
            System.out.println("// Step 21:  Tap Search");
            UiObject searchButton = new UiObject(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/searchButton"));
            searchButton.clickAndWaitForNewWindow();
            


            // Verify You Can not set the same departure and arrival date if using HHonors Points
            System.out.println("// Verify You Can not set the same departure and arrival date if using HHonors Points");
            UiObject noSameDayMsg = new UiObject(new UiSelector()
                    .resourceId("android:id/message"));
            assertEquals("No Same Day Msg is incorrect", "When using HHonors Points, your departure date must be later than your arrival date.", noSameDayMsg.getText());
            UiObject okBtn = new UiObject(
                    new UiSelector()
                    .resourceId("android:id/button1"));
            okBtn.clickAndWaitForNewWindow();

            // Uncheck HHonors Points
            System.out.println("// Uncheck HHonors Points");
            honorsPointsCheckbox.click();
            assertTrue("HHonors was not unchecked", !honorsPointsCheckbox.isChecked());

            searchButton.clickAndWaitForNewWindow();
            


            if (Build.VERSION.SDK_INT >= Utilities.ANDROID_M_VERSION) {
                
                UiObject searchBar = new UiObject(new UiSelector()
                        .resourceId(Utilities.PACKAGE + ":id/search_bar"));
                UiObject hotelsNearMeListItem = new UiObject(new UiSelector().resourceId(Utilities.PACKAGE + ":id/list_item_near_me"));
                UiObject locationPermissionMsg = new UiObject(new UiSelector().resourceId("com.android.packageinstaller:id/permission_message").textContains("Allow HHonors stg to access this device's location?"));
                UiObject locationPermissionAllowButton = new UiObject(new UiSelector().resourceId("com.android.packageinstaller:id/permission_allow_button").textContains("Allow"));

                assertTrue("Select a Location text not dislayed ", searchBar.getText().contains("Select a Location"));
                searchBar.clickAndWaitForNewWindow();
                hotelsNearMeListItem.clickAndWaitForNewWindow();
                assertTrue("Location Permission message did not appear", locationPermissionMsg.exists());
                assertTrue("Location Permission Allow Button is not present", locationPermissionAllowButton.exists());
                locationPermissionAllowButton.clickAndWaitForNewWindow();
                searchButton.clickAndWaitForNewWindow();
                
            }

            UiObject PleaseWaitText = new UiObject(new UiSelector().text("Please Wait..."));

            i = 0;
            while (PleaseWaitText.exists()) {
                sleep(1000);
                i++;
                if (i == 60) {
                    System.out.println("Splash Page has taken longer than 60 seconds....This test may fail on the next step");
                    break;
                }
            }


            // Step 21:  Tap on Search - Verify Date arrival and Departure
                    // Validate Screen name in Toolbar
                    System.out.println("// Step 10:  Tap on Search - Verify Date arrival and Departure");

            assertTrue("Choose a Hotel screen name is not displaying", screenTitle.getText().contains("Choose a Hotel"));


            // Since Departure Date and Arrival Date are the same, using the same variable for both
            System.out.println("// Since Departure Date and Arrival Date are the same, using the same variable for both");
            String summaryDatesString = mSearchResultDepartureDate + " - " + mSearchResultDepartureDate;
            System.out.println("The Summary Dates are " + summaryDatesString);
            UiObject summaryDates = new UiObject(new UiSelector()
                    .resourceId(Utilities.PACKAGE + ":id/summary_dates"));
            String summaryDatesText = summaryDates.getText();
            assertEquals("The Summary Dates are incorrect", summaryDatesString, summaryDatesText);
            


        }

        catch (AssertionFailedError afe) {
            System.out.println("Taking Screenshot after Assertion Failed Exception");
            takeScreenshot();
            throw afe;
        }
        catch (UiObjectNotFoundException onfe) {
            System.out.println("Taking Screenshot after UiObject Not Found Exception");
            takeScreenshot();
            throw onfe;
        }

    }

    public void takeScreenshot() {
        File path = new File("/sdcard/TestCase02_Fail.png");
        assertTrue("Didn't Take Screenshot", getUiDevice().takeScreenshot(path));
    }

    public void takeScreenshot(String testScenario) {
        File path = new File("/sdcard/TestCase02_" + testScenario + ".png");
        assertTrue("Didn't Take Screenshot", getUiDevice().takeScreenshot(path));
    }

    public void tearDown() throws UiObjectNotFoundException {
    }

}