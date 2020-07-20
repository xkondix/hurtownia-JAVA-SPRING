package com.kowalczyk.hurtownia.model.services;

import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployee;
import com.kowalczyk.hurtownia.model.repositories.employees.EmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionEmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionRepository;
import com.kowalczyk.hurtownia.model.responses.JobPositionEmployeeRestModel;
import org.springframework.stereotype.Service;

@Service
public class JobPositionEmployeeService {

    private final JobPositionEmployeeRepository jobPositionEmployeeRepository;
    private final JobPositionRepository jobPositionRepository;
    private final EmployeeRepository employeeRepository;

    public JobPositionEmployeeService(JobPositionEmployeeRepository jobPositionEmployeeRepository, JobPositionRepository jobPositionRepository, EmployeeRepository employeeRepository) {
        this.jobPositionEmployeeRepository = jobPositionEmployeeRepository;
        this.jobPositionRepository = jobPositionRepository;
        this.employeeRepository = employeeRepository;
    }

    public void save(JobPositionEmployeeRestModel jobPositionEmployeeRestModel)
    {
        jobPositionEmployeeRepository.save(mapToRest(jobPositionEmployeeRestModel));
    }

    public JobPositionEmployee mapToRest(JobPositionEmployeeRestModel job)
    {
        return new JobPositionEmployee(job.getDataOfStart()
                ,job.getDataOfEnd(),job.getReward()
                ,jobPositionRepository.findById(job.getPosition()).get()
                ,employeeRepository.findById(job.getEmployee()).get());
    }

}
