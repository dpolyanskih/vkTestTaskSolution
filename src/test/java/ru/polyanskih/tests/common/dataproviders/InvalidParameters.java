package ru.polyanskih.tests.common.dataproviders;

import org.testng.annotations.DataProvider;

public class InvalidParameters {

    @DataProvider
    private static Object[][] invalidItemId() {
        return new Object[][]{
                {-1},
                {0},
                {Integer.MIN_VALUE},
        };
    }
}
