package ru.polyanskih.tests.likes;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import org.testng.TestNGException;
import org.testng.annotations.Test;
import ru.polyanskih.tests.VkTestBase;

import static ru.polyanskih.tests.common.asserts.AssertsWithLog.assertNotNullWithLog;
import static ru.polyanskih.tests.common.asserts.AssertsWithLog.compareException;
import static ru.polyanskih.tests.common.factory.ExceptionFactory.privateProfileException;
import static ru.polyanskih.tests.common.helpers.RequestHelper.addLike;

public class TestLikesAdd extends VkTestBase {

    @Test(groups = "likes.add")
    public static void shouldAddLike() {
        AddResponse addResponse;
        try {
            addResponse = addLike(SIMPLE_POST_ID, PUBLIC_PROFILE_ID);
        } catch (ApiException e) {
            throw new TestNGException("Unexpected exception '" + e.getMessage() + "' during likes.add execution");
        }

        assertNotNullWithLog(addResponse.getLikes(), "likes", "Expected 'likes' field in response");
    }

    @Test(groups = "likes.add")
    public static void shouldFailToAddLikeToPrivateProfile() {
        try {
            addLike(SIMPLE_ITEM_ID, PRIVATE_PROFILE_ID);
        } catch (ApiException actualException) {
            compareException(actualException, privateProfileException());
        }
    }
}
