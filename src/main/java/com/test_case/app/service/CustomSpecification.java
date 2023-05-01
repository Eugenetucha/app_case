package com.test_case.app.service;

import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class CustomSpecification<T> {
    public Specification<T> findEq(String key, Object value) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(
                        root.get(key), value);
            }
        };
    }

    public Specification<T> findBetween(String key, Integer from, Integer to) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(
                        root.get(key), from,
                        to);
            }
        };
    }

    public Specification<T> findLike(String key, Object value) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(
                        root.get(key), "%" + value + "%");
            }
        };
    }

    @Bean
    public CustomSpecification<Model> setModelCustomSpec() {
        return new CustomSpecification<Model>();
    }

    @Bean
    public CustomSpecification<Parameters> setParameterCustomSpec() {
        return new CustomSpecification<Parameters>();
    }

    @Bean
    public CustomSpecification<Line> setLineCustomSpec() {
        return new CustomSpecification<Line>();
    }
}
