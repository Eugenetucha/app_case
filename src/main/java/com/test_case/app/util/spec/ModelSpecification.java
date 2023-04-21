package com.test_case.app.util.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@AllArgsConstructor
public class ModelSpecification<T> implements Specification<T> {
    private ModelCriteria criteria;


    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if(criteria == null) {
            return null;
        }
        try {
            switch (criteria.getType()) {
                case "VARCHAR": {
                    return criteriaBuilder.like(
                            root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                }
                case "BOOLEAN": {
                    if (criteria.getValue().equals("true")) {
                        return criteriaBuilder.isTrue(
                                root.get(criteria.getKey()));
                    } else {
                        return criteriaBuilder.isFalse(
                                root.get(criteria.getKey()));
                    }
                }
                case "INT_CHECK_1": {
                    return criteriaBuilder.greaterThan(
                            root.get(criteria.getKey()), criteria.getValue());
                }
                case "INT_CHECK_2": {
                    return criteriaBuilder.lessThan(
                            root.get(criteria.getKey()), criteria.getValue());
                }
                case "INT": {
                    return criteriaBuilder.equal(
                            root.get(criteria.getKey()), criteria.getValue());
                }
            }
        }catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
