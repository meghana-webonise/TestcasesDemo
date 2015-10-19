package com.weboniselab.meghana.android.app.testingdemoapplication;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private TextView tv;


    public ApplicationTest(){
        super(MainActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity= getActivity();
        assertNotNull(mainActivity);
        tv= (TextView)mainActivity.findViewById(R.id.tv);

    }

    @MediumTest
    public void testOpenNextActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NewActivity.class.getName(), null, false);
        final Button button = (Button) mainActivity.findViewById(R.id.btn);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                button.performClick();
            }
        });

        NewActivity newActivity = (NewActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500);
        // next activity is opened and captured.
        assertNotNull(newActivity);
        newActivity .finish();
    }

    @SmallTest
    public void testTextView() {
        assertNotNull("text view is null", tv);
    }

    @SmallTest
    public void testTextViewContent() {
        assertEquals("Text view text is incorrect", tv.getText().toString(), mainActivity.getString(R.string.hello_world));
    }

    @SmallTest
    public void testMultiply() {
        assertEquals("10 x 5 must be 50", 50, 10 * 5);
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testMethodCheck() {
        mainActivity.setLeft(5);
        mainActivity.setRight(10);
        mainActivity.add();
        assertEquals("10 + 5 must be 15", 15, mainActivity.getResult());
        mainActivity.multiply();
        assertEquals("10 * 5 must be 50", 50, mainActivity.getResult());
    }
}