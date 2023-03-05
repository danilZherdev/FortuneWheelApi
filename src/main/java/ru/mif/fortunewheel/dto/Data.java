package ru.mif.fortunewheel.dto;

import ru.mif.fortunewheel.domain.PersistentObject;

import java.lang.reflect.InvocationTargetException;

public abstract class Data<ENTITY extends PersistentObject> {
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

          } catch (NoSuchMethodException e) {

          } catch (IllegalAccessException e) {

          } catch (InvocationTargetException e) {

          }
          return object;
     }
}
