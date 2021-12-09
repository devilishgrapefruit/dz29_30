package com.dz;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.*;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String strTest1 = "Мама мыла-мыла-мыла раму!";
        String strTest2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.";
        BufferedReader bf = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(strTest1.getBytes()), "UTF-8"));
        Stream <String> stream = bf.lines();
        List <String> words = stream.map(String::toLowerCase) // строку в нижний регистр
                .map(s -> s.split("[\\pP\s&&[^']]+")) // убираем разделители
                .flatMap(Arrays::stream) // выравнивание списка элементов
                .collect(Collectors.toList()); // собираем в List
        Map<String, Long> result = //
                words.stream().collect( // преобразовываем map в stream и собираем
                        Collectors.groupingBy( // группируем
                                s -> s, Collectors.counting() // по кол-ву вхождений
                        )
                );
        result.entrySet().stream() // преобразовываем в stream
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed()) // сортируем по убыванию
                .limit(10).forEach(e -> System.out.println(e.getKey())); // выводим
    }
}

