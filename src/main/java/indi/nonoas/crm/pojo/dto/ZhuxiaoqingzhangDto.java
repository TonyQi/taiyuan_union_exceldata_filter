package indi.nonoas.crm.pojo.dto;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

public class ZhuxiaoqingzhangDto extends EnterpriseDto{


    private BigDecimal total2021;
    private BigDecimal total2022;
    private BigDecimal total2023;
    private BigDecimal total;




    public ZhuxiaoqingzhangDto() {
    }


    public BigDecimal getTotal2021() {
        return total2021;
    }

    public void setTotal2021(BigDecimal total2021) {
        this.total2021 = total2021;
    }

    public BigDecimal getTotal2022() {
        return total2022;
    }

    public void setTotal2022(BigDecimal total2022) {
        this.total2022 = total2022;
    }

    public BigDecimal getTotal2023() {
        return total2023;
    }

    public void setTotal2023(BigDecimal total2023) {
        this.total2023 = total2023;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTotal2021String(){
        return total2021==null?"0.00":total2021.toString();
    }

    public String getTotal2022String(){
        return total2022==null?"0.00":total2022.toString();
    }

    public String getTotal2023String(){
        return total2023==null?"0.00":total2023.toString();
    }
}
