package ru.polyanskih.tests.common.helpers;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.likes.responses.DeleteResponse;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import com.vk.api.sdk.objects.likes.responses.IsLikedResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.likes.LikesAddQuery;
import com.vk.api.sdk.queries.likes.LikesDeleteQuery;
import com.vk.api.sdk.queries.likes.LikesGetListQuery;
import com.vk.api.sdk.queries.likes.LikesIsLikedQuery;
import com.vk.api.sdk.queries.wall.WallGetQuery;
import org.testng.Reporter;
import org.testng.TestNGException;

import static ru.polyanskih.tests.common.helpers.QueryBuilder.addPostLikeQuery;
import static ru.polyanskih.tests.common.helpers.QueryBuilder.deleteLikeQuery;
import static ru.polyanskih.tests.common.helpers.QueryBuilder.isLikedQuery;
import static ru.polyanskih.tests.common.helpers.QueryBuilder.listLikesQuery;
import static ru.polyanskih.tests.common.helpers.QueryBuilder.wallGetLastPostQuery;

public class RequestHelper {

    public static AddResponse addLike(int itemId, int ownerId) throws ApiException {
        LikesAddQuery likesAddQuery = addPostLikeQuery(itemId, ownerId);
        return (AddResponse) executeAndLogRequest(likesAddQuery);
    }

    public static DeleteResponse deleteLike(int itemId, int ownerId) throws ApiException {
        LikesDeleteQuery likesDeleteQuery = deleteLikeQuery(itemId, ownerId);
        return (DeleteResponse) executeAndLogRequest(likesDeleteQuery);
    }

    public static GetListResponse listLikes(Type type, int itemId, int ownerId) throws ApiException {
        LikesGetListQuery likesGetListQuery = listLikesQuery(type, itemId, ownerId);
        return (GetListResponse) executeAndLogRequest(likesGetListQuery);
    }

    public static IsLikedResponse isLiked(int itemId, int userId) throws ApiException {
        LikesIsLikedQuery likesIsLikedQuery = isLikedQuery(itemId, userId);
        return (IsLikedResponse) executeAndLogRequest(likesIsLikedQuery);
    }

    public static GetResponse getUserWallLastPost(int ownerId) throws ApiException {
        WallGetQuery wallGetQuery = wallGetLastPostQuery(ownerId);
        return (GetResponse) executeAndLogRequest(wallGetQuery);
    }

    private static Validable executeAndLogRequest(AbstractQueryBuilder request) throws ApiException {
        Reporter.log("Execute method " + request.getMethod() + " with params " + request.getParams() + "\r\n");
        Validable validable;
        try {
            validable = (Validable) request.execute();
        } catch (ClientException e) {
            throw new TestNGException("Failed to execute " + request + " due to " + e.getMessage());
        }
        Reporter.log("Response:\r\n" + validable.toString() + "\r\n");
        return validable;
    }
}
