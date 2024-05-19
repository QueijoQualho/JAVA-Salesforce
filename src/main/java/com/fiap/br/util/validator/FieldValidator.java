package com.fiap.br.util.validator;

import java.lang.reflect.Field;

import com.fiap.br.util.annotations.Required;

public class FieldValidator {
    public static <T> boolean requiredFieldsFilled(T object) throws IllegalAccessException {
        boolean allFilled = true;
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Required.class)) {
                field.setAccessible(true);
                if (field.get(object) == null) {
                    allFilled = false;
                }

            }
        }

        return allFilled;
    }
}
