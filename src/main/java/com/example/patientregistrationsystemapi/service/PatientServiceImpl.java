package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.PatientRequest;
import com.example.patientregistrationsystemapi.exception.PatientNotFoundException;
import com.example.patientregistrationsystemapi.model.Patient;
import com.example.patientregistrationsystemapi.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Получить информацию о пациенте по его идентификатору.
     *
     * @param patientId Идентификатор пациента.
     * @return Информация о пациенте.
     * @throws PatientNotFoundException если пациент с указанным идентификатором не найден.
     */
    @Override
    public Patient getPatientById(Long patientId) {
        log.info("Fetching patient with ID: {}", patientId);
        return patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found with ID " + patientId));
    }

    /**
     * Получить список всех пациентов.
     *
     * @return Список всех пациентов.
     */
    @Override
    public List<Patient> getAllPatient() {
        log.info("Fetching all patients");
        return patientRepository.findAll();
    }

    /**
     * Удалить пациента по его идентификатору.
     *
     * @param id Идентификатор пациента.
     * @throws PatientNotFoundException если пациент с указанным идентификатором не найден.
     */
    @Override
    public void deletePatientById(Long id) {
        Patient patient = getPatientById(id);
        log.info("Deleting patient with ID: {}", id);
        patientRepository.delete(patient);
    }

    /**
     * Удалить талоны по полному имени пациента.
     *
     * @param fullName Полное имя пациента.
     */
    @Override
    public void deletePatientByFullName(String fullName) {
        log.info("Deleting patients by full name: {}", fullName);
        patientRepository.deleteByFullName(fullName);
    }

    /**
     * Сохранить информацию о новом пациенте.
     *
     * @param patientRequest Запрос с данными о пациенте.
     * @return Сохраненная информация о пациенте.
     */
    @Override
    public Patient savePatient(PatientRequest patientRequest) {
        log.info("Saving new patient: {}", patientRequest.getFullName());
        Patient patient = new Patient();
        patient.setUuid(patientRequest.getUuid());
        patient.setFullName(patientRequest.getFullName());
        patient.setDateOfBirth(patientRequest.getDateOfBirth());
        return patientRepository.save(patient);
    }

    /**
     * Обновить информацию о пациенте по его идентификатору.
     *
     * @param id            Идентификатор пациента.
     * @param patientRequest Запрос с обновленными данными о пациенте.
     * @return Обновленная информация о пациенте.
     * @throws PatientNotFoundException если пациент с указанным идентификатором не найден.
     */
    @Override
    public Patient updatePatient(Long id, PatientRequest patientRequest) {
        log.info("Updating patient with ID: {}", id);
        Patient existingPatient = getPatientById(id);
        existingPatient.setUuid(patientRequest.getUuid());
        existingPatient.setFullName(patientRequest.getFullName());
        existingPatient.setDateOfBirth(patientRequest.getDateOfBirth());
        return patientRepository.save(existingPatient);
    }
}
