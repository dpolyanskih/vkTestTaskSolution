package ru.polyanskih.tests.likes;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import org.testng.TestNGException;
import org.testng.annotations.Test;
import ru.polyanskih.tests.VkTestBase;
import ru.polyanskih.tests.common.dataproviders.InvalidParameters;

import static ru.polyanskih.tests.common.asserts.AssertsWithLog.assertNotNullWithLog;
import static ru.polyanskih.tests.common.asserts.AssertsWithLog.compareException;
import static ru.polyanskih.tests.common.factory.ExceptionFactory.apiParamException;
import static ru.polyanskih.tests.common.helpers.RequestHelper.listLikes;

public class TestLikesGetList extends VkTestBase {

    @Test(groups = "likes.getList")
    public static void shouldReturnLikesList() {
        GetListResponse getListResponse;
        try {
            getListResponse = listLikes(Type.POST, SIMPLE_POST_ID, PUBLIC_PROFILE_ID);
        } catch (ApiException e) {
            throw new TestNGException("Unexpected exception '" + e.getMessage() + "' during likes.getList execution");
        }

        assertNotNullWithLog(getListResponse.getCount(), "count", "Expect 'count' in response");
        assertNotNullWithLog(getListResponse.getItems(), "items", "Expect 'items' in response");
    }

    @Test(groups = "likes.getList", dataProvider = "invalidItemId", dataProviderClass = InvalidParameters.class)
    public static void shouldFailToReturnLikesListInvalidItemId(int itemId) {
        try {
            listLikes(Type.POST, itemId, PRIVATE_PROFILE_ID);
        } catch (ApiException actualException) {
            compareException(actualException, apiParamException("item_id"));
        }
    }
}
