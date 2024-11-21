package indi.nonoas.crm.controller.report;

import com.monitorjbl.xlsx.StreamingReader;
import indi.jfxmaker.AppState;
import indi.nonoas.crm.component.alert.MyAlert;
import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.dto.ZhuxiaoqingzhangDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;
import indi.nonoas.crm.service.EnterpriseService;
import indi.nonoas.crm.service.ZhuxiaoqingzhangService;
import indi.nonoas.crm.utils.SpringUtil;
import indi.nonoas.crm.utils.StringUtil;
import indi.nonoas.crm.view.baseinfo.EnterpriseTable;
import indi.nonoas.crm.view.report.ZhuxiaoqingzhangTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.*;

public class ZhuxiaoqingzhangController implements Initializable {

    private Logger logger = LoggerFactory.getLogger(ZhuxiaoqingzhangController.class);

    private final ZhuxiaoqingzhangService zhuxiaoqingzhangService = (ZhuxiaoqingzhangService) SpringUtil.getBean(ZhuxiaoqingzhangService.class);

    private ZhuxiaoqingzhangTable table;

    @FXML
    private TextField tf_findInfo;
    @FXML
    private TextField tf_findInfo1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ComboBox<String> cbb_level;
    @FXML
    private DatePicker dpk_from;
    @FXML
    private DatePicker dpk_to;
    @FXML
    private Label total;
    @FXML
    private Label totolpage;
    @FXML
    private Label currentpage;
    @FXML
    private Button prebutton;
    @FXML
    private Button nextbutton;

    private int currentPageNum = 1;

    private int pageSize = 10;

    public ZhuxiaoqingzhangController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();
    }

    private void initView() {
        cbb_level.getItems().addAll("全部",
                "其他企业",
                "单位纳税人税务登记",
                "私营有限责任公司",
                "其他有限责任公司",
                "私营独资企业",
                "集体企业",
                "股份合作企业",
                "事业单位",
                "社会团体",
                "民办非企业单位（法人）",
                "缴纳预提所得税的企业",
                "私营合伙企业",
                "非国有控股非上市企业",
                "国有企业",
                "外资企业",
                "港、澳、台商投资股份有限公司",
                "非国有控股上市企业",
                "基层群众自治组织",
                "国家机关",
                "私营股份有限公司",
                "港、澳、台商独资经营企业",
                "国有独资公司",
                "国有相对控股上市企业",
                "国有相对控股非上市企业",
                "内资个人",
                "其他",
                "国有绝对控股非上市企业",
                "合资经营企业（港或澳、台资）",
                "国有绝对控股上市企业",
                "民办非企业单位（合伙）",
                "中外合资经营企业",
                "（港澳台商）提供劳务承包工程作业企业",
                "民办非企业单位（个体）",
                "提供劳务、承包工程作业企业",
                "其他外商投资企业",
                "外商投资股份有限公司",
                "中外合作经营企业",
                "其他港澳台商企业",
                "其他联营企业",
                "外国企业常驻代表机构");
        cbb_level.setValue("全部");
        dpk_from.setValue(null);
        dpk_to.setValue(null);
        EnterpriseVO vo = new EnterpriseVO();
        vo.setCurrentPage(currentPageNum);
        vo.setPageSize(pageSize);
        initData(vo);
    }

    private void initData(EnterpriseVO vo) {
        vo.setOffset((vo.getCurrentPage()-1)*vo.getPageSize());
        vo.setSize(vo.getPageSize());
        int totalCount =  zhuxiaoqingzhangService.getZhuxiaoqingzhangCount(vo);
        int totalpage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        this.total.setText("第"+currentPageNum+"页/共"+totalpage+"页");
        this.currentpage.setText("第"+currentPageNum+"页");
        List<ZhuxiaoqingzhangDto> dtoList = zhuxiaoqingzhangService.getAllZhuxiaoqingzhangListByPage(vo, currentPageNum, pageSize);
        if (table ==null){
            table = new ZhuxiaoqingzhangTable(dtoList);
            scrollPane.setContent(table);
        }else {
            table.refreshData(dtoList);
        }
        if (currentPageNum==1){
            this.prebutton.setDisable(true);
        }else {
            this.prebutton.setDisable(false) ;
        }
        if (currentPageNum == totalpage){
            this.nextbutton.setDisable(true);
        }else {
            this.nextbutton.setDisable(false);
        }
    }



    @FXML
    private void findEnterprise() {
        EnterpriseVO vo = new EnterpriseVO();
        if(StringUtil.isNotEmpty(tf_findInfo.getText())){
            vo.setEnterpriseId(tf_findInfo.getText());
        }
        if (StringUtil.compareIgnoreCase(cbb_level.getValue(),"全部",false)!=0){
            vo.setEnterpriseType(cbb_level.getValue());
        }
        if (StringUtil.isNotEmpty(tf_findInfo1.getText())){
            vo.setEnterpriseName(tf_findInfo1.getText());
        }
        if(dpk_from.getValue()!=null){
            vo.setCancelDateBegin(Date.from(dpk_from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_to.getValue()!=null){
            vo.setCancelDateEnd(Date.from(dpk_to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        currentPageNum = 1;
        vo.setCurrentPage(currentPageNum);
        vo.setPageSize(pageSize);
        initData(vo);
    }




    @FXML
    private void export() {
        EnterpriseVO vo = new EnterpriseVO();
        if(StringUtil.isNotEmpty(tf_findInfo.getText())){
            vo.setEnterpriseId(tf_findInfo.getText());
        }
        if (StringUtil.compareIgnoreCase(cbb_level.getValue(),"全部",false)!=0){
            vo.setEnterpriseType(cbb_level.getValue());
        }
        if (StringUtil.isNotEmpty(tf_findInfo1.getText())){
            vo.setEnterpriseName(tf_findInfo1.getText());
        }
        if(dpk_from.getValue()!=null){
            vo.setCancelDateBegin(Date.from(dpk_from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_to.getValue()!=null){
            vo.setCancelDateEnd(Date.from(dpk_to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        List<ZhuxiaoqingzhangDto> dtoList = zhuxiaoqingzhangService.getZhuxiaoqingzhangList(vo);
        Workbook workbook = new SXSSFWorkbook();
        FileChooser fileChooser = new FileChooser();

        // 设置文件选择器的标题
        fileChooser.setTitle("选择保存文件的位置");


        // 设置初始目录（可选）
        // fileChooser.setInitialDirectory(new File("C:/"));
        fileChooser.setInitialFileName("注销清账.xlsx");
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Excel文件", "*.xlsx");
        fileChooser.getExtensionFilters().add(textFilter);

        // 弹出对话框，让用户选择文件
        java.io.File selectedFile = fileChooser.showSaveDialog(AppState.getStage());
        if (selectedFile != null) {
            // 用户选择了文件，处理文件路径
            Sheet sheet = workbook.createSheet("注销清账"); // 创建一个新的工作表

            // 创建表头
            Row headerRow = sheet.createRow(0);
            //社会信用代码（纳税人识别号）	纳税人名称	登记注册类型	主管税务机关	注销日期	2023年缴纳金额

            headerRow.createCell(0).setCellValue("社会信用代码（纳税人识别号）");
            headerRow.createCell(1).setCellValue("纳税人名称");
            headerRow.createCell(2).setCellValue("登记注册类型");
            headerRow.createCell(3).setCellValue("主管税务机关");
            headerRow.createCell(4).setCellValue("注销日期");
            headerRow.createCell(5).setCellValue("2021年缴纳金额");
            headerRow.createCell(6).setCellValue("2022年缴纳金额");
            headerRow.createCell(7).setCellValue("2023年缴纳金额");
            headerRow.createCell(8).setCellValue("缴纳金额总计");


            // 填充数据
            int rowNum = 1;
            for (ZhuxiaoqingzhangDto myObject : dtoList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(myObject.getEnterpriseId());
                row.createCell(1).setCellValue(myObject.getEnterpriseName());
                row.createCell(2).setCellValue(myObject.getEnterpriseType());
                row.createCell(3).setCellValue(myObject.getTaxOrg());
                row.createCell(4).setCellValue(myObject.getCancelDateString());
                row.createCell(5).setCellValue(myObject.getTotal2021().doubleValue());
                row.createCell(6).setCellValue(myObject.getTotal2022().doubleValue());
                row.createCell(7).setCellValue(myObject.getTotal2023().doubleValue());
                row.createCell(8).setCellValue(myObject.getTotal().doubleValue());
            }
            try (FileOutputStream outputStream = new FileOutputStream(selectedFile)) {
                workbook.write(outputStream); // 将工作簿写入文件
                System.out.println("Excel file written successfully");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close(); // 关闭工作簿
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("导出成功");
            alert.setContentText("导出成功！！！");

            // 显示弹窗并等待用户关闭它
            alert.showAndWait();
        } else {
            // 用户取消了选择
            System.out.println("未选择文件");
        }

    }

    @FXML
    private void goPre(){
        EnterpriseVO vo = new EnterpriseVO();
        if(StringUtil.isNotEmpty(tf_findInfo.getText())){
            vo.setEnterpriseId(tf_findInfo.getText());
        }
        if (StringUtil.compareIgnoreCase(cbb_level.getValue(),"全部",false)!=0){
            vo.setEnterpriseType(cbb_level.getValue());
        }
        if (StringUtil.isNotEmpty(tf_findInfo1.getText())){
            vo.setEnterpriseName(tf_findInfo1.getText());
        }
        if(dpk_from.getValue()!=null){
            vo.setCancelDateBegin(Date.from(dpk_from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_to.getValue()!=null){
            vo.setCancelDateEnd(Date.from(dpk_to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        currentPageNum =  currentPageNum -1;
        vo.setCurrentPage(currentPageNum);
        vo.setPageSize(pageSize);
        initData(vo);
    }

    @FXML
    private void goNext(){
        EnterpriseVO vo = new EnterpriseVO();
        if(StringUtil.isNotEmpty(tf_findInfo.getText())){
            vo.setEnterpriseId(tf_findInfo.getText());
        }
        if (StringUtil.compareIgnoreCase(cbb_level.getValue(),"全部",false)!=0){
            vo.setEnterpriseType(cbb_level.getValue());
        }
        if (StringUtil.isNotEmpty(tf_findInfo1.getText())){
            vo.setEnterpriseName(tf_findInfo1.getText());
        }
        if(dpk_from.getValue()!=null){
            vo.setCancelDateBegin(Date.from(dpk_from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_to.getValue()!=null){
            vo.setCancelDateEnd(Date.from(dpk_to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        currentPageNum =  currentPageNum +1;
        vo.setCurrentPage(currentPageNum);
        vo.setPageSize(pageSize);
        initData(vo);
    }




}
