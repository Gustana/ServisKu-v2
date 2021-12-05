package com.example.serviceku.ui.auth;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.serviceku.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void registerActivityTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.btnLogin), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                11),
                        isDisplayed()));
        button.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction editText = onView(
                allOf(withId(R.id.edtEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                2),
                        isDisplayed()));
        editText.perform(replaceText("gesa@gmail.com"), closeSoftKeyboard());



        ViewInteraction button2 = onView(
                allOf(withId(R.id.btnLogin), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                11),
                        isDisplayed()));
        button2.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.edtPassword),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                4),
                        isDisplayed()));
        editText2.perform(replaceText("123"), closeSoftKeyboard());



        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnLogin), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                11),
                        isDisplayed()));
        button3.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.edtNama),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6),
                        isDisplayed()));
        editText3.perform(replaceText("gesha_0109"), closeSoftKeyboard());



        ViewInteraction button4 = onView(
                allOf(withId(R.id.btnLogin), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                11),
                        isDisplayed()));
        button4.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.edtNo),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                8),
                        isDisplayed()));
        editText4.perform(replaceText("081219210109"), closeSoftKeyboard());



        ViewInteraction button5 = onView(
                allOf(withId(R.id.btnLogin), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                11),
                        isDisplayed()));
        button5.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.rbLakiLaki), withText("Laki - Laki"),
                        childAtPosition(
                                allOf(withId(R.id.rgJenisKelamin),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                10)),
                                0),
                        isDisplayed()));
        radioButton.perform(click());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.btnLogin), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                11),
                        isDisplayed()));
        button6.perform(click());
        onView(isRoot()).perform(waitFor(3000));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return isRoot();
            }
            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }
            @Override public void perform(UiController uiController,
                                          View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }
}
