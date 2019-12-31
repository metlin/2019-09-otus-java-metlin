package ru.otus.homework8;

import java.lang.reflect.Field;
import java.util.Collection;

public class MyJson {
    public String toJson(int[] arrayInt) {
        if (arrayInt == null) {
            return "null";
        }

        String json = "[";
        for (int value : arrayInt) {
            json += value + ",";
        }

        json = json.substring(0, json.length() - 1);
        return json + "]";
    }

    public String toJson(Object[] objects) {
        if(objects == null) {
            return "null";
        }

        String json = "[";
        for(Object object : objects) {
            json += "{";
            Field[] fields = object.getClass().getDeclaredFields();
            json = getJsonObject(json, object, fields);
        }

        json = json.substring(0, json.length() - 1);
        return json + "]";
    }

    private String getJsonObject(String json, Object object, Field[] fields) {
        for(Field field : fields) {
            try {
                field.setAccessible(true);
                if(field.get(object) instanceof Number) {
                    json += "\"" + field.getName() + "\":" + field.get(object) + ",";
                } else {
                    json += "\"" + field.getName() + "\":\"" + field.get(object) + "\",";
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        json = json.substring(0, json.length() - 1);
        json += "},";
        return json;
    }

    public <T> String toJson(Collection<T> collection) {
        if(collection == null) {
            return "null";
        }
        String json = "[\"";
            for(T element : collection) {
                json += element.toString() + "\",\"";
            }

            json = json.substring(0, json.length() - 2);

            return json + "]";
    }

    public String toJson(Number number) {
        return String.valueOf(number);
    }

    public String toJson(Dog dog) {
        if(dog == null) {
            return "null";
        }

        String json = "{";
        Field[] fields = Dog.class.getDeclaredFields();
        json = getJsonObject(json, dog, fields);

        json = json.substring(0, json.length() - 1);
        return json;
    }
}
