package org.backend.onlinejudge.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.backend.onlinejudge.DTO.PageDTO;
import org.backend.onlinejudge.DTO.ProblemDTO;
import org.backend.onlinejudge.Exception.AppException;
import org.backend.onlinejudge.Query.ProblemQuery;
import org.backend.onlinejudge.Service.ProblemService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@PropertySource("classpath:message.properties")
@RequestMapping("problem")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }


    @GetMapping("/all")
    public PageDTO<ProblemDTO> getProblems(
            ProblemQuery problemQuery ,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size)
            throws JsonProcessingException {
        return problemService.findProblems(problemQuery ,page , size);
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<ProblemDTO> createProblem(@Validated @RequestBody ProblemDTO problemDTO) throws
            AppException, JsonProcessingException
    {
        ProblemDTO response = problemService.create(problemDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<ProblemDTO> updateProblem(@Validated @RequestBody ProblemDTO problemDTO, @PathVariable String id)
            throws AppException, JsonProcessingException {
        problemDTO.setProblemId(id);
        ProblemDTO response = problemService.update(problemDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ProblemDTO deleteProblem(@PathVariable String id) throws AppException {
        return problemService.delete(id);
    }






}
