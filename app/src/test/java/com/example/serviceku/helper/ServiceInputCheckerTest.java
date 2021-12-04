package com.example.serviceku.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ServiceInputCheckerTest {


    ServiceInputChecker serviceInputChecker;

    @Before
    public void setUp() {
        serviceInputChecker = new ServiceInputChecker("", "rusak", "motor");
    }

    @Test
    public void shouldTrueIfVehicleNoEmpty() {
        assertTrue(serviceInputChecker.isNoPlatEmpty());
    }

    @Test
    public void shouldFalseIfProblemNotEmpty() {
        assertFalse(serviceInputChecker.isProblemEmpty());
    }

    @Test
    public void shouldTrueIfVehicleTypeNotEmpty() {
        assertFalse(serviceInputChecker.isVehicleTypeEmpty());
    }


}