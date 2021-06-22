package com.jetbone.others.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;

/**
 * @author Chris
 * @date 2021-06-22
 */
public class Test {

    private static final String FILE_PATH = "./tmp/Serial";

    public static void main(String[] args) throws Exception {

//        SerialClass serialClass = new SerialClass();
//
//        serialClass.setName("serial class 1");
//        serialClass.setAge(10);
//        serialClass.setGender("female");

//        kryoSerializeObject(serialClass);
//        System.out.println("successful serial jdk object to file");

        System.out.println("=======");
        SerialClass result = (SerialClass) kryoDeserializeObject();
        System.out.println(result);

    }

    private static void jdkSerializeObject(SerialInterface serial) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH + "/jdkObject.jdk");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(serial);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private static SerialInterface jdkDeserializeObject() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH + "jdkObject.jdk"));
        var result = (SerialInterface) objectInputStream.readObject();
        objectInputStream.close();

        return result;
    }

    private static void kryoSerializeObject(SerialInterface serial) throws Exception {
        Kryo kryo  = new Kryo();
        Output output = new Output(new FileOutputStream(FILE_PATH + "/kryoObject.kryo"));
        kryo.writeObject(output, serial);
        output.flush();
        output.close();
    }

    private static SerialInterface kryoDeserializeObject() throws Exception {
        Input input = new Input(new FileInputStream(FILE_PATH + "/kryoObject.kryo"));
        Kryo kryo = new Kryo();
        var result = kryo.readObject(input, SerialClass.class);
        input.close();

        return result;
    }

}
