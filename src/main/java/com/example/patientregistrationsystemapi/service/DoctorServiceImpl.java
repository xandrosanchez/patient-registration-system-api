package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.DoctorRequest;
import com.example.patientregistrationsystemapi.exception.DoctorNotFoundException;
import com.example.patientregistrationsystemapi.model.Doctor;
import com.example.patientregistrationsystemapi.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final TicketService ticketService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, TicketService ticketService) {
        this.doctorRepository = doctorRepository;
        this.ticketService = ticketService;
    }

    /**
     * Получить информацию о враче по его идентификатору.
     *
     * @param doctorId Идентификатор врача.
     * @return Информация о враче.
     * @throws DoctorNotFoundException если врач с указанным идентификатором не найден.
     */
    @Override
    public Doctor getDoctorById(Long doctorId) {
        log.info("Fetching doctor with ID: {}", doctorId);
        return doctorRepository.findById(doctorId).orElseThrow(() ->
                new DoctorNotFoundException("Doctor not found with ID " + doctorId));
    }

    /**
     * Получить список всех врачей.
     *
     * @return Список всех врачей.
     */
    @Override
    public List<Doctor> getAllDoctor() {
        log.info("Fetching all doctors");
        return doctorRepository.findAll();
    }

    /**
     * Удалить врача по его идентификатору.
     * Также удаляются все талоны, связанные с этим врачом.
     *
     * @param id Идентификатор врача.
     */
    @Override
    public void deleteDoctorById(Long id) {
        Doctor doctor = getDoctorById(id);
        log.info("Deleting doctor with ID: {}", id);
        ticketService.deleteTicketByDoctorId(id);
        doctorRepository.delete(doctor);
    }

    /**
     * Удалить талоны по полному имени врача.
     *
     * @param fullName Полное имя врача.
     */
    @Override
    public void deleteTicketByFullName(String fullName) {
        log.info("Deleting tickets by full name: {}", fullName);
        Doctor doctor = doctorRepository.findByFullName(fullName);
        if (doctor != null) {
            ticketService.deleteTicketByDoctorId(doctor.getId());
        }
    }

    /**
     * Сохранить информацию о враче.
     *
     * @param doctorRequest Запрос с данными о враче.
     * @return Сохраненная информация о враче.
     */
    @Override
    public Doctor saveDoctor(DoctorRequest doctorRequest) {
        log.info("Saving new doctor: {}", doctorRequest.getFullName());
        Doctor doctor = new Doctor();
        doctor.setUuid(doctorRequest.getUuid());
        doctor.setFullName(doctorRequest.getFullName());
        return doctorRepository.save(doctor);
    }

    /**
     * Обновить информацию о враче по его идентификатору.
     *
     * @param id            Идентификатор врача.
     * @param doctorRequest Запрос с обновленными данными о враче.
     * @return Обновленная информация о враче.
     */
    @Override
    public Doctor updateDoctor(Long id, DoctorRequest doctorRequest) {
        log.info("Updating doctor with ID: {}", id);
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setUuid(doctorRequest.getUuid());
        existingDoctor.setFullName(doctorRequest.getFullName());
        return doctorRepository.save(existingDoctor);
    }
}
