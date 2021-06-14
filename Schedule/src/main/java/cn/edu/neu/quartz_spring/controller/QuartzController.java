package cn.edu.neu.quartz_spring.controller;

import cn.edu.neu.quartz_spring.common.http.GenericResponse;
import cn.edu.neu.quartz_spring.common.http.ResponseCode;
import cn.edu.neu.quartz_spring.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 32098
 */
@RestController
@RequestMapping("/quartz/test")
public class QuartzController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("job/newTimePrintJob")
    public GenericResponse<Boolean> newTimePrintJob(@RequestParam("name") String name, @RequestParam("cron") String cron) {
        Boolean data = scheduleService.newTimePrintJob(name, cron);
        return new GenericResponse<Boolean>().setResult("success").setCode(ResponseCode.SUCCESS.getCode()).setData(data);
    }

    @GetMapping("job/newSpiderJob")
    public GenericResponse<Boolean> newSpiderJob(@RequestParam("interval") String interval) {
        Boolean data = scheduleService.newSpiderJob(interval);
        return new GenericResponse<Boolean>().setResult("success").setCode(ResponseCode.SUCCESS.getCode()).setData(data);
    }

    @GetMapping("job/modify")
    public GenericResponse<Boolean> modifyJob(@RequestParam("name") String name, @RequestParam("cron") String cron) {
        Boolean data = scheduleService.modifyJob(name, cron);
        return new GenericResponse<Boolean>().setResult("success").setCode(ResponseCode.SUCCESS.getCode()).setData(data);
    }

    @GetMapping("job/pause")
    public GenericResponse<Boolean> newJob(@RequestParam("name") String name) {
        Boolean data = scheduleService.pauseJob(name);
        return new GenericResponse<Boolean>().setResult("success").setCode(ResponseCode.SUCCESS.getCode()).setData(data);
    }

    @GetMapping("job/resume")
    public GenericResponse<Boolean> resumeJob(@RequestParam("name") String name) {
        Boolean data = scheduleService.resumeJob(name);
        return new GenericResponse<Boolean>().setResult("success").setCode(ResponseCode.SUCCESS.getCode()).setData(data);
    }

    @GetMapping("job/remove")
    public GenericResponse<Boolean> removeJob(@RequestParam("name") String name) {
        Boolean data = scheduleService.removeJob(name);
        return new GenericResponse<Boolean>().setResult("success").setCode(ResponseCode.SUCCESS.getCode()).setData(data);
    }
}
