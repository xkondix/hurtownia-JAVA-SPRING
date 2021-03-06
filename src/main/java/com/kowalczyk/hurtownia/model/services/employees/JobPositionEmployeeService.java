package com.kowalczyk.hurtownia.model.services.employees;

import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployee;
import com.kowalczyk.hurtownia.model.entities.employees.JobPositionEmployeeId;
import com.kowalczyk.hurtownia.model.entities.employees.UserAccount;
import com.kowalczyk.hurtownia.model.repositories.employees.EmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionEmployeeRepository;
import com.kowalczyk.hurtownia.model.repositories.employees.JobPositionRepository;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionEmployeeRestModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void saveJob(JobPositionEmployeeRestModel jobPositionEmployeeRestModel)
    {
        JobPositionEmployee jobPositionEmployee = mapToEntiti(jobPositionEmployeeRestModel);
        jobPositionEmployeeRepository.save(jobPositionEmployee);
    }

    public void putJob(JobPositionEmployeeRestModel jobPositionEmployeeRestModel,Long employeeId,Long jobId)
    {
        JobPositionEmployee jobPositionEmployee = mapToEntiti(jobPositionEmployeeRestModel);
        jobPositionEmployee.setId(new JobPositionEmployeeId(employeeId,jobId));
        jobPositionEmployeeRepository.save(jobPositionEmployee);
    }

    public void patchJob(JobPositionEmployeeRestModel jobPositionEmployeeRestModel, Long employeeId,Long jobId)
    {
        Optional<JobPositionEmployee> jobPositionEmployee =
                jobPositionEmployeeRepository.findByEmployeeIdAndPositionId(employeeId,jobId);
        if(jobPositionEmployee.isPresent())
        {
            JobPositionEmployee job = jobPositionEmployee.get();
            job.setDataOfEnd(jobPositionEmployeeRestModel.getDataOfEnd());
            jobPositionEmployeeRepository.save(job);
        }


    }



    //methods
    private JobPositionEmployee mapToEntiti(JobPositionEmployeeRestModel job)
    {
        return new JobPositionEmployee(job.getDataOfStart()
                ,job.getDataOfEnd(),job.getReward()
                ,jobPositionRepository.findById(job.getPosition()).get()
                ,employeeRepository.findById(job.getEmployee()).get());
    }


}
