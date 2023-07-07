package com.vorova.gifts.dao.impl;

import com.vorova.gifts.dao.abstraction.GiftDao;
import com.vorova.gifts.exception.GiftException;
import com.vorova.gifts.exception.UserException;
import com.vorova.gifts.model.entity.Gift;
import com.vorova.gifts.model.entity.TagSearch;
import com.vorova.gifts.model.dto.FilterSearchDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftDaoImpl implements GiftDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public GiftDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long add(Gift gift) {
        try {
            entityManager.persist(gift);
        } catch (Exception e) {
            var exception = new GiftException();
            exception.addMessage("Сохранение не удалось");
            exception.addMessage(e.getMessage());
            throw exception;
        }
        return gift.getId();
    }

    @Override
    public void remove(Gift gift) {
        try {
            entityManager.remove(gift);
        } catch (Exception e) {
            GiftException exception = new GiftException();
            exception.addMessage("Не удалось удалить пользователя");
            exception.addMessage(e.getMessage());
            throw exception;
        }
    }

    @Override
    public void update(Gift gift) {
        try {
            Gift gift1 = entityManager.find(Gift.class, gift.getId());
            gift.setDateAdded(gift1.getDateAdded());
            gift.setType(gift1.getType());
        } catch (Exception e) {
            GiftException exception = new GiftException();
            exception.addMessage("Обновление не удалось");
            throw exception;
        }
        entityManager.merge(gift);
    }

    @Override
    public Optional<Gift> getById(long id) {
        try {
            return Optional.of(entityManager.find(Gift.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Gift> getByFilter(FilterSearchDto filter) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gift> query = builder.createQuery(Gift.class);

        Root<Gift> gift = query.from(Gift.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTitle() != null) {
            predicates.add(builder.like(gift.get("title"), "%" + filter.getTitle() + "%"));
        }
        if (filter.getCategory() != null) {
            String subQuery = """
                    WITH RECURSIVE temp1(id, parent_id) as (
                    SELECT t1.id, t1.parent FROM category as t1 WHERE id = ?
                    UNION
                    SELECT t2.id, t2.parent FROM category as t2 INNER JOIN temp1 ON(temp1.parent_id = t2.id))
                    SELECT id FROM temp1
                    """;
            List<Long> categories = entityManager.createNativeQuery(subQuery, List.class)
                    .setParameter(1, filter.getCategory().getId())
                    .getResultList();
            predicates.add(gift.get("category").get("id").in(categories));
        }
        if (filter.getType() != null) {
            predicates.add(builder.equal(gift.get("type"), filter.getType()));
        }
        if (filter.getTags() != null) {
            predicates.add(gift.get("tags").get("id").in(
                filter.getTags().stream().map(TagSearch::getId).toList()
            ));
        }
        if (filter.getPriceMin() != 0 || filter.getPriceMax() != 0) {
            if (filter.getPriceMin() < filter.getPriceMax()) {
                predicates.add(builder.between(gift.get("price"), filter.getPriceMin(), filter.getPriceMax()));
            } else {
                if (filter.getPriceMax() == 0) {
                    predicates.add(builder.gt(gift.get("price"), filter.getPriceMin()));
                } else {
                    predicates.add(builder.le(gift.get("price"), filter.getPriceMax()));
                }
            }
        }
        predicates.add(builder.equal(gift.get("isEnabled"), true));
        predicates.add(builder.equal(gift.get("tagFor"), filter.getTagFor()));

        query.where(builder.and(predicates.toArray(new Predicate[]{})));

        return entityManager.createQuery(query)
                .setFirstResult(filter.getSkipResult())
                .setMaxResults(filter.getCountResult())
                .getResultList();
    }
}
