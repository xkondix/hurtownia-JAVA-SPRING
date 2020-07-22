package com.kowalczyk.hurtownia.model.services.employees;

import com.kowalczyk.hurtownia.model.entities.employees.JobPosition;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionRepository;
import com.kowalczyk.hurtownia.model.representationModel.employees.JobPositionRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionRestModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobPositionService {

    private final JobPositionRepository jobPositionRepository;


    public JobPositionService(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    public List<JobPositionRepresentationModel> getAll() {

        return jobPositionRepository.findAll().stream()
                .map(job -> new JobPositionRepresentationModel(job))
                .collect(Collectors.toList());


    }

    public JobPositionRepresentationModel getById(Long id) {

        Optional<JobPosition> jobPosition = jobPositionRepository.findById(id);
        return jobPosition.isPresent() ? new JobPositionRepresentationModel(jobPosition.get()) : null;

    }

    public void save(JobPositionRestModel jobPositionRestModel) {
        jobPositionRepository.save(jobPositionRestModel.mapToEntity());
    }
}
