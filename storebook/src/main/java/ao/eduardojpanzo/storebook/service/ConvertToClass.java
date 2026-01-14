package ao.eduardojpanzo.storebook.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertToClass implements IConvertToClass{
   ObjectMapper objectMapper = new ObjectMapper();

   @Override
   public <T> T getData(String json, Class<T> cl) {
        try {
         return objectMapper.readValue(json, cl);
      } catch (JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }
   
}
