package org.backend.judgeService.Controller;

import org.backend.judgeService.DTO.SubmissionRequest;
import org.backend.judgeService.DTO.SubmissionResponse;
import org.backend.judgeService.Service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/judge")
public class JudgeController {


    private final JudgeService judgeService;

    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @GetMapping("/status")
    public String status(){
        return "Judge working in Docker";
    }

    @PostMapping("/submit")
    public ResponseEntity<SubmissionResponse> submitCode(@RequestBody SubmissionRequest submissionRequest) {
        try {
            SubmissionResponse submissionResponse = judgeService.executeCode(submissionRequest);

            return ResponseEntity.ok(submissionResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
