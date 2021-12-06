package com.wtf.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;
/**
 * Hello world!
 *
 */
public class App 
{


    public void testTime() {

        System.out.println(String.format("start %s, end : %s", LocalDateTime.MIN, LocalDateTime.MAX));
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now " + now);
        LocalDateTime someDate = LocalDateTime.of(2020, 9, 12, 12, 11, 34, 33);
        System.out.println("some date: " + someDate);

        Timestamp ts = Timestamp.valueOf(someDate);
        System.out.println("timestammp: " + ts);

        Instant ins = ts.toInstant();
        System.out.println("instant: " + ins);

        // test gson with timestamp
        Gson g = new Gson();

        System.out.println("datetime to gson  " + g.toJson(someDate));
        System.out.println("timestamp to gson  " + g.toJson(ts));
        System.out.println("instant to gson  " + g.toJson(ins));

        // test timestamp
        Long createTime =  Long.valueOf("1638186939000");
        long modifiedTime = Long.valueOf("1638187996000");
        // Timestamp ct = Timestamp.valueOf("1638186939000");
        Date ct = new Date(createTime); // date和local date time什么区别. date是旧api但也没说被废弃，local date是新api。
        System.out.println("create time " + ct + " modified time" + new Date(modifiedTime));
    }

    private void printArray(String[] abc) {
        StringBuilder sb = new StringBuilder();
        for (String a : abc) {
            sb.append("|" + a);
        }
        System.out.println(abc + "--> " + sb.toString());
    }
    
    public void testSplit() {
        String qualifiedName = "a.b.c.d";
        printArray(qualifiedName.split("\\.", 1));
        printArray(qualifiedName.split("\\.", 2));
        printArray(qualifiedName.split("\\.", 3));
        printArray(qualifiedName.split("\\.", 4));
        printArray(qualifiedName.split("\\.", 5));
        printArray(qualifiedName.split("\\.", 0));
        printArray(qualifiedName.split("\\.", -1));
        printArray(qualifiedName.split("\\."));
        int idx = qualifiedName.lastIndexOf(".");
        String a = qualifiedName.substring(0, idx);
        System.out.println(a);
    }

    public void testGson() {
        Gson gsonx = new Gson();
        String g = gsonx.toJson(1);
        // com.google.gson.JsonObject but was com.google.gson.JsonPrimitive
        // JsonObject go = gsonx.fromJson(g, JsonObject.class); // 无法操作 需要primitive

        JsonObject go = gsonx.fromJson("{\"a\": 1}", JsonObject.class);

        go.add("abcd", JsonNull.INSTANCE);
        // go.add("abc", "123"); // 无法操作
        // go.addProperty("abcisNull", null); // 无法操作
        String nullStr = null;
        go.addProperty("null", nullStr); // string为null没有问题
        go.addProperty("dup", 234);
        go.addProperty("dup", "2345");

        System.out.println(go);
        System.out.println("--------------------------------");
        // JsonObject empty = (new Gson()).fromJson(null, JsonObject.class); // 无法操作
        // System.out.println(empty);
        JsonObject param = (new Gson()).fromJson("", JsonObject.class); // 竟然是null
        System.out.println(param);
        System.out.println("--------------");
        param = new JsonObject();
        param.addProperty("abc", "");
        System.out.println(param);

    }

    // 测试如果快速从json字符串中稳定抽取参数
    public void testGsonIter() {
        String result = "{\"code\":\"success\",\"msg\":\"Get tasks info successfully\",\"error\":null,\"resultData\":{\"taskId\":\"20211130143949991\",\"virtualTaskId\":null,\"virtualFlag\":false,\"taskName\":\"fghdfg\",\"startTime\":\"2021-11-30 00:00:00\",\"endTime\":\"2021-11-30 00:00:00\",\"projectId\":4,\"projectName\":\"\",\"taskType\":{\"typeId\":34,\"typeDesc\":\"Hive SQL\",\"targetServerType\":null,\"defaultAliveWait\":720,\"taskTypeExts\":{\"source_service\":{\"typeId\":34,\"propName\":\"source_service\"}}}}}";
        JsonObject jObject = new Gson().fromJson(result, JsonObject.class);
        JsonElement jElement = jObject.get("code");
        System.out.println("try: " + jElement + " ");
        // jElement.get("abc"); // get undefined from jsonElement
        // System.out.println(jElement.getAsJsonObject()); // ClassCastException: Not a JSON Object: "success"
        // System.out.println("interative" + jObject.getAsJsonObject("resultData").getAsJsonObject("taskId")); //  java.lang.ClassCastException: com.google.gson.JsonPrimitive cannot be cast to com.google.gson.JsonObject
        System.out.println("interative: " + jObject.getAsJsonObject("resultData").getAsJsonObject("a")); // null
        JsonObject a = jObject.getAsJsonObject("resultData").getAsJsonObject("a");
        // System.out.println(" " + a.isJsonNull());  // java.lang.NullPointerException
        // JsonObject b = jObject.getAsJsonObject("resultData").getAsJsonObject("virtualTaskId"); // com.google.gson.JsonNull cannot be cast to com.google.gson.JsonObject
        JsonElement c = jObject.getAsJsonObject("resultData").get("virtualTaskId");
        System.out.println("jsonnull? " + c.isJsonNull() + "  null? " + (c == null));


    }

    public void testString() {
        String a = null;
        String b = "abc";
        System.out.println(a);
        System.out.println(a + b);

        String bb = new Gson().fromJson(b, JsonElement.class).getAsString();
        String bbb = new Gson().fromJson(b, JsonElement.class).toString();

        System.out.println("getAsString|" + bb + "|end");
        System.out.println("toString|" + bbb + "|end"); // 会额外带有双引号
    }

    public static void main( String[] args )
    {
        System.out.println("----------------------[test datetime]------------------------------------------");
        new App().testTime();
        System.out.println("----------------------[test gson]------------------------------------------");
        (new App()).testGson();
        System.out.println("Hello World!");
        System.out.println("----------------------[test split]------------------------------------------");
        (new App()).testSplit();

        System.out.println("----------------------[test gson iter]------------------------------------------");
        (new App()).testGsonIter();

        System.out.println("----------------------[test string]------------------------------------------");
        new App().testString();
    }
}
