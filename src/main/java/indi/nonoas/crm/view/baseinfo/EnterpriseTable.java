package indi.nonoas.crm.view.baseinfo;

import indi.nonoas.crm.component.progress.TableProgressIndicator;
import indi.nonoas.crm.component.table.ConditionTable;
import indi.nonoas.crm.config.TableViewConfig;
import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.dto.MenuConditionDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;
import indi.nonoas.crm.service.EnterpriseService;
import indi.nonoas.crm.utils.SpringUtil;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseTable extends ConditionTable<EnterpriseDto> {

    private final Logger logger = Logger.getLogger(EnterpriseTable.class);

    private static final String MENU_CODE = "EnterpriseInfo";

    private final ObservableList<EnterpriseDto> obList = getItems();

    public EnterpriseTable(List<EnterpriseDto> item) {
        super(MENU_CODE, ((TableViewConfig) SpringUtil.getBean(TableViewConfig.class)).getEnterprise(),item, EnterpriseDto.class);
        setItems(obList);
        obList.addAll(item);
        showAllInfos(item);
    }


    /**
     * 显示所有企业信息
     */
    public void showAllInfos(List<EnterpriseDto> items) {
        setPlaceholder(new TableProgressIndicator());
        clearData();
        if (items.size() != 0){
            obList.addAll(items);
        } else{
            setPlaceholder(new Label("没有数据"));
        }

    }

    public void showProgress(){
        setPlaceholder(new TableProgressIndicator());
    }
}
