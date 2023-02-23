package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.entities.Teacher;
import com.uniovi.notaneitor.services.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddTeacherFormValidator implements Validator{

    @Autowired
    private TeachersService teachersService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Teacher.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Teacher teacher = (Teacher) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");

        if(!checkDni(teacher.getDni())){
            errors.rejectValue("dni", "Error.teacher.add.dni");
        }
        if(teachersService.getTeacherByDni(teacher.getDni()) != null){
            errors.rejectValue("dni", "Error.signup.dni.duplicate");
        }
        if (teacher.getName().length() < 5 || teacher.getName().length() > 24) {
            errors.rejectValue("name", "Error.teacher.name.length");
        }
        if (teacher.getSurname().length() < 5 || teacher.getSurname().length() > 24) {
            errors.rejectValue("surname", "Error.teacher.surname.length");
        }
        if (teacher.getCategory().length() < 5 || teacher.getCategory().length() > 24) {
            errors.rejectValue("category", "Error.teacher.category.length");
        }

    }

    private boolean checkDni(String dni){
        if(dni.length() != 9){
            return false;
        }
        if(!Character.isAlphabetic(dni.charAt(dni.length() - 1))){
            return false;
        }
        return true;
    }
}
