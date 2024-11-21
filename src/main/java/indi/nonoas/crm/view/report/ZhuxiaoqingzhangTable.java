package indi.nonoas.crm.view.report;

import indi.nonoas.crm.component.progress.TableProgressIndicator;
import indi.nonoas.crm.component.table.ConditionTable;
import indi.nonoas.crm.config.TableViewConfig;
import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.dto.ZhuxiaoqingzhangDto;
import indi.nonoas.crm.utils.SpringUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;

import java.util.List;

public class ZhuxiaoqingzhangTable extends ConditionTable<ZhuxiaoqingzhangDto> {

    private final Logger logger = Logger.getLogger(ZhuxiaoqingzhangTable.class);

    private static final String MENU_CODE = "Zhuxiaoqingzhang";

    private final ObservableList<ZhuxiaoqingzhangDto> obList = getItems();

    public ZhuxiaoqingzhangTable(List<ZhuxiaoqingzhangDto> item) {
        super(MENU_CODE, ((TableViewConfig) SpringUtil.getBean(TableViewConfig.class)).getZhuxiaoqingzhang(),item, ZhuxiaoqingzhangDto.class);
        setItems(obList);
        obList.addAll(item);
        showAllInfos(item);
    }


    /**
     * 显示所有企业信息
     */
    public void showAllInfos(List<ZhuxiaoqingzhangDto> items) {
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
