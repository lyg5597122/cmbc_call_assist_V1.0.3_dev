package com.guiji.robot.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAiCfgInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public UserAiCfgInfoExample() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAiNumIsNull() {
            addCriterion("ai_num is null");
            return (Criteria) this;
        }

        public Criteria andAiNumIsNotNull() {
            addCriterion("ai_num is not null");
            return (Criteria) this;
        }

        public Criteria andAiNumEqualTo(Integer value) {
            addCriterion("ai_num =", value, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumNotEqualTo(Integer value) {
            addCriterion("ai_num <>", value, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumGreaterThan(Integer value) {
            addCriterion("ai_num >", value, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ai_num >=", value, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumLessThan(Integer value) {
            addCriterion("ai_num <", value, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumLessThanOrEqualTo(Integer value) {
            addCriterion("ai_num <=", value, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumIn(List<Integer> values) {
            addCriterion("ai_num in", values, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumNotIn(List<Integer> values) {
            addCriterion("ai_num not in", values, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumBetween(Integer value1, Integer value2) {
            addCriterion("ai_num between", value1, value2, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAiNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ai_num not between", value1, value2, "aiNum");
            return (Criteria) this;
        }

        public Criteria andAssignLevelIsNull() {
            addCriterion("assign_level is null");
            return (Criteria) this;
        }

        public Criteria andAssignLevelIsNotNull() {
            addCriterion("assign_level is not null");
            return (Criteria) this;
        }

        public Criteria andAssignLevelEqualTo(String value) {
            addCriterion("assign_level =", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelNotEqualTo(String value) {
            addCriterion("assign_level <>", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelGreaterThan(String value) {
            addCriterion("assign_level >", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelGreaterThanOrEqualTo(String value) {
            addCriterion("assign_level >=", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelLessThan(String value) {
            addCriterion("assign_level <", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelLessThanOrEqualTo(String value) {
            addCriterion("assign_level <=", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelLike(String value) {
            addCriterion("assign_level like", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelNotLike(String value) {
            addCriterion("assign_level not like", value, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelIn(List<String> values) {
            addCriterion("assign_level in", values, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelNotIn(List<String> values) {
            addCriterion("assign_level not in", values, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelBetween(String value1, String value2) {
            addCriterion("assign_level between", value1, value2, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andAssignLevelNotBetween(String value1, String value2) {
            addCriterion("assign_level not between", value1, value2, "assignLevel");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsIsNull() {
            addCriterion("template_ids is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsIsNotNull() {
            addCriterion("template_ids is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsEqualTo(String value) {
            addCriterion("template_ids =", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsNotEqualTo(String value) {
            addCriterion("template_ids <>", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsGreaterThan(String value) {
            addCriterion("template_ids >", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsGreaterThanOrEqualTo(String value) {
            addCriterion("template_ids >=", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsLessThan(String value) {
            addCriterion("template_ids <", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsLessThanOrEqualTo(String value) {
            addCriterion("template_ids <=", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsLike(String value) {
            addCriterion("template_ids like", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsNotLike(String value) {
            addCriterion("template_ids not like", value, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsIn(List<String> values) {
            addCriterion("template_ids in", values, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsNotIn(List<String> values) {
            addCriterion("template_ids not in", values, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsBetween(String value1, String value2) {
            addCriterion("template_ids between", value1, value2, "templateIds");
            return (Criteria) this;
        }

        public Criteria andTemplateIdsNotBetween(String value1, String value2) {
            addCriterion("template_ids not between", value1, value2, "templateIds");
            return (Criteria) this;
        }

        public Criteria andOpenDateIsNull() {
            addCriterion("open_date is null");
            return (Criteria) this;
        }

        public Criteria andOpenDateIsNotNull() {
            addCriterion("open_date is not null");
            return (Criteria) this;
        }

        public Criteria andOpenDateEqualTo(String value) {
            addCriterion("open_date =", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotEqualTo(String value) {
            addCriterion("open_date <>", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateGreaterThan(String value) {
            addCriterion("open_date >", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateGreaterThanOrEqualTo(String value) {
            addCriterion("open_date >=", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLessThan(String value) {
            addCriterion("open_date <", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLessThanOrEqualTo(String value) {
            addCriterion("open_date <=", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLike(String value) {
            addCriterion("open_date like", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotLike(String value) {
            addCriterion("open_date not like", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateIn(List<String> values) {
            addCriterion("open_date in", values, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotIn(List<String> values) {
            addCriterion("open_date not in", values, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateBetween(String value1, String value2) {
            addCriterion("open_date between", value1, value2, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotBetween(String value1, String value2) {
            addCriterion("open_date not between", value1, value2, "openDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNull() {
            addCriterion("invalid_date is null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNotNull() {
            addCriterion("invalid_date is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateEqualTo(String value) {
            addCriterion("invalid_date =", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotEqualTo(String value) {
            addCriterion("invalid_date <>", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThan(String value) {
            addCriterion("invalid_date >", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThanOrEqualTo(String value) {
            addCriterion("invalid_date >=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThan(String value) {
            addCriterion("invalid_date <", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThanOrEqualTo(String value) {
            addCriterion("invalid_date <=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLike(String value) {
            addCriterion("invalid_date like", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotLike(String value) {
            addCriterion("invalid_date not like", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIn(List<String> values) {
            addCriterion("invalid_date in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotIn(List<String> values) {
            addCriterion("invalid_date not in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateBetween(String value1, String value2) {
            addCriterion("invalid_date between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotBetween(String value1, String value2) {
            addCriterion("invalid_date not between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyIsNull() {
            addCriterion("invalid_policy is null");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyIsNotNull() {
            addCriterion("invalid_policy is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyEqualTo(String value) {
            addCriterion("invalid_policy =", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyNotEqualTo(String value) {
            addCriterion("invalid_policy <>", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyGreaterThan(String value) {
            addCriterion("invalid_policy >", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyGreaterThanOrEqualTo(String value) {
            addCriterion("invalid_policy >=", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyLessThan(String value) {
            addCriterion("invalid_policy <", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyLessThanOrEqualTo(String value) {
            addCriterion("invalid_policy <=", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyLike(String value) {
            addCriterion("invalid_policy like", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyNotLike(String value) {
            addCriterion("invalid_policy not like", value, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyIn(List<String> values) {
            addCriterion("invalid_policy in", values, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyNotIn(List<String> values) {
            addCriterion("invalid_policy not in", values, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyBetween(String value1, String value2) {
            addCriterion("invalid_policy between", value1, value2, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andInvalidPolicyNotBetween(String value1, String value2) {
            addCriterion("invalid_policy not between", value1, value2, "invalidPolicy");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNull() {
            addCriterion("crt_time is null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIsNotNull() {
            addCriterion("crt_time is not null");
            return (Criteria) this;
        }

        public Criteria andCrtTimeEqualTo(Date value) {
            addCriterion("crt_time =", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotEqualTo(Date value) {
            addCriterion("crt_time <>", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThan(Date value) {
            addCriterion("crt_time >", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("crt_time >=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThan(Date value) {
            addCriterion("crt_time <", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeLessThanOrEqualTo(Date value) {
            addCriterion("crt_time <=", value, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeIn(List<Date> values) {
            addCriterion("crt_time in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotIn(List<Date> values) {
            addCriterion("crt_time not in", values, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeBetween(Date value1, Date value2) {
            addCriterion("crt_time between", value1, value2, "crtTime");
            return (Criteria) this;
        }

        public Criteria andCrtTimeNotBetween(Date value1, Date value2) {
            addCriterion("crt_time not between", value1, value2, "crtTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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