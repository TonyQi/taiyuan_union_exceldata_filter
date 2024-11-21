package indi.nonoas.crm.view.report;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class ZhuxiaoqingzhangTab extends Tab {

    private static volatile ZhuxiaoqingzhangTab instance;

    private ZhuxiaoqingzhangTab() {
        this("注销清账");
    }

    private ZhuxiaoqingzhangTab(String str) {
        super(str);
        initView();
    }

    private void initView() {
        URL url = getClass().getResource("/fxml/report/zhuxiaoqingzhang.fxml");
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

    public static ZhuxiaoqingzhangTab getInstance() {
        if (null == instance) {
            synchronized (ZhuxiaoqingzhangTab.class) {
                if (null == instance) {
                    instance = new ZhuxiaoqingzhangTab();
                }
            }
        }
        return instance;
    }
}
