package com.test_case.app.util;

import com.test_case.app.model.entity.Line;
import com.test_case.app.model.entity.Model;
import com.test_case.app.model.entity.Parameters;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class CustomSpec<T> {
    public Specification<T> findEq(String key, Object value) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(
                        root.get(key), value);
            }
        };
    }
    public Specification<T> findEq2(String key, Object value) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.in(
                        root.get(key), criteriaBuilder.equal(value));
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
    public CustomSpec<Model> setModelCustomSpec() {
        return new CustomSpec<Model>();
    }

    @Bean
    public CustomSpec<Parameters> setParameterCustomSpec() {
        return new CustomSpec<Parameters>();
    }

    @Bean
    public CustomSpec<Line> setLineCustomSpec() {
        return new CustomSpec<Line>();
    }
}
