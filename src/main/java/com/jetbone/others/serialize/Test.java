package com.jetbone.others.serialize;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;

/**
 * @author Chris
 * @date 2021-06-22
 */
public class Test {

    private static final String FILE_PATH = "/Users/chrischan/Desktop/tmp/Serial";

    public static void main(String[] args) throws Exception {

        SerialClass serialObject = new SerialClass();

        serialObject.setName("serial class 1");
        serialObject.setAge(10);
        serialObject.setGender("female");
        serialObject.setAddress("address");
        serialObject.setEmail("email");

//        jdkSerializeObject(serialObject);
//        kryoSerializeObject(serialObject);
        hessian2SerializeObject(serialObject);
        System.out.println("successful serial jdk object to file");

        System.out.println("=======");
//        SerialClass result = (SerialClass) jdkDeserializeObject();
//        SerialClass result = (SerialClass) kryoDeserializeObject();
        SerialClass result = (SerialClass) hessian2DeserializeObject();
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
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH + "/jdkObject.jdk"));
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

    private static void hessian2SerializeObject(SerialInterface serial) throws Exception {
        Hessian2Output hessian2Output = new Hessian2Output(new FileOutputStream(FILE_PATH + "/hessian2Object.hessian"));
        hessian2Output.writeObject(serial);
        hessian2Output.flush();
        hessian2Output.close();
    }

    private static SerialInterface hessian2DeserializeObject() throws Exception {
        Hessian2Input hessian2Input = new Hessian2Input(new FileInputStream(FILE_PATH + "/hessian2Object.hessian"));
        var result = (SerialInterface) hessian2Input.readObject();

        return result;
    }

}
