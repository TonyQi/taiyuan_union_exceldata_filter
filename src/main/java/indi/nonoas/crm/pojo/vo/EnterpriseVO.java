package indi.nonoas.crm.pojo.vo;

import indi.nonoas.crm.pojo.dto.EnterpriseDto;

import java.util.Date;

public class EnterpriseVO extends EnterpriseDto {

    private Date cancelDateBegin;

    private Date cancelDateEnd;

    private Integer currentPage;

    private Integer pageSize;

    private Integer offset;

    private Integer size;

    public EnterpriseVO() {
    }

    public EnterpriseVO(int id, String enterprisId, String enterpriseName, String enterpriseType, String taxOrg, Date cancelDate, Date cancelDateBegin) {
        super(id, enterprisId, enterpriseName, enterpriseType, taxOrg, cancelDate);
        this.cancelDateBegin = cancelDateBegin;
    }

    public Date getCancelDateBegin() {
        return cancelDateBegin;
    }

    public void setCancelDateBegin(Date cancelDateBegin) {
        this.cancelDateBegin = cancelDateBegin;
    }

    public Date getCancelDateEnd() {
        return cancelDateEnd;
    }

    public void setCancelDateEnd(Date cancelDateEnd) {
        this.cancelDateEnd = cancelDateEnd;
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
}
