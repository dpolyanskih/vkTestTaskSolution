package ru.polyanskih.tests.common.helpers;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.likes.LikesAddQuery;
import com.vk.api.sdk.queries.wall.WallGetQuery;
import org.testng.Reporter;
import org.testng.TestNGException;

import static ru.polyanskih.tests.common.helpers.QueryBuilder.addPostLikeQuery;

public class RequestHelper {

    public static AddResponse addLike(int itemId, int ownerId) {
        LikesAddQuery likesAddQuery = addPostLikeQuery(itemId, ownerId);
        return (AddResponse) executeAndLogRequest(likesAddQuery);
    }

    /**
     *
     * @param ownerId
     * @return
     * @throws ClientException
     * @throws ApiException
     */
    public static GetResponse getUserWallLastPost(int ownerId) {
        WallGetQuery wallGetQuery = QueryBuilder.wallGetQuery(ownerId);
        return (GetResponse) executeAndLogRequest(wallGetQuery);
    }

    private static Validable executeAndLogRequest(AbstractQueryBuilder request) {
        Reporter.log("Execute method " + request.getMethod() + " with params " + request.getParams() + "\r\n");
        Validable validable;
        try {
            validable = (Validable) request.execute();
        } catch (ApiException | ClientException e) {
            throw new TestNGException("Failed to execute " + request + " due to " + e.getMessage());
        }
        Reporter.log("Response:\r\n" + validable.toString() + "\r\n");
        return validable;
    }
}
