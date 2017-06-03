package com.donglicms.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "integral_config")
public class IntegralConfig {
    @Id
    @Column(name = "config_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long configId;

    @Column(name = "end_price")
    private BigDecimal endPrice;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name = "get_integral")
    private Long getIntegral;

    @Column(name = "insert_by")
    private Long insertBy;

    @Column(name = "insert_time")
    private Date insertTime;

    @Column(name = "integral_type")
    private Byte integralType;

    @Column(name = "start_price")
    private BigDecimal startPrice;

    private Byte state;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "use_integral")
    private Long useIntegral;

    /**
     * @return config_id
     */
    public Long getConfigId() {
        return configId;
    }

    /**
     * @param configId
     */
    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    /**
     * @return end_price
     */
    public BigDecimal getEndPrice() {
        return endPrice;
    }

    /**
     * @param endPrice
     */
    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }

    /**
     * @return exchange_rate
     */
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    /**
     * @param exchangeRate
     */
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * @return get_integral
     */
    public Long getGetIntegral() {
        return getIntegral;
    }

    /**
     * @param getIntegral
     */
    public void setGetIntegral(Long getIntegral) {
        this.getIntegral = getIntegral;
    }

    /**
     * @return insert_by
     */
    public Long getInsertBy() {
        return insertBy;
    }

    /**
     * @param insertBy
     */
    public void setInsertBy(Long insertBy) {
        this.insertBy = insertBy;
    }

    /**
     * @return insert_time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return integral_type
     */
    public Byte getIntegralType() {
        return integralType;
    }

    /**
     * @param integralType
     */
    public void setIntegralType(Byte integralType) {
        this.integralType = integralType;
    }

    /**
     * @return start_price
     */
    public BigDecimal getStartPrice() {
        return startPrice;
    }

    /**
     * @param startPrice
     */
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * @return state
     */
    public Byte getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * @return update_by
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return use_integral
     */
    public Long getUseIntegral() {
        return useIntegral;
    }

    /**
     * @param useIntegral
     */
    public void setUseIntegral(Long useIntegral) {
        this.useIntegral = useIntegral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", configId=").append(configId);
        sb.append(", endPrice=").append(endPrice);
        sb.append(", exchangeRate=").append(exchangeRate);
        sb.append(", getIntegral=").append(getIntegral);
        sb.append(", insertBy=").append(insertBy);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", integralType=").append(integralType);
        sb.append(", startPrice=").append(startPrice);
        sb.append(", state=").append(state);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", useIntegral=").append(useIntegral);
        sb.append("]");
        return sb.toString();
    }
}