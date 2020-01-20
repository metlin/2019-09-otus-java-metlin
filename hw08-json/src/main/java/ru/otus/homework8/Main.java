package ru.otus.homework8;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //массив объектов
        Gson gson = new Gson();
        MyJson myJsonConverter = new MyJson();
        Dog dog1 = new Dog("Sharik", 5);
        Dog dog2 = new Dog("Bobik", 3);
        Dog[] array1 = {dog1, dog2};
        System.out.println(Arrays.toString(array1));

        String jsonObject = myJsonConverter.toJson(array1);
        System.out.println(jsonObject);

        Dog[] array2 = gson.fromJson(jsonObject, Dog[].class);
        System.out.println(Arrays.equals(array1, array2));
        System.out.println(Arrays.toString(array2));

        //массив примитивов
        Gson gsonInt = new Gson();
        MyJson myJsonIntConverter = new MyJson();
        int[] arrayInt1 = {1, 2, 3};
        System.out.println(Arrays.toString(arrayInt1));

        String jsonInt = myJsonIntConverter.toJson(arrayInt1);
        System.out.println(jsonInt);

        int[] arrayInt2 = gsonInt.fromJson(jsonInt, int[].class);
        System.out.println(Arrays.equals(arrayInt1, arrayInt2));
        System.out.println(Arrays.toString(arrayInt2));

        //Коллекции из стандартной библиотеки
        Gson gsonCollection = new Gson();
        MyJson myJsonCollectionConverter = new MyJson();
        List<String> stringList1 = new ArrayList<>();
        stringList1.add("hello");
        stringList1.add("world");
        System.out.println(stringList1);

        String jsonCollection = myJsonCollectionConverter.toJson(stringList1);
        System.out.println(jsonCollection);

        Type typeToken = new TypeToken<List<String>>(){}.getType();
        List<String> stringList2 = gsonCollection.fromJson(jsonCollection, typeToken);
        System.out.println(CollectionUtils.isEqualCollection(stringList1, stringList2));
        System.out.println(stringList2);

        //примитивы
        Gson gsonPrimitive = new Gson();
        MyJson myJsonPrimitiveConverter = new MyJson();
        long number1 = 5;
        System.out.println(number1);

        String jsonPrimitive = myJsonPrimitiveConverter.toJson(number1);
        System.out.println(jsonPrimitive);

        long number2 = gsonPrimitive.fromJson(jsonPrimitive, long.class);
        System.out.println(number1 == number2);
        System.out.println(number2);

        //свой объект
        Gson gson1 = new Gson();
        MyJson myJsonObjectConverter = new MyJson();
        Serializable dog3 = new Dog("Tuzik", 1);
        System.out.println(dog3);

        String jsonObject1 = myJsonObjectConverter.toJson(dog3);
        System.out.println(jsonObject1);

        Serializable dog4 = gson1.fromJson(jsonObject1, Dog.class);
        System.out.println(dog3.equals(dog4));
        System.out.println(dog4);
    }
}
