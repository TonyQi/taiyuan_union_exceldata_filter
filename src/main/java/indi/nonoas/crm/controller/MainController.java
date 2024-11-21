package indi.nonoas.crm.controller;

import de.felixroske.jfxsupport.FXMLController;
import indi.jfxmaker.AppState;
import indi.jfxmaker.utils.UIUtil;
import indi.nonoas.crm.common.ClientSession;
import indi.nonoas.crm.component.LeftMenuItemLabel;
import indi.nonoas.crm.config.ImageSrc;
import indi.nonoas.crm.pojo.LoginDto;
import indi.nonoas.crm.view.baseinfo.EnterpriseManagementTab;

import java.net.URL;
import java.util.ResourceBundle;

import indi.nonoas.crm.view.baseinfo.FundsManagementTab;
import indi.nonoas.crm.view.report.ZhuxiaoqingzhangTab;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FXMLController
public class MainController implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    private static final double IMG_SIZE = 30;




    private static final TabPane rootTabPane;

    static {
        rootTabPane = new TabPane();
        rootTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    @FXML
    private ToolBar toolBar;

    /**
     * 根布局
     */
    @FXML
    private BorderPane bp_root;



    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_backups;

    /**
     * 左侧菜单VBox
     */
    @FXML
    private VBox leftMenuVb;

    public MainController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //AppState.getAppStage().registryDragger(toolBar);


        initLeftMenu();
        initTopMenu();

        bp_root.setStyle("-fx-padding: -1px");
        bp_root.setCenter(rootTabPane);
        //addTab(ConsumeTab.getInstance());

        LoginDto loginDto = (LoginDto) ClientSession.getAttribute("user");

    }

    /**
     * 添加Tab到主TabPane
     *
     * @param tab tab面板
     */
    public static void addTab(Tab tab) {
        ObservableList<Tab> tabs = rootTabPane.getTabs();
        if (!tabs.contains(tab)) {
            tabs.add(tab);
        }
        rootTabPane.getSelectionModel().select(tab);
    }

    /**
     * 从主TabPane移除Tab
     *
     * @param tab tab面板
     */
    public static void removeTab(Tab tab) {
        ObservableList<Tab> tabs = rootTabPane.getTabs();
        tabs.remove(tab);
    }

    private void initTopMenu() {
        // 初始化顶部菜单图标
        ImageView img_backups = new ImageView(ImageSrc.BACKUPS_PATH); // 备份图标
        ImageView img_exit = new ImageView(ImageSrc.EXIT_PATH); // 退出图标

        UIUtil.setImageViewSize(img_backups, IMG_SIZE);
        UIUtil.setImageViewSize(img_exit, IMG_SIZE - 1);

//        btn_backups.setGraphic(img_backups);
//        btn_exit.setGraphic(img_exit);
    }

    /**
     * 初始化左侧菜单
     */
    private void initLeftMenu() {

        leftMenuVb.getStylesheets().add("/css/leftmenu.css");
        Accordion accordion = new Accordion();
        leftMenuVb.getChildren().add(accordion);
        accordion.getPanes().addAll(
                this.reportManageLeftMenu(),
                this.enterpriseManageLeftMenu()
        );
    }

    private TitledPane reportManageLeftMenu(){
        ListView<Label> lv = new ListView<>();
        Label zhuxiaoqingzhangReport = new LeftMenuItemLabel("注销清账");
        zhuxiaoqingzhangReport.setPrefHeight(LeftMenuItemLabel.LEFT_MENUITEM_SIZE);
        ObservableList<Label> items = lv.getItems();
        items.add(zhuxiaoqingzhangReport);
        lv.setPrefHeight(items.size() * (LeftMenuItemLabel.LEFT_MENUITEM_SIZE + 8));
        zhuxiaoqingzhangReport.setOnMouseClicked(event -> addTab(ZhuxiaoqingzhangTab.getInstance()));
        return createTitledPane("报表",lv);
    }

    private TitledPane enterpriseManageLeftMenu() {
        ListView<Label> lv = new ListView<>();
        Label enterpriseManageLb = new LeftMenuItemLabel("企业信息管理");
        enterpriseManageLb.setPrefHeight(LeftMenuItemLabel.LEFT_MENUITEM_SIZE);
        Label fundManageLb = new LeftMenuItemLabel("缴费信息管理");
        fundManageLb.setPrefHeight(LeftMenuItemLabel.LEFT_MENUITEM_SIZE);

        ObservableList<Label> items = lv.getItems();
        items.add(enterpriseManageLb);
        items.add(fundManageLb);
        lv.setPrefHeight(items.size() * (LeftMenuItemLabel.LEFT_MENUITEM_SIZE + 8));
        enterpriseManageLb.setOnMouseClicked(event -> addTab(EnterpriseManagementTab.getInstance()));
        /**
         * ToDo  修改为缴费信息管理
         */
        fundManageLb.setOnMouseClicked(event -> addTab(FundsManagementTab.getInstance()));
        return createTitledPane("基础信息管理", lv);
    }



    private TitledPane createTitledPane(String label, ListView<Label> content) {
        TitledPane titledPane = new TitledPane(label, content);
        titledPane.setExpanded(false);
        return titledPane;
    }



}
