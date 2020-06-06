package org.acme.rest.json;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;

@QuarkusTest
public class FruitResourceTest {

    @Test
    @SuppressWarnings("unchecked")
    public void postFruitSuccessfulWithNameAndDescription() {
        JsonObject obj = new JsonObject();
        obj.put("name", "Orange");
        obj.put("description", "fresh oranges");
        LinkedHashMap<String, String> resp = given().when().body(obj.toString()).contentType(ContentType.JSON)
                .post("/fruits").then().statusCode(200).extract().as(LinkedHashMap.class);
        assertNotNull(resp.get("created"));
        assertNotNull(resp.get("uuid"));
        assertEquals("Orange", resp.get("name"));
        assertEquals("fresh oranges", resp.get("description"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void postFruitWitUUIDAndDate() {
        String uuid = UUID.randomUUID().toString();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        JsonObject obj = new JsonObject();
        obj.put("name", "Orange");
        obj.put("description", "fresh oranges");
        obj.put("uuid", uuid);
        obj.put("created", formatter.format(date));

        LinkedHashMap<String, String> resp = given().when().body(obj.toString()).contentType(ContentType.JSON)
                .post("/fruits").then().statusCode(200).extract().as(LinkedHashMap.class);
        LocalDateTime returnedDate = LocalDateTime.parse(resp.get("created"), formatter);
        assertNotEquals(date, returnedDate);
        assertNotEquals(uuid, resp.get("uuid"));
        assertEquals("Orange", resp.get("name"));
        assertEquals("fresh oranges", resp.get("description"));
    }

}