package indi.nonoas.crm.config;


import indi.nonoas.crm.pojo.dto.MenuConditionDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "view.table")
public class TableViewConfig {

    private List<MenuConditionDto> enterprise;
    private List<MenuConditionDto> funds;
    private List<MenuConditionDto> zhuxiaoqingzhang;


    public List<MenuConditionDto> getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(List<MenuConditionDto> enterprise) {
        this.enterprise = enterprise;
    }

    public List<MenuConditionDto> getFunds() {
        return funds;
    }

    public void setFunds(List<MenuConditionDto> funds) {
        this.funds = funds;
    }

    public List<MenuConditionDto> getZhuxiaoqingzhang() {
        return zhuxiaoqingzhang;
    }

    public void setZhuxiaoqingzhang(List<MenuConditionDto> zhuxiaoqingzhang) {
        this.zhuxiaoqingzhang = zhuxiaoqingzhang;
    }
}
