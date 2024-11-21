package indi.nonoas.crm.pojo.dto;

import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class FundsDto {

    // 主键，自增
    private Integer id;

    // 企业ID
    private String enterpriseId;

    // 企业名称
    private String enterpriseName;

    // 征收机关
    private String office;

    // 组织
    private String org;

    // 资金
    private BigDecimal money;

    // 开始日期
    private Date startDate;

    // 结束日期
    private Date endDate;

    // 输入日期
    private Date inputDate;

    // 资金类型
    private String fundType;

    // 企业类型
    private String enterpriseType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public FundsDto(Integer id, String enterpriseId, String enterpriseName, String office, String org, BigDecimal money, Date startDate, Date endDate, Date inputDate, String fundType, String enterpriseType) {
        this.id = id;
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.office = office;
        this.org = org;
        this.money = money;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inputDate = inputDate;
        this.fundType = fundType;
        this.enterpriseType = enterpriseType;
    }

    public FundsDto() {
    }

    @Override
    public String toString() {
        return "FundsDto{" +
                "id=" + id +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", office='" + office + '\'' +
                ", org='" + org + '\'' +
                ", money=" + money +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", inputDate=" + inputDate +
                ", fundType='" + fundType + '\'' +
                ", enterpriseType='" + enterpriseType + '\'' +
                '}';
    }

    public String getStartDateString(){
        return startDate==null?"": DateUtil.formatDate(startDate);
    }

    public String getEndDateString(){
        return endDate==null?"": DateUtil.formatDate(endDate);
    }

    public String getInputDateString(){
        return inputDate==null?"": DateUtil.formatDate(inputDate);
    }
}