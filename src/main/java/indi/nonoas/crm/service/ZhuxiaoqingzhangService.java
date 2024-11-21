package indi.nonoas.crm.service;

import indi.nonoas.crm.pojo.dto.ZhuxiaoqingzhangDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;

import java.util.List;

public interface ZhuxiaoqingzhangService {

    public List<ZhuxiaoqingzhangDto> getAllZhuxiaoqingzhangList();

    public List<ZhuxiaoqingzhangDto> getZhuxiaoqingzhangList(EnterpriseVO vo);

    public List<ZhuxiaoqingzhangDto> getAllZhuxiaoqingzhangListByPage(EnterpriseVO vo,int pageNum, int pageSize);

    public int getZhuxiaoqingzhangCount(EnterpriseVO vo);
}
