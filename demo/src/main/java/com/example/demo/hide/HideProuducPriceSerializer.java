package com.example.demo.hide;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class HideProuducPriceSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
      //  jsonGenerator.writeObject("*******");
        if(bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) == 0){
            jsonGenerator.writeObject("*******");//将Java对象序列化为JSON数据并写入输出流
           // serializerProvider.defaultSerializeValue("******",jsonGenerator); //是将Java对象序列化为JSON字符串的方法，它返回一个字符串结果
        }else{
            jsonGenerator.writeObject(bigDecimal);
           // serializerProvider.defaultSerializeValue(bigDecimal,jsonGenerator);
        }

    }
}
