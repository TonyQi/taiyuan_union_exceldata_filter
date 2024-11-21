package indi.nonoas.crm.controller.baseinfo;

import com.monitorjbl.xlsx.StreamingReader;
import indi.jfxmaker.AppState;
import indi.nonoas.crm.component.alert.MyAlert;
import indi.nonoas.crm.pojo.dto.EnterpriseDto;
import indi.nonoas.crm.pojo.dto.FundsDto;
import indi.nonoas.crm.pojo.vo.EnterpriseVO;
import indi.nonoas.crm.pojo.vo.FundsVO;
import indi.nonoas.crm.service.EnterpriseService;
import indi.nonoas.crm.service.FundsService;
import indi.nonoas.crm.utils.SpringUtil;
import indi.nonoas.crm.utils.StringUtil;
import indi.nonoas.crm.view.baseinfo.EnterpriseTable;
import indi.nonoas.crm.view.baseinfo.FundsTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

public class FundsManageController implements Initializable {

    private Logger logger = LoggerFactory.getLogger(FundsManageController.class);

    private final FundsService fundsService = (FundsService) SpringUtil.getBean(FundsService.class);

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    private FundsTable table;

    @FXML
    private TextField tf_findEnterpriseId;
    @FXML
    private TextField tf_findEnterpriseName;
    @FXML
    private ComboBox<String> cbb_enterprise_type;
    @FXML
    private TextField tf_findOffice;
    @FXML
    private TextField tf_findOrg;
    @FXML
    private TextField tf_findMoneyFrom;
    @FXML
    private TextField tf_findMoneyTo;
    @FXML
    private DatePicker dpk_start_date_from;
    @FXML
    private DatePicker dpk_start_date_to;
    @FXML
    private DatePicker dpk_end_date_from;
    @FXML
    private DatePicker dpk_end_date_to;
    @FXML
    private DatePicker dpk_input_date_from;
    @FXML
    private DatePicker dpk_input_date_to;
    @FXML
    private ComboBox<String> fund_type;

    @FXML
    private ScrollPane scrollPane;

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

    public FundsManageController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();
    }

    private void initView() {
        cbb_enterprise_type.getItems().addAll("全部","小额");
        cbb_enterprise_type.setValue("全部");
        fund_type.getItems().addAll("全部","工会筹备金","工会经费");
        FundsVO vo = new FundsVO();
        vo.setCurrentPage(currentPageNum);
        vo.setPageSize(pageSize);
        initData(vo);
    }

    private void initData(FundsVO vo) {
        vo.setOffset((currentPageNum-1)*pageSize);
        vo.setSize(pageSize);
        int totalCount =  fundsService.selectFundsCount(vo);
        int totalpage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        this.total.setText("第"+currentPageNum+"页/共"+totalpage+"页");
        this.currentpage.setText("第"+currentPageNum+"页");
        List<FundsDto> dtoList = fundsService.listFundsByPage(vo, currentPageNum, pageSize);
        if (table ==null){
            table = new FundsTable(dtoList);
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
    private void findFunds() {
        FundsVO vo = new FundsVO();
        if(StringUtil.isNotEmpty(tf_findEnterpriseId.getText())){
            vo.setEnterpriseId(tf_findEnterpriseId.getText());
        }
        if (StringUtil.isNotEmpty(tf_findEnterpriseName.getText())){
            vo.setEnterpriseName(tf_findEnterpriseName.getText());
        }
        if (StringUtil.compareIgnoreCase(cbb_enterprise_type.getValue(),"全部",false)!=0){
            vo.setEnterpriseType(cbb_enterprise_type.getValue());
        }
        if (StringUtil.isNotEmpty(tf_findOffice.getText())){
            vo.setOffice(tf_findOffice.getText());
        }
        if (StringUtil.isNotEmpty(tf_findOrg.getText())){
            vo.setOrg(tf_findOrg.getText());
        }
        if (StringUtil.isNotEmpty(tf_findMoneyFrom.getText())){
            vo.setMoneyFrom(Double.parseDouble(tf_findMoneyFrom.getText()));
        }
        if (StringUtil.isNotEmpty(tf_findMoneyTo.getText())){
            vo.setMoneyto(Double.parseDouble(tf_findMoneyTo.getText()));
        }
        if(dpk_start_date_from.getValue()!=null){
            vo.setStartDateFrom(Date.from(dpk_start_date_from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_start_date_to.getValue()!=null){
            vo.setStartDateTo(Date.from(dpk_start_date_to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_end_date_from.getValue()!=null){
            vo.setEndDateFrom(Date.from(dpk_end_date_from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_end_date_to.getValue()!=null){
            vo.setEndDateTo(Date.from(dpk_end_date_to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_input_date_from.getValue()!=null){
            vo.setInputDateFrom(Date.from(dpk_input_date_from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if(dpk_input_date_to.getValue()!=null){
            vo.setInputDateTo(Date.from(dpk_input_date_to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if (StringUtil.compareIgnoreCase(fund_type.getValue(),"全部",false)!=0){
            vo.setFundType(fund_type.getValue());
        }

        initData(vo);
    }

    @FXML
    private void importData() throws IOException {
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
            File selectedFile = fileChooser.showOpenDialog(AppState.getStage());
            if (selectedFile != null) {
                logger.info("选择的文件路径: " + selectedFile.getPath());
                try (FileInputStream fis = new FileInputStream(new File(selectedFile.getPath()))) {
                    Workbook workbook = StreamingReader.builder().rowCacheSize(10)  //缓存到内存中的行数，默认是10
                            .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                            .open(fis);
                    List<FundsDto> objs = readFundsExcel(workbook);
                    fundsService.deleteAllFunds();
                    fundsService.insertFundsBatch(objs);
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
        findFunds();
    }


    @FXML
    private void printInfo() {

    }

    @FXML
    private void goPre(){
        currentPageNum =  currentPageNum -1;
        findFunds();
    }

    @FXML
    private void goNext(){
        currentPageNum =  currentPageNum + 1;
        findFunds();
    }

    private List<FundsDto> readFundsExcel(Workbook workbook) throws ParseException {
        List<FundsDto> objs = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++){
            Sheet sheet = workbook.getSheetAt(i); // 获取第一个工作表
            objs.addAll(this.readSheet(sheet));
        }
        return objs;
    }

    private List<FundsDto> readSheet(Sheet sheet) throws ParseException {
        int indexColumn = 0, nameColumn = 0, officeColumn = 0, orgColumn = 0, moneyColumn = 0, startDateColumn = 0, endDateColumn = 0, inputDateColumn = 0,fundTypeColumn = 0,enterpriseTypeColumn = 0;
        List<FundsDto> objs = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0){
                for (Cell cell : row) {
                    switch (cell.getStringCellValue()){
                        case "纳税人识别码":
                            indexColumn = cell.getColumnIndex();
                            break;
                        case "企业名称":
                            nameColumn = cell.getColumnIndex();
                            break;
                        case "征收机关":
                            officeColumn = cell.getColumnIndex();
                            break;
                        case "所属工会":
                            orgColumn = cell.getColumnIndex();
                            break;
                        case "实缴金额":
                            moneyColumn = cell.getColumnIndex();
                            break;
                        case "起始日期":
                            startDateColumn= cell.getColumnIndex();
                            break;
                        case  "截止日期":
                            endDateColumn = cell.getColumnIndex();
                            break;
                        case "入库日期":
                            inputDateColumn = cell.getColumnIndex();
                            break;
                        case "征收类型":
                            fundTypeColumn = cell.getColumnIndex();
                            break;
                        case "企业类型":
                            enterpriseTypeColumn = cell.getColumnIndex();
                            break;
                        default:
                            break;

                    }
                }
            }else {
                Cell cellOrg = row.getCell(orgColumn);
                if (cellOrg !=null){
                    String indexValue = row.getCell(indexColumn).getStringCellValue().replace(" ","");
                    String nameValue = row.getCell(nameColumn).getStringCellValue().replace(" ","");
                    String officeValue = row.getCell(officeColumn).getStringCellValue().replace(" ","");
                    String valueOrg = row.getCell(orgColumn).getStringCellValue().replace(" ","");
                    double moneyValue = row.getCell(moneyColumn).getNumericCellValue();
                    BigDecimal money =  new BigDecimal(moneyValue);
                    Date startDate = dateFormat.parse(row.getCell(startDateColumn).getStringCellValue());
                    Date endDate = dateFormat.parse(row.getCell(endDateColumn).getStringCellValue());
                    Date inputDate = dateFormat.parse(row.getCell(inputDateColumn).getStringCellValue());
                    String enterpriseType = row.getCell(enterpriseTypeColumn).getStringCellValue().replace(" ","");
                    String valueFundType = row.getCell(fundTypeColumn).getStringCellValue().replace(" ","");
                    FundsDto obj = new FundsDto(0,indexValue, nameValue, officeValue, valueOrg, money, startDate, endDate, inputDate, valueFundType, enterpriseType);
                    objs.add(obj);
                }

            }
        }
        return  objs;
    }
}
