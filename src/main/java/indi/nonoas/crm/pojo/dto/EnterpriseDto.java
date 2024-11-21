package indi.nonoas.crm.pojo.dto;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class EnterpriseDto {

    private int id;
    private String enterpriseId;
    private String enterpriseName;
    private String enterpriseType;
    private String taxOrg;
    private Date cancelDate;


    public EnterpriseDto() {
    }

    public EnterpriseDto(int id, String enterprisId, String enterpriseName, String enterpriseType, String taxOrg, Date cancelDate) {
        this.id = id;
        this.enterpriseId = enterprisId;
        this.enterpriseName = enterpriseName;
        this.enterpriseType = enterpriseType;
        this.taxOrg = taxOrg;
        this.cancelDate = cancelDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getTaxOrg() {
        return taxOrg;
    }

    public void setTaxOrg(String taxOrg) {
        this.taxOrg = taxOrg;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCancelDateString(){
        return cancelDate==null?"":DateUtil.formatDate(cancelDate);
    }


}
