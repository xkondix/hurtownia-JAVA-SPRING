package com.kowalczyk.hurtownia.controller.employees;

import com.kowalczyk.hurtownia.model.representationModel.employees.JobPositionRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.employees.JobPositionRestModel;
import com.kowalczyk.hurtownia.model.services.employees.JobPositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<JobPositionRepresentationModel>> getPositions()
    {
        return ResponseEntity.ok(jobPositionService.getAll());
    }


    @GetMapping("position/{id}")
    public ResponseEntity<JobPositionRepresentationModel> getById(@PathVariable Long id)
    {

        JobPositionRepresentationModel jobPositionRepresentationModel
                = jobPositionService.getById(id);
        if(jobPositionRepresentationModel==null)
        {
            return new ResponseEntity<JobPositionRepresentationModel>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(jobPositionRepresentationModel);

    }

    @PostMapping("position")
    public ResponseEntity<?> saveJob(@RequestBody JobPositionRestModel jobPositionRestModel)
    {
        try{
            jobPositionService.save(jobPositionRestModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);

        }
    }




}
