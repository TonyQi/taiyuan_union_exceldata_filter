package indi.nonoas.crm.pojo.vo;

import indi.nonoas.crm.pojo.dto.FundsDto;

import java.math.BigDecimal;
import java.util.Date;

public class FundsVO extends FundsDto {

    private Integer currentPage;

    private Integer pageSize;

    private Integer offset;

    private Integer size;

    private Date startDateFrom;

    private Date startDateTo;

    private Date endDateFrom;

    private Date endDateTo;

    private Date inputDateFrom;

    private Date inputDateTo;

    private Double moneyFrom;

    private Double moneyto;

    public FundsVO() {
    }

    public FundsVO(Integer id, String enterpriseId, String enterpriseName, String office, String org, BigDecimal money, Date startDate, Date endDate, Date inputDate, String fundType, String enterpriseType) {
        super(id, enterpriseId, enterpriseName, office, org, money, startDate, endDate, inputDate, fundType, enterpriseType);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getStartDateFrom() {
        return startDateFrom;
    }

    public void setStartDateFrom(Date startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    public Date getStartDateTo() {
        return startDateTo;
    }

    public void setStartDateTo(Date startDateTo) {
        this.startDateTo = startDateTo;
    }

    public Date getEndDateFrom() {
        return endDateFrom;
    }

    public void setEndDateFrom(Date endDateFrom) {
        this.endDateFrom = endDateFrom;
    }

    public Date getEndDateTo() {
        return endDateTo;
    }

    public void setEndDateTo(Date endDateTo) {
        this.endDateTo = endDateTo;
    }

    public Date getInputDateFrom() {
        return inputDateFrom;
    }

    public void setInputDateFrom(Date inputDateFrom) {
        this.inputDateFrom = inputDateFrom;
    }

    public Date getInputDateTo() {
        return inputDateTo;
    }

    public void setInputDateTo(Date inputDateTo) {
        this.inputDateTo = inputDateTo;
    }

    public Double getMoneyFrom() {
        return moneyFrom;
    }

    public void setMoneyFrom(Double moneyFrom) {
        this.moneyFrom = moneyFrom;
    }

    public Double getMoneyto() {
        return moneyto;
    }

    public void setMoneyto(Double moneyto) {
        this.moneyto = moneyto;
    }
}
