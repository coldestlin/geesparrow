package demo.ocx.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Demo {

    private void gsonTest(){

        // Serialization
        Gson gson = new Gson();
//        gson.toJson(1);            // ==> 1
//        gson.toJson("abcd");       // ==> "abcd"
//        gson.toJson(new Long(10)); // ==> 10
//        int[] values = { 1 };
//        gson.toJson(values);       // ==> [1]

//        // Deserialization
//        int one = gson.fromJson("1", int.class);
//        Integer one = gson.fromJson("1", Integer.class);
//        Long one = gson.fromJson("1", Long.class);
//        Boolean falsex = gson.fromJson("false", Boolean.class);
//        String str = gson.fromJson("\"abc\"", String.class);
//        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);

    }

    public static void main(String[] args) {
        Demo d = new Demo();
        d.gsonTest();
    }
}
