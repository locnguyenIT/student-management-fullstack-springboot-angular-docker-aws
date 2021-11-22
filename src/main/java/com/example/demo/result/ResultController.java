package com.example.demo.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/spring-boot/result")
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public List<Result> getAllResult()
    {
        return resultService.getAllResult();
    }

    @GetMapping(path = "/get/{resultId}")
    public Result getOneEnrolment(@PathVariable("resultId") Integer resultId)
    {
        return resultService.getOneResult(resultId);
    }

    @GetMapping(path = "/get/grade/{grade}")
    public List<Result> getResultByGradeGreaterThanEqual(@PathVariable("grade") Integer grade)
    {
        return resultService.getResultByGradeGreaterThanEqual(grade);
    }

    @PostMapping(path = "/add/studentId/{studentId}/courseId/{courseId}")
    public void addResult(@RequestBody Result result,
                          @PathVariable("studentId") Integer studentId,
                          @PathVariable("courseId") Integer courseId)
    {
        resultService.addResult(result,studentId,courseId);
    }
    @DeleteMapping(path = "/delete/studentId/{studentId}/courseId/{courseId}")
    public void deleteResult(@PathVariable("studentId") Integer studentId,
                                @PathVariable("courseId") Integer courseId)
    {
        resultService.deleteResult(studentId,courseId);
    }

    @PutMapping(path = "/update/studentId/{studentId}/courseId/{courseId}/grade") //http://localhost:8080/api/spring-boot/enrolment/update/"enrolmentId"?studentId="id"&courseId="";
    public void updateResult(@PathVariable("studentId") Integer studentId,
                                @PathVariable("courseId") Integer courseId,
                                @RequestParam(required = false) Integer grade)
    {
        resultService.updateResult(studentId,courseId,grade);
    }
}
