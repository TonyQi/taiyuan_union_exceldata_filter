package indi.nonoas.crm.dao;

import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.dto.ZhuxiaoqingzhangDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;

import java.util.List;

public interface ZhuxiaoqingzhangMapper {

    public List<ZhuxiaoqingzhangDto> selectZhuxiaoqingzhang(EnterpriseVO vo);

    public List<ZhuxiaoqingzhangDto> selectZhuxiaoqingzhangByPage(EnterpriseVO vo);

    public int selectZhuxiaoqingzhangCount(EnterpriseVO vo);
}
