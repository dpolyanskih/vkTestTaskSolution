package ru.polyanskih.tests;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.SkipException;
import org.testng.TestNGException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import ru.polyanskih.tests.common.Listener;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static ru.polyanskih.tests.common.asserts.AssertsWithLog.assertNotNullWithLog;
import static ru.polyanskih.tests.common.helpers.RequestHelper.getUserWallLastPost;

@Listeners(Listener.class)
public class VkTestBase {

    protected static VkApiClient VK = new VkApiClient(new HttpTransportClient());
    protected static UserActor ACTOR;
    protected static int PUBLIC_PROFILE_ID = 1;
    protected static int PRIVATE_PROFILE_ID = 24214938; // yep, that's me :)
    protected static int SIMPLE_ITEM_ID = 12345678;
    protected static int SIMPLE_POST_ID;
    private static String USERNAME;
    private static String PASSWORD;
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    private static final String AUTH_URI = "https://oauth.vk.com/token";

    @BeforeSuite(alwaysRun = true)
    public static void init() {
        loadProperties();
        ACTOR = getUserActor();
        SIMPLE_POST_ID = getUserLastPostId(PUBLIC_PROFILE_ID);
    }

    private static int getUserLastPostId(int ownerId) {
        GetResponse getResponse;
        try {
            getResponse = getUserWallLastPost(ownerId);
        } catch (ApiException e) {
            throw new TestNGException("");
        }
        assertNotNullWithLog(getResponse.getItems().get(0).getId(), "item.id", "Expect item.id");
        return getResponse.getItems().get(0).getId();
    }

    private static UserActor getUserActor() {
        JsonPath authResponse = auth();
        return new UserActor(authResponse.getInt("user_id"), authResponse.getString("access_token"));
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(VkTestBase.class.getClassLoader().getResourceAsStream("config.properties"));
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            CLIENT_ID = properties.getProperty("client_id");
            CLIENT_SECRET = properties.getProperty("client_secret");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static JsonPath auth() {
        Response response = given()
                .queryParam("client_id", CLIENT_ID)
                .queryParam("grant_type", "password")
                .queryParam("client_secret", CLIENT_SECRET)
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD)
                .queryParam("scope", "offline")
                .when()
                .get(AUTH_URI);
        int statusCode = response.statusCode();
        if (statusCode != 200) {
            throw new SkipException("Failed authorization, unable to get user_id, access_token, response is " + response.asPrettyString());
        }
        return response.jsonPath();
    }
}
