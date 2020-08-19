package com.shree.ecommerce_m_v.deliveryBoy.task.resource;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskDTO;
import com.shree.ecommerce_m_v.deliveryBoy.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TaskDTO>>> getListOfTask(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                          @RequestParam(defaultValue = "0", required = false,value = "page")int page,
                                                                          PagedResourcesAssembler<TaskDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK).
                body(assembler.toModel(taskService.getAllTasks(page)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                               @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<Long> saveTask(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                         @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.saveTask(taskDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                              @PathVariable long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.updateTask(id, taskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                             @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(taskService.deleteTaskById(id));
    }
}
