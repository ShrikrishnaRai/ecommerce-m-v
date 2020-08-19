package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.resource;

import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.service.TaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskHistoryTransaction")
public class TaskHistoryResource {

    @Autowired
    private TaskHistoryService taskHistoryService;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TaskHistoryResponseDTO>>> getListOfTaskHistories(@RequestHeader(required = false,value = "Authorization") String Authorization,
                                                                                                  @RequestParam(defaultValue = "0",required =false,value = "page")int page,
                                                                                                  PagedResourcesAssembler<TaskHistoryResponseDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(taskHistoryService.getAllTaskHistory(page)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskHistoryResponseDTO> getTaskHistoryById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                     @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskHistoryService.getTaskHistoryById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveTaskHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                  @RequestBody TaskHistoryDTO taskHistoryDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskHistoryService.saveTaskHistory(taskHistoryDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskHistoryResponseDTO> updateTaskHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                                    @PathVariable long id,
                                                                    @RequestBody TaskHistoryDTO taskHistoryDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskHistoryService.updateTaskHistoryById(id, taskHistoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskHistory(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                                    @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(taskHistoryService.deleteTaskHistoryById(id));
    }
}
