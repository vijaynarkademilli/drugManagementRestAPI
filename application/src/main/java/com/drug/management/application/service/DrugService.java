package com.drug.management.application.service;


import com.drug.management.application.model.Drug;
import com.drug.management.application.repository.DrugRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DrugService {
    private final DrugRepository drugRepository;

    public Page<Drug> getAllDrugs(Pageable pageable) {
        return drugRepository.findAll(pageable);
    }

    public Drug getDrugById(Long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Drug not found with id: " + id));
    }

    @Transactional
    public Drug createDrug(Drug drug) {
        return drugRepository.save(drug);
    }

    @Transactional
    public Drug updateDrug(Long id, Drug drug) {
        Drug existingDrug = getDrugById(id);
        drug.setId(existingDrug.getId());
        return drugRepository.save(drug);
    }

    @Transactional
    public void deleteDrug(Long id) {
        if (!drugRepository.existsById(id)) {
            throw new EntityNotFoundException("Drug not found with id: " + id);
        }
        drugRepository.deleteById(id);
    }
}