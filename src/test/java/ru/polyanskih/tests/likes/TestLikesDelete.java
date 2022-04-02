package ru.polyanskih.tests.likes;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.objects.likes.responses.DeleteResponse;
import org.testng.TestNGException;
import org.testng.annotations.Test;
import ru.polyanskih.tests.VkTestBase;

import static ru.polyanskih.tests.common.asserts.AssertsWithLog.assertNotNullWithLog;
import static ru.polyanskih.tests.common.asserts.AssertsWithLog.compareException;
import static ru.polyanskih.tests.common.factory.ExceptionFactory.privateProfileException;
import static ru.polyanskih.tests.common.helpers.RequestHelper.deleteLike;

public class TestLikesDelete extends VkTestBase {

    @Test(groups = "likes.delete")
    public static void shouldAddLike() {
        DeleteResponse deleteResponse;
        try {
            deleteResponse = deleteLike(SIMPLE_POST_ID, PUBLIC_PROFILE_ID);
        } catch (ApiException e) {
            throw new TestNGException("Unexpected exception '" + e.getMessage() + "' during likes.delete execution");
        }

        assertNotNullWithLog(deleteResponse.getLikes(), "likes", "Expected 'likes' field in response");
    }

    @Test(groups = "likes.delete")
    public static void shouldFailToDeleteLikeFromPrivateProfile() {
        try {
            deleteLike(SIMPLE_ITEM_ID, PRIVATE_PROFILE_ID);
        } catch (ApiException actualException) {
            compareException(actualException, privateProfileException());
        }
    }
}
