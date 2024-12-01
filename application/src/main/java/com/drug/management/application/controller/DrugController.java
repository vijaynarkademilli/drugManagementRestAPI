package com.drug.management.application.controller;


import com.drug.management.application.model.Drug;
import com.drug.management.application.service.DrugService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DrugController {
    private final DrugService drugService;

    @GetMapping
    public ResponseEntity<Page<Drug>> getAllDrugs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(drugService.getAllDrugs(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drug> getDrugById(@PathVariable Long id) {
        return ResponseEntity.ok(drugService.getDrugById(id));
    }

    @PostMapping
    public ResponseEntity<Drug> createDrug(@Valid @RequestBody Drug drug) {
        return ResponseEntity.ok(drugService.createDrug(drug));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drug> updateDrug(@PathVariable Long id, @Valid @RequestBody Drug drug) {
        return ResponseEntity.ok(drugService.updateDrug(id, drug));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable Long id) {
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }
}