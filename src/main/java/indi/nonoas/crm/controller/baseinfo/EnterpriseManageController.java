package indi.nonoas.crm.controller.baseinfo;

import com.monitorjbl.xlsx.StreamingReader;
import indi.jfxmaker.AppState;
import indi.nonoas.crm.component.alert.MyAlert;
import indi.nonoas.crm.component.progress.TableProgressIndicator;
import indi.nonoas.crm.controller.MainController;
import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;
import indi.nonoas.crm.service.EnterpriseService;
import indi.nonoas.crm.utils.SpringUtil;
import indi.nonoas.crm.utils.StringUtil;
import indi.nonoas.crm.view.baseinfo.EnterpriseTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EnterpriseManageController implements Initializable {

    private Logger logger = LoggerFactory.getLogger(EnterpriseManageController.class);

    private final EnterpriseService enterpriseService = (EnterpriseService) SpringUtil.getBean(EnterpriseService.class);

    private EnterpriseTable table;

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

    public EnterpriseManageController() {
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
        int totalCount =  enterpriseService.selectEnterpriseCount(vo);
        int totalpage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        this.total.setText("第"+currentPageNum+"页/共"+totalpage+"页");
        this.currentpage.setText("第"+currentPageNum+"页");
        List<EnterpriseDto> dtoList = enterpriseService.listEnterprisesByPage(vo);
        if (table ==null){
            table = new EnterpriseTable(dtoList);
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
        vo.setCurrentPage(currentPageNum);
        vo.setPageSize(pageSize);
        initData(vo);
    }

    @FXML
    private void importData() throws IOException, InterruptedException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText("请确认您的操作");
        alert.setContentText("此操作会导致原有数据恢复为导入文件的数据，是否继续操作？");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            FileChooser fileChooser = new FileChooser();
            // 设置文件选择器的标题
            fileChooser.setTitle("选择备份文件");
            // 设置文件选择器的扩展名过滤器
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("excel Files", "*.xlsx")
            );
            java.io.File selectedFile = fileChooser.showOpenDialog(AppState.getStage());
            if (selectedFile != null) {
                logger.info("选择的文件路径: " + selectedFile.getPath());
                this.table.showProgress();
                Thread.sleep(3000);
                try (FileInputStream fis = new FileInputStream(new File(selectedFile.getPath()))) {
                    Workbook workbook = StreamingReader.builder().rowCacheSize(10)  //缓存到内存中的行数，默认是10
                            .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                            .open(fis);
                    List<EnterpriseDto> objs = readEnterpriseExcel(workbook);
                    enterpriseService.deleteAllEnterprises();
                    enterpriseService.insertEnterpriseBatch(objs);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }else {
                new MyAlert(Alert.AlertType.INFORMATION, "未选择文件！").show();
            }
        }else {
            new MyAlert(Alert.AlertType.INFORMATION, "用户取消操作！").show();
        }
        currentPageNum = 1;
        findEnterprise();
    }


    @FXML
    private void printInfo() {

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

    private List<EnterpriseDto> readEnterpriseExcel(Workbook workbook) throws ParseException {
        List<EnterpriseDto> objs = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++){
            Sheet sheet = workbook.getSheetAt(i); // 获取第一个工作表
            objs.addAll(this.readSheet(sheet));
        }
        return objs;
    }

    private List<EnterpriseDto> readSheet(Sheet sheet) throws ParseException {
        int taxIndexColumn = 0,enterpriseNameColumn = 0,enterpriseTypeColumn = 0,taxOrgColumn = 0,cancelDateColumn = 0;
        List<EnterpriseDto> objs = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0){
                for (Cell cell : row) {
                    switch (cell.getStringCellValue()){
                        case "社会信用代码（纳税人识别号）":
                            taxIndexColumn = cell.getColumnIndex();
                            break;
                        case "纳税人名称":
                            enterpriseNameColumn = cell.getColumnIndex();
                            break;
                        case "登记注册类型":
                            enterpriseTypeColumn = cell.getColumnIndex();
                            break;
                        case "主管税务机关":
                            taxOrgColumn = cell.getColumnIndex();
                            break;
                        case "注销日期":
                            cancelDateColumn= cell.getColumnIndex();
                            break;
                        default:
                            break;

                    }
                }
            }else {
                try{
                    String indexValue = "",nameValue = "",typeValue = "",taxOrgValue = "";
                    Date cancelDate = null;
                    if (row.getCell(taxIndexColumn)!= null) {
                        indexValue = row.getCell(taxIndexColumn).getStringCellValue().replace(" ","");
                    }else {
                        break;
                    }
                    if (row.getCell(enterpriseNameColumn).getCellType() != CellType.BLANK) {
                        nameValue = row.getCell(enterpriseNameColumn).getStringCellValue().replace(" ","");

                    }
                    typeValue = row.getCell(enterpriseTypeColumn)==null?null:row.getCell(enterpriseTypeColumn).getStringCellValue().replace(" ","");

                    taxOrgValue = row.getCell(taxOrgColumn) == null?null:row.getCell(taxOrgColumn).getStringCellValue().replace(" ","");

                    cancelDate = row.getCell(cancelDateColumn) == null ? null : row.getCell(cancelDateColumn).getDateCellValue();

                    EnterpriseDto obj = new EnterpriseDto(1, indexValue, nameValue, typeValue, taxOrgValue, cancelDate);
                    objs.add(obj);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }
        return  objs;
    }
}
