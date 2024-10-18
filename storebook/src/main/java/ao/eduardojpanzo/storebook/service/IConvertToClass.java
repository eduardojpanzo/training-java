package ao.eduardojpanzo.storebook.service;


public interface IConvertToClass {
   public <T> T getData(String json, Class<T> cl);
}
