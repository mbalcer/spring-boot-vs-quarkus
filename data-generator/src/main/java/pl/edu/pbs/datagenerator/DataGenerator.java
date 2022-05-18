package pl.edu.pbs.datagenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {
    private FakerMethod fakerMethod;

    public DataGenerator() {
        this.fakerMethod = new FakerMethod();
    }

    public List<String> generate(Integer rows, Integer columns, String separator) {
        Map<String, Map<Object, Method>> fakerMethodMap = fakerMethod.generateFakerMethodMap();

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
