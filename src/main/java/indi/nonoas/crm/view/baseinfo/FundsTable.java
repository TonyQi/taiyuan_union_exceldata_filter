package indi.nonoas.crm.view.baseinfo;

import indi.nonoas.crm.component.table.ConditionTable;
import indi.nonoas.crm.config.TableViewConfig;
import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.dto.FundsDto;
import indi.nonoas.crm.utils.SpringUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;

import java.util.List;

public class FundsTable extends ConditionTable<FundsDto> {

    private final Logger logger = Logger.getLogger(FundsTable.class);

    private static final String MENU_CODE = "FundsInfo";

    private final ObservableList<FundsDto> obList = getItems();

    public FundsTable(List<FundsDto> item) {
        super(MENU_CODE, ((TableViewConfig) SpringUtil.getBean(TableViewConfig.class)).getFunds(),item, FundsDto.class);
        setItems(obList);
        obList.addAll(item);
        showAllInfos(item);
    }


    /**
     * 显示所有缴费信息
     */
    public void showAllInfos(List<FundsDto> items) {
        clearData();
        if (items.size() != 0){
            obList.addAll(items);
        } else{
            setPlaceholder(new Label("没有数据"));
        }

    }
}
