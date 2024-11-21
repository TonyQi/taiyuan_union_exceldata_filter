package indi.nonoas.crm.service;

import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;

import java.util.List;

public interface EnterpriseService {

    public List<EnterpriseDto> listEnterprises(EnterpriseVO vo);

    public int selectEnterpriseCount(EnterpriseVO vo);

    public List<EnterpriseDto> listEnterprisesByPage(EnterpriseVO vo);

    public int deleteEnterprise(int id);

    public int deleteAllEnterprises();

    public int insertEnterprise(EnterpriseDto e);

    public int insertEnterpriseBatch(List<EnterpriseDto> dtos);

}
