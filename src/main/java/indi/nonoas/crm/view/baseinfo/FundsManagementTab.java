package indi.nonoas.crm.view.baseinfo;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class FundsManagementTab extends Tab {

    private static volatile FundsManagementTab instance;

    private FundsManagementTab() {
        this("缴费信息管理");
    }

    private FundsManagementTab(String str) {
        super(str);
        initView();
    }

    private void initView() {
        URL url = getClass().getResource("/fxml/baseinfo/funds_manage.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            Pane pane = fxmlLoader.load();
            setContent(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setClosable(true);
    }

    public static FundsManagementTab getInstance() {
        if (null == instance) {
            synchronized (FundsManagementTab.class) {
                if (null == instance) {
                    instance = new FundsManagementTab();
                }
            }
        }
        return instance;
    }
}
