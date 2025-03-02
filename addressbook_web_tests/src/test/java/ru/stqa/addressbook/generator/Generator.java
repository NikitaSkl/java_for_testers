package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.Contact;
import ru.stqa.addressbook.model.Group;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {
    @Parameter(names = {"--type","-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String outputFile;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-c"})
    int count;
    public static void main(String[] args) throws IOException {
        var generator=new Generator();
        JCommander.newBuilder()
                .addObject(generator)//здесь описано, что создается парсер командной строки, который будет анализировать параметры описанные в объекте generator, с последующей передачей args парсеру
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data=generate();
        save(data);
    }

    private Object generate() {
        if ("group".equals(type)){
            return generateGroups();
        }
        else if ("contact".equals(type)){
            return generateContacts();
        }
        else {
            throw new IllegalArgumentException("Неизвестный тип данных "+type);
        }
    }

    private Object generateGroups() {
        var result=new ArrayList<Group>();
        for (int i = 0; i < count; i++) {
            result.add(new Group().withName(CommonFunctions.randomString(i*10))
                    .withFooter(CommonFunctions.randomString(i*10))
                    .withHeader(CommonFunctions.randomString(i*10)));
        }
        return result;
    }
    private Object generateContacts() {
        var result=new ArrayList<Contact>();
        for (int i = 0; i < count; i++) {
            result.add(new Contact().withFirstName(CommonFunctions.randomString(i * 3))
                    .withLastName(CommonFunctions.randomString(i * 3))
                    .withMobile(CommonFunctions.randomStringOfNumbers(i * 5))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
        }
        return result;
    }
    private void save(Object data) throws IOException {
        if ("json".equals(format)){
            var mapper = new ObjectMapper(); //часть кода из README библиотеки
            mapper.enable(SerializationFeature.INDENT_OUTPUT); //включили для мэппера pretty-printing
            // mapper.writeValue(new File(outputFile), data); название файла выведено в переменную outputFile, данные для записи data передаются в параметр метода save
            var json=mapper.writeValueAsString(data);

            try (var writer=new FileWriter(outputFile)){
                writer.write(json);
            }
        }
        else if ("yaml".equals(format)){
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(outputFile),data);

        }
        else if ("xml".equals(format)){
            var mapper = new XmlMapper();
            mapper.writeValue(new File(outputFile),data);

        }
        else {
            throw new IllegalArgumentException("Неизвестный формат данных "+format);
        }
    }
}
