package pl.edu.pbs.datagenerator;

import com.github.javafaker.Faker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {
    private Faker faker;

    private Map<String, Map<Object, Method>> fakerMethodMap = new LinkedHashMap<>();

    public DataGenerator() {
        this.faker = new Faker(Locale.forLanguageTag("pl"));

        try {
            fakerMethodMap.put("ID",
                    Collections.singletonMap(this.faker.number(),
                            this.faker.number().getClass().getDeclaredMethod("randomNumber")));
            fakerMethodMap.put("First name",
                    Collections.singletonMap(this.faker.name(),
                    this.faker.name().getClass().getDeclaredMethod("firstName")));
            fakerMethodMap.put("Last name",
                    Collections.singletonMap(this.faker.name(),
                    this.faker.name().getClass().getDeclaredMethod("lastName")));
            fakerMethodMap.put("Username",
                    Collections.singletonMap(this.faker.name(),
                    this.faker.name().getClass().getDeclaredMethod("username")));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public List<String> generate(Integer rows, Integer columns, String separator) {
        List<String> titles = fakerMethodMap.keySet()
                .stream()
                .limit(columns)
                .collect(Collectors.toList());


        List<String> d = IntStream.range(0, rows)
                .mapToObj(i -> titles.stream()
                        .map(title -> {
                            try {
                                Map<Object, Method> objectMethodMap = fakerMethodMap.get(title);
                                Object object = objectMethodMap.keySet().stream().findFirst().get();

                                return objectMethodMap.get(object).invoke(object).toString();
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            return "";
                        })
                        .collect(Collectors.joining(separator))
                ).collect(Collectors.toList());

        List<String> data = new ArrayList<>();
        data.add(String.join(separator, titles));
        data.addAll(d);

        return data;
    }
}
