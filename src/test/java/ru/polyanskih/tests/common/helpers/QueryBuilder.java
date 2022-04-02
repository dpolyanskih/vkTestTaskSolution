package ru.polyanskih.tests.common.helpers;

import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.queries.likes.LikesAddQuery;
import com.vk.api.sdk.queries.likes.LikesDeleteQuery;
import com.vk.api.sdk.queries.likes.LikesGetListQuery;
import com.vk.api.sdk.queries.likes.LikesIsLikedQuery;
import com.vk.api.sdk.queries.wall.WallGetQuery;
import ru.polyanskih.tests.VkTestBase;

public class QueryBuilder extends VkTestBase {

    /**
     * Generates query for add like to item with type = POST
     * @param itemId id of item
     * @param ownerId id of owner
     */
    public static LikesAddQuery addPostLikeQuery(int itemId, int ownerId) {
        return addPostLikeQuery(itemId, Type.POST, ownerId);
    }

    /**
     * Generates query for add like to item with specified type
     * @param itemId id of item
     * @param ownerId id of owner
     * @param type item type
     */
    public static LikesAddQuery addPostLikeQuery(int itemId, Type type, int ownerId) {
        return VK.likes().add(ACTOR, type, itemId).ownerId(ownerId);
    }

    /**
     * Generates query for delete like from item with type = POST
     * @param itemId id of item
     * @param ownerId id of owner
     */
    public static LikesDeleteQuery deleteLikeQuery(int itemId, int ownerId) {
        return deleteLikeQuery(itemId, Type.POST, ownerId);
    }

    /**
     * Generates query for delete like from item with specified type
     * @param itemId - id of item
     * @param ownerId - id of owner
     * @param type - item type
     */
    public static LikesDeleteQuery deleteLikeQuery(int itemId, Type type, int ownerId) {
        return VK.likes().delete(ACTOR, type, itemId).ownerId(ownerId);
    }

    /**
     * Generates query for request to get likes from item with specified type
     * @param itemId - id of item
     * @param ownerId - id of owner
     * @param type - item type
     */
    public static LikesGetListQuery listLikesQuery(Type type, int itemId, int ownerId) {
        return VK.likes().getList(ACTOR, type).ownerId(ownerId).itemId(itemId);
    }

    /**
     * Generates query for check like of item with type = POST
     * @param itemId id of item
     * @param userId id of user
     */
    public static LikesIsLikedQuery isLikedQuery(int itemId, int userId) {
        return isLikedQuery(Type.POST, itemId, userId);
    }

    /**
     * Generates query for check like of item with specified type
     * @param itemId id of item
     * @param userId id of user
     * @param type - item type
     */
    public static LikesIsLikedQuery isLikedQuery(Type type, int itemId, int userId) {
        return VK.likes().isLiked(ACTOR, type, itemId).userId(userId);
    }

    /**
     * Generates query to get last wall post of specified owner
     * @param ownerId - id of owner
     */
    public static WallGetQuery wallGetLastPostQuery(int ownerId) {
        return wallGetQuery(ownerId, 1);
    }

    /**
     * Generates query to get wall posts of specified owner
     * @param ownerId - id of owner
     * @param count count of posts in response
     */
    public static WallGetQuery wallGetQuery(int ownerId, int count) {
        return VK.wall().get(ACTOR).ownerId(ownerId).count(count);
    }
}
