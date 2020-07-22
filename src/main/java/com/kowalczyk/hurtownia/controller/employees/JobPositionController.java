package com.kowalczyk.hurtownia.controller.employees;

import com.kowalczyk.hurtownia.model.representationModel.JobPositionRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionEmployeeRestModel;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionRestModel;
import com.kowalczyk.hurtownia.model.services.employees.JobPositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class JobPositionController {

private final JobPositionService jobPositionService;

    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("positions")
    public List<JobPositionRepresentationModel> getPositions()
    {
        return jobPositionService.getAll();
    }


    @GetMapping("position/{id}")
    public JobPositionRepresentationModel getById(@PathVariable Long id)
    {
        return jobPositionService.getById(id);
    }

    @PostMapping("position")
    public void saveJob(@RequestBody JobPositionRestModel jobPositionRestModel)
    {
        jobPositionService.save(jobPositionRestModel);
    }
}
