package indi.nonoas.crm.dao;


import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterpriseMapper {

    List<EnterpriseDto> selectEnterprise(EnterpriseVO vo);

    int selectEnterpriseCount(EnterpriseVO vo);

    List<EnterpriseDto> selectEnterpriseByPage(EnterpriseVO vo);

    int insertEnterprise(EnterpriseDto e);

    int deleteEnterprise(int id);

    int deleteAllEnterprises();

    int insertEnterpriseBatch(List<EnterpriseDto> dtos);
}
