package ru.mif.fortunewheel.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mif.fortunewheel.domain.PersistentObject;

import java.lang.reflect.InvocationTargetException;

public abstract class Data<ENTITY extends PersistentObject> {

     private final Logger logger = LoggerFactory.getLogger(this.getClass());

     /**
      * Transform Entity object to DTO object.
      * @param entity - entity
      * @return dto object
      */
     public Data(ENTITY entity) {}

     protected Data<ENTITY> create(Class<Data<ENTITY>> clazz) {
          Data<ENTITY> object = null;
          try {
               object = clazz.getDeclaredConstructor().newInstance();
          } catch (InstantiationException e) {
               logger.error("InstantiationException in ");
               e.printStackTrace();
          } catch (NoSuchMethodException e) {
               logger.error("NoSuchMethodException in ");
               e.printStackTrace();
          } catch (IllegalAccessException e) {
               logger.error("IllegalAccessException in ");
               e.printStackTrace();
          } catch (InvocationTargetException e) {
               logger.error("InvocationTargetException in ");
               e.printStackTrace();
          }
          return object;
     }
}
