package indi.nonoas.crm.view.baseinfo;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class EnterpriseManagementTab extends Tab {

    private static volatile EnterpriseManagementTab instance;

    private EnterpriseManagementTab() {
        this("企业信息管理");
    }

    private EnterpriseManagementTab(String str) {
        super(str);
        initView();
    }

    private void initView() {
        URL url = getClass().getResource("/fxml/baseinfo/enterprise_manage.fxml");
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

    public static EnterpriseManagementTab getInstance() {
        if (null == instance) {
            synchronized (EnterpriseManagementTab.class) {
                if (null == instance) {
                    instance = new EnterpriseManagementTab();
                }
            }
        }
        return instance;
    }
}
