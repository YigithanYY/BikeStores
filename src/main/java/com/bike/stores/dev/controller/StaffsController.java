package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.StaffsDto;
import com.bike.stores.dev.service.StaffsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/staffs")
public class StaffsController {

    private final StaffsService staffsService;

    // Applying dependency injection using constructor injection
    public StaffsController(StaffsService staffsService) {
        this.staffsService = staffsService;
    }

    /**
     * Retrieves the staff with the specified staffId.
     *
     * @param staffId Identifier of the staff to retrieve
     * @return DTO with information of the specified staff and HTTP Status 200 OK
     */
    @GetMapping("/{staffId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StaffsDto> getStaffById(@PathVariable int staffId) {
        return ResponseEntity.ok(staffsService.getStaffsById(staffId));
    }

    /**
     * Retrieves all staffs.
     *
     * @return List of DTOs with information of all staffs and HTTP Status 200 OK
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<StaffsDto>> getAllStaffs() {
        return ResponseEntity.ok(staffsService.getAllStaffs());
    }

    /**
     * Creates a new staff.
     *
     * @param staffsDto DTO containing information for the new staff
     * @return DTO with information of the created staff and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StaffsDto> createStaffs(@RequestBody StaffsDto staffsDto) {
        StaffsDto createdStaffs = staffsService.createStaffs(staffsDto);
        return new ResponseEntity<>(createdStaffs, HttpStatus.CREATED);
    }

    /**
     * Updates the staff with the specified staffId.
     *
     * @param staffId   Identifier of the staff to update
     * @param staffsDto DTO containing the new information
     * @return DTO with information of the updated staff and HTTP Status 200 OK
     */
    @PutMapping("/update/{staffId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StaffsDto> updateStaffs(@PathVariable int staffId, @RequestBody StaffsDto staffsDto) {
        StaffsDto updatedStaffs = staffsService.updateStaffs(staffId, staffsDto);
        return new ResponseEntity<>(updatedStaffs, HttpStatus.OK);
    }

    /**
     * Deletes the staff with the specified staffId.
     *
     * @param staffId Identifier of the staff to delete
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{staffId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StaffsDto> deleteStaffs(@PathVariable int staffId) {
        staffsService.deleteStaffs(staffId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
