package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.StaffsDto;
import com.bike.stores.dev.exceptions.StaffsNotFoundException;
import com.bike.stores.dev.model.Staffs;
import com.bike.stores.dev.repository.StaffsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffsService {

    private final StaffsRepository staffsRepository;

    private final PasswordEncoder passwordEncoder;

    public StaffsService(StaffsRepository staffsRepository, PasswordEncoder passwordEncoder) {
        this.staffsRepository = staffsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves Staffs by ID.
     *
     * @param id The ID of the Staffs to be retrieved.
     * @return The Staffs DTO.
     * @throws StaffsNotFoundException if no Staffs are found for the given ID.
     */
    public StaffsDto getStaffsById(int id) {
        Staffs staffs = staffsRepository.findById(id)
                .orElseThrow(() -> new StaffsNotFoundException("Staffs couldn't be found by id:" + id));
        return mapToStaffsDto(staffs);
    }

    /**
     * Retrieves all Staffs.
     *
     * @return List of Staffs DTOs.
     */
    public List<StaffsDto> getAllStaffs() {
        List<Staffs> staffsList = staffsRepository.findAll();
        return staffsList.stream()
                .map(this::mapToStaffsDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new Staffs.
     *
     * @param staffsDto The DTO representing the Staffs to be created.
     * @return The created Staffs DTO.
     */
    public StaffsDto createStaffs(StaffsDto staffsDto) {

        Optional<Staffs> optionalStaffs = staffsRepository.findByEmail(staffsDto.geteMail());
        if (optionalStaffs.isPresent()) {
            throw new StaffsNotFoundException("This email address is already registered");
        }

        String hashedPassword = passwordEncoder.encode(staffsDto.getPassword());
        staffsDto.setPassword(hashedPassword);
        Staffs newStaff = mapToStaffsEntity(staffsDto);
        Staffs savedStaff = staffsRepository.save(newStaff);
        return mapToStaffsDto(savedStaff);
    }

    /**
     * Updates an existing Staffs.
     *
     * @param id        The ID of the Staffs to be updated.
     * @param staffsDto The DTO representing the updated Staffs.
     * @return The updated Staffs DTO.
     * @throws StaffsNotFoundException if no Staffs are found for the given ID.
     */
    public StaffsDto updateStaffs(int id, StaffsDto staffsDto) {
        Staffs existingStaff = staffsRepository.findById(id)
                .orElseThrow(() -> new StaffsNotFoundException("Staffs couldn't be found by id:" + id));

        // Update existingStaff fields with staffsDto fields
        existingStaff.setFirstName(staffsDto.getFirstName());
        existingStaff.setLastName(staffsDto.getLastName());
        existingStaff.seteMail(staffsDto.geteMail());
        existingStaff.setPhone(staffsDto.getPhone());
        existingStaff.setActive(staffsDto.getActive());
        existingStaff.setStoreId(staffsDto.getStoreId());
        existingStaff.setManagerId(staffsDto.getManagerId());

        Staffs updatedStaff = staffsRepository.save(existingStaff);
        return mapToStaffsDto(updatedStaff);
    }

    /**
     * Deletes an existing Staffs.
     *
     * @param id The ID of the Staffs to be deleted.
     * @throws StaffsNotFoundException if no Staffs are found for the given ID.
     */
    public void deleteStaffs(int id) {
        if (!staffsRepository.existsById(id)) {
            throw new StaffsNotFoundException("Staffs couldn't be found by id:" + id);
        }

        staffsRepository.deleteById(id);
    }

    /**
     * Maps Staffs entity to StaffsDto.
     *
     * @param staffs The Staffs entity.
     * @return The StaffsDto.
     */
    private StaffsDto mapToStaffsDto(Staffs staffs) {
        return new StaffsDto(
                staffs.getStaffId(),
                staffs.getFirstName(),
                staffs.getLastName(),
                staffs.geteMail(),
                staffs.getPhone(),
                staffs.getActive(),
                staffs.getStoreId(),
                staffs.getManagerId(),
                staffs.getPassword(),
                staffs.getRole()
        );
    }

    /**
     * Maps StaffsDto to Staffs entity.
     *
     * @param staffsDto The StaffsDto.
     * @return The Staffs entity.
     */
    private Staffs mapToStaffsEntity(StaffsDto staffsDto) {
        Staffs staffs = new Staffs();
        staffs.setFirstName(staffsDto.getFirstName());
        staffs.setLastName(staffsDto.getLastName());
        staffs.seteMail(staffsDto.geteMail());
        staffs.setPhone(staffsDto.getPhone());
        staffs.setActive(staffsDto.getActive());
        staffs.setStoreId(staffsDto.getStoreId());
        staffs.setManagerId(staffsDto.getManagerId());
        return staffs;
    }
}
