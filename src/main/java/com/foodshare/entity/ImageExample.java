package com.foodshare.entity;

import java.util.ArrayList;
import java.util.List;

public class ImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImageExample() {
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

        public Criteria andMd5IsNull() {
            addCriterion("md5 is null");
            return (Criteria) this;
        }

        public Criteria andMd5IsNotNull() {
            addCriterion("md5 is not null");
            return (Criteria) this;
        }

        public Criteria andMd5EqualTo(String value) {
            addCriterion("md5 =", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotEqualTo(String value) {
            addCriterion("md5 <>", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5GreaterThan(String value) {
            addCriterion("md5 >", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5GreaterThanOrEqualTo(String value) {
            addCriterion("md5 >=", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5LessThan(String value) {
            addCriterion("md5 <", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5LessThanOrEqualTo(String value) {
            addCriterion("md5 <=", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5Like(String value) {
            addCriterion("md5 like", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotLike(String value) {
            addCriterion("md5 not like", value, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5In(List<String> values) {
            addCriterion("md5 in", values, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotIn(List<String> values) {
            addCriterion("md5 not in", values, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5Between(String value1, String value2) {
            addCriterion("md5 between", value1, value2, "md5");
            return (Criteria) this;
        }

        public Criteria andMd5NotBetween(String value1, String value2) {
            addCriterion("md5 not between", value1, value2, "md5");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andPostfixIsNull() {
            addCriterion("postfix is null");
            return (Criteria) this;
        }

        public Criteria andPostfixIsNotNull() {
            addCriterion("postfix is not null");
            return (Criteria) this;
        }

        public Criteria andPostfixEqualTo(String value) {
            addCriterion("postfix =", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixNotEqualTo(String value) {
            addCriterion("postfix <>", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixGreaterThan(String value) {
            addCriterion("postfix >", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixGreaterThanOrEqualTo(String value) {
            addCriterion("postfix >=", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixLessThan(String value) {
            addCriterion("postfix <", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixLessThanOrEqualTo(String value) {
            addCriterion("postfix <=", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixLike(String value) {
            addCriterion("postfix like", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixNotLike(String value) {
            addCriterion("postfix not like", value, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixIn(List<String> values) {
            addCriterion("postfix in", values, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixNotIn(List<String> values) {
            addCriterion("postfix not in", values, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixBetween(String value1, String value2) {
            addCriterion("postfix between", value1, value2, "postfix");
            return (Criteria) this;
        }

        public Criteria andPostfixNotBetween(String value1, String value2) {
            addCriterion("postfix not between", value1, value2, "postfix");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5IsNull() {
            addCriterion("thumbnail_md5 is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5IsNotNull() {
            addCriterion("thumbnail_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5EqualTo(String value) {
            addCriterion("thumbnail_md5 =", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5NotEqualTo(String value) {
            addCriterion("thumbnail_md5 <>", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5GreaterThan(String value) {
            addCriterion("thumbnail_md5 >", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5GreaterThanOrEqualTo(String value) {
            addCriterion("thumbnail_md5 >=", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5LessThan(String value) {
            addCriterion("thumbnail_md5 <", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5LessThanOrEqualTo(String value) {
            addCriterion("thumbnail_md5 <=", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5Like(String value) {
            addCriterion("thumbnail_md5 like", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5NotLike(String value) {
            addCriterion("thumbnail_md5 not like", value, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5In(List<String> values) {
            addCriterion("thumbnail_md5 in", values, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5NotIn(List<String> values) {
            addCriterion("thumbnail_md5 not in", values, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5Between(String value1, String value2) {
            addCriterion("thumbnail_md5 between", value1, value2, "thumbnailMd5");
            return (Criteria) this;
        }

        public Criteria andThumbnailMd5NotBetween(String value1, String value2) {
            addCriterion("thumbnail_md5 not between", value1, value2, "thumbnailMd5");
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