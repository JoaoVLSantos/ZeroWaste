package com.zerowaste.services.promotions;

import com.zerowaste.models.promotion.Promotion;
import com.zerowaste.repositories.PromotionsRepository;
import com.zerowaste.services.promotions.exceptions.PromotionNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DeletePromotionService {

    private PromotionsRepository promotionsRepository;

    public DeletePromotionService(PromotionsRepository promotionsRepository) {
        this.promotionsRepository = promotionsRepository;
    }

    public void execute(Long id) throws PromotionNotFoundException {

        Optional<Promotion> promotionOpt = promotionsRepository.findById(id);

        if (!promotionOpt.isPresent() || promotionOpt.get().getDeletedAt() != null)
            throw new PromotionNotFoundException("Promoção não encontrada!");

        Promotion promotion = promotionOpt.get();

        promotion.setDeletedAt(LocalDate.now());

        promotionsRepository.save(promotion);

    }

}
