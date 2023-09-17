package com.example.patientregistrationsystemapi.exception;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(String s) {
        super(s);
    }
}
