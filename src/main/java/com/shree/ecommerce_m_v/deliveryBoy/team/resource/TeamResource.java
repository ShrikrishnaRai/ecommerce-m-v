package com.shree.ecommerce_m_v.deliveryBoy.team.resource;

import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamDTO;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamIdAndName;
import com.shree.ecommerce_m_v.deliveryBoy.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamResource {

    @Autowired
    private TeamService teamService;


    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TeamDTO>>> getListOfTeam(
            @RequestHeader(value = "Authorization",required = false) String Authorization,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "teamName",required = false,value = "sortBy")String sortBy,
            @RequestParam(defaultValue = "ASC",required = false,value = "orderBy")String orderBy,
            PagedResourcesAssembler<TeamDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(teamService.getListOfTeam(page,sortBy,orderBy)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                               @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(teamService.getTeamById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveTeam(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                           @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teamService.saveTeam(teamDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                              @PathVariable long id, @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(teamService.updateTeam(id, teamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@RequestHeader(value = "Authorization",required = false) String Authorization,
                                             @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(teamService.deleteTeam(id));
    }

    @GetMapping("/idAndName")
    public ResponseEntity<List<TeamIdAndName>> getTeamIdAndName(@RequestHeader(value = "Authorization",required = false) String Authorization) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(teamService.getTeamIdAndName());
    }
}
