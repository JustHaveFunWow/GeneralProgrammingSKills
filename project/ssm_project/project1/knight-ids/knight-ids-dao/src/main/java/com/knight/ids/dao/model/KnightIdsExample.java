package com.knight.ids.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KnightIdsExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public KnightIdsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdsSystemIdIsNull() {
            addCriterion("ids_system_id is null");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdIsNotNull() {
            addCriterion("ids_system_id is not null");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdEqualTo(Integer value) {
            addCriterion("ids_system_id =", value, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdNotEqualTo(Integer value) {
            addCriterion("ids_system_id <>", value, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdGreaterThan(Integer value) {
            addCriterion("ids_system_id >", value, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ids_system_id >=", value, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdLessThan(Integer value) {
            addCriterion("ids_system_id <", value, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdLessThanOrEqualTo(Integer value) {
            addCriterion("ids_system_id <=", value, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdIn(List<Integer> values) {
            addCriterion("ids_system_id in", values, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdNotIn(List<Integer> values) {
            addCriterion("ids_system_id not in", values, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdBetween(Integer value1, Integer value2) {
            addCriterion("ids_system_id between", value1, value2, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsSystemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ids_system_id not between", value1, value2, "idsSystemId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameIsNull() {
            addCriterion("ids_business_name is null");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameIsNotNull() {
            addCriterion("ids_business_name is not null");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameEqualTo(String value) {
            addCriterion("ids_business_name =", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameNotEqualTo(String value) {
            addCriterion("ids_business_name <>", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameGreaterThan(String value) {
            addCriterion("ids_business_name >", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameGreaterThanOrEqualTo(String value) {
            addCriterion("ids_business_name >=", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameLessThan(String value) {
            addCriterion("ids_business_name <", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameLessThanOrEqualTo(String value) {
            addCriterion("ids_business_name <=", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameLike(String value) {
            addCriterion("ids_business_name like", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameNotLike(String value) {
            addCriterion("ids_business_name not like", value, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameIn(List<String> values) {
            addCriterion("ids_business_name in", values, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameNotIn(List<String> values) {
            addCriterion("ids_business_name not in", values, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameBetween(String value1, String value2) {
            addCriterion("ids_business_name between", value1, value2, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessNameNotBetween(String value1, String value2) {
            addCriterion("ids_business_name not between", value1, value2, "idsBusinessName");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdIsNull() {
            addCriterion("ids_business_id is null");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdIsNotNull() {
            addCriterion("ids_business_id is not null");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdEqualTo(Integer value) {
            addCriterion("ids_business_id =", value, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdNotEqualTo(Integer value) {
            addCriterion("ids_business_id <>", value, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdGreaterThan(Integer value) {
            addCriterion("ids_business_id >", value, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ids_business_id >=", value, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdLessThan(Integer value) {
            addCriterion("ids_business_id <", value, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdLessThanOrEqualTo(Integer value) {
            addCriterion("ids_business_id <=", value, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdIn(List<Integer> values) {
            addCriterion("ids_business_id in", values, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdNotIn(List<Integer> values) {
            addCriterion("ids_business_id not in", values, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdBetween(Integer value1, Integer value2) {
            addCriterion("ids_business_id between", value1, value2, "idsBusinessId");
            return (Criteria) this;
        }

        public Criteria andIdsBusinessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ids_business_id not between", value1, value2, "idsBusinessId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}