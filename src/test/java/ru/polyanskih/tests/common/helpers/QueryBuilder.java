package ru.polyanskih.tests.common.helpers;

import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.queries.likes.LikesAddQuery;
import com.vk.api.sdk.queries.wall.WallGetQuery;
import ru.polyanskih.tests.common.VkTestBase;

public class QueryBuilder extends VkTestBase {

    public static LikesAddQuery addPostLikeQuery(int itemId, int ownerId) {
        return addPostLikeQuery(itemId, Type.POST, ownerId);
    }

    public static LikesAddQuery addPostLikeQuery(int itemId, Type type, int ownerId) {
        return vk.likes().add(actor, type, itemId).ownerId(ownerId);
    }

    public static WallGetQuery wallGetQuery(int ownerId) {
        return wallGetQuery(ownerId, 1);
    }

    public static WallGetQuery wallGetQuery(int ownerId, int count) {
        return vk.wall().get(actor).ownerId(ownerId).count(count);
    }
}
