/**
 * 
 */
package com.univers.architecture.transporter.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.univers.architecture.transporter.service.search.TaskExecutionSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univers.architecture.transporter.model.TaskExecution;
import com.univers.architecture.transporter.service.ITaskExecutionService;

/**
 * @author sabir
 *
 */
@RestController
@RequestMapping("/api")
public class TaskExecutionController {

	@Autowired
	private ITaskExecutionService taskExecutionService;

	@GetMapping("/taskExecution")
	public List<TaskExecution> getAllTaskExecutions(@RequestParam(value = "search") String search, Pageable pageable) {

		TaskExecutionSpecificationsBuilder builder = new TaskExecutionSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
             builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
		Specification<TaskExecution> spec = builder.build();
		return this.taskExecutionService.getAllTaskExecution(spec,pageable).getContent();
	}
}
