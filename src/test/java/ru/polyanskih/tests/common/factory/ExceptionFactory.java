package ru.polyanskih.tests.common.helpers;

import com.vk.api.sdk.exceptions.ApiParamException;
import com.vk.api.sdk.exceptions.ApiPrivateProfileException;

public class Exception {

    public static ApiPrivateProfileException privateProfileException() {
        return new ApiPrivateProfileException("This profile is private");
    }

    public static ApiParamException apiParamException(String paramName) {
        return new ApiParamException("One of the parameters specified was missing or invalid: " + paramName +" is undefined");
    }
}
