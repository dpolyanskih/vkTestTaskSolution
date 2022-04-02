package ru.polyanskih.tests.likes;

import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import org.testng.annotations.Test;
import ru.polyanskih.tests.common.VkTestBase;

import static ru.polyanskih.tests.common.helpers.RequestHelper.addLike;
import static ru.polyanskih.tests.common.helpers.RequestHelper.getUserWallLastPost;

public class TestLikesGetList extends VkTestBase {

    @Test
    public static void shouldAddLike() {
        GetResponse getResponse = getUserWallLastPost(1);
        Integer postId = getResponse.getItems().get(0).getId();
        AddResponse addResponse = addLike(postId, 1);
    }
}
