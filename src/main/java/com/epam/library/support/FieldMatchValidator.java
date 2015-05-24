package com.epam.library.support;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
  private String firstFieldName;
  private String secondFieldName;
  private String message;

  @Override
  public void initialize(final FieldMatch constraintAnnotation) {
    firstFieldName = constraintAnnotation.first();
    secondFieldName = constraintAnnotation.second();
    message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    try {
      Field f = value.getClass().getDeclaredField(firstFieldName);
      f.setAccessible(true);
      final Object firstObj = f.get(value);

      f = value.getClass().getDeclaredField(secondFieldName);
      f.setAccessible(true);
      final Object secondObj = f.get(value);

      if (firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj)) return true;
    } catch (final Exception ignore) {
      // ignore
    }
    context
        .buildConstraintViolationWithTemplate(message)
        .addPropertyNode(secondFieldName)
        .addConstraintViolation();
    return false;
  }
}
