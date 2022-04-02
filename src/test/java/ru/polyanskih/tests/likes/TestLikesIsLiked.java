package ru.polyanskih.tests.likes;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.objects.likes.responses.IsLikedResponse;
import org.testng.TestNGException;
import org.testng.annotations.Test;
import ru.polyanskih.tests.VkTestBase;

import static ru.polyanskih.tests.common.asserts.AssertsWithLog.assertNotNullWithLog;
import static ru.polyanskih.tests.common.asserts.AssertsWithLog.compareException;
import static ru.polyanskih.tests.common.factory.ExceptionFactory.privateProfileException;
import static ru.polyanskih.tests.common.helpers.RequestHelper.isLiked;

public class TestLikesIsLiked extends VkTestBase {

    @Test(groups = "likes.isLiked")
    public static void shouldCheckLike() {
        IsLikedResponse isLikedResponse;
        try {
            isLikedResponse = isLiked(SIMPLE_POST_ID, PUBLIC_PROFILE_ID);
        } catch (ApiException e) {
            throw new TestNGException("Unexpected exception '" + e.getMessage() + "' during likes.isLiked execution");
        }

        assertNotNullWithLog(isLikedResponse.getLiked(), "liked", "Expect 'liked' in response");
        assertNotNullWithLog(isLikedResponse.getCopied(), "copied", "Expect 'copied' in response");
    }

    @Test(groups = "likes.isLiked")
    public static void shouldFailToCheckLikeOnPrivateProfile() {
        try {
            isLiked(SIMPLE_POST_ID, PRIVATE_PROFILE_ID);
        } catch (ApiException actualException) {
            compareException(actualException, privateProfileException());
        }
    }
}
