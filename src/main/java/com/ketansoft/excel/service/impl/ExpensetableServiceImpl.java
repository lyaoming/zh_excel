package com.ketansoft.excel.service.impl;
import java.math.BigDecimal;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.ketansoft.excel.entity.MajorDiseaseEntity;
import com.ketansoft.excel.mapper.CostRecordDao;
import com.ketansoft.excel.entity.CostRecordEntity;
import com.ketansoft.excel.mapper.ExpensetableDao;
import com.ketansoft.excel.service.CostRecordService;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ketansoft.excel.service.ExpensetableService;
import com.ketansoft.excel.service.MajorDiseaseService;
import org.apache.ibatis.logging.LogException;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
@Service("expensetableService")
public class ExpensetableServiceImpl implements ExpensetableService {
    @Autowired
    private ExpensetableDao expensetableDao;

    @Autowired
    private MajorDiseaseService majorDiseaseService;


    @Override
    public List<CostRecordEntity>queryList(Map<String, Object> map){return expensetableDao.queryList(map);}



    @Override
    public List<CostRecordEntity>selectList(Map<String,Object>map){return expensetableDao.selectList(map);}


    @Override
    public int queryTotal(Map<String, Object> map){ return expensetableDao.queryTotal(map);}


    @Override
    public void exportCostReport(HttpServletResponse response, HttpServletRequest request,List exportList) throws Exception {
        String templetPath = ExpensetableServiceImpl.class.getClassLoader().getResource("import-template/export.xlsx").getPath();
           try {
               templetPath = java.net.URLDecoder.decode(templetPath, "UTF-8");
               } catch (Exception e) {
                      e.printStackTrace();
               }
        XSSFWorkbook workbook = getSelectWorkbook(templetPath);
        fillExcel(workbook,exportList);
        outPutExcel(workbook, "费用报销报表", response);
    }

        public void fillExcel(XSSFWorkbook workbook,List exportList) {


        List<CostRecordEntity> expenseList =exportList;

        XSSFCellStyle cellStyle = getXssfCellStyle(workbook);

        XSSFCellStyle cellStyle1 = getXssfCellStyle(workbook);

       XSSFCellStyle cellStyle2 = getXssfCellStyle(workbook);
        // 设置单元格字体
        XSSFFont font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeight((short) 250);

        //第一行使用的字体
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeight((short)250);
        font2.setColor(HSSFColor.WHITE.index);
        font2.setBoldweight( XSSFFont.BOLDWEIGHT_BOLD);

        cellStyle.setFont(font1);

        cellStyle1.setFont(font1);

        cellStyle2.setFont(font2);

        cellStyle1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);


        cellStyle2.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCell cell = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            int rows =3;

            int rowTitle=0;

            CostRecordEntity costRecordEntity1=expenseList.get(0);
            XSSFRow rowT = sheet.createRow(rowTitle);
            rowT.setHeight((short)700);//设置表格高度

            cell = getCell(cellStyle2, rowT,0);
            cell.setCellValue("姓名:");

            cell = getCell(cellStyle1, rowT,1);
            cell.setCellValue(costRecordEntity1.getpName());


            cell = getCell(cellStyle1, rowT,2);
            cell.setCellValue(costRecordEntity1.getsName());

            cell = getCell(cellStyle1, rowT,3);
            cell.setCellValue(costRecordEntity1.getpDepartment());

            cell = getCell(cellStyle1, rowT,4);
            cell.setCellValue(costRecordEntity1.getpNumber());

            int ColNumber=0;

            Map<String,Object>map=new HashMap<>();

            List<MajorDiseaseEntity>ma=majorDiseaseService.queryList(map);

            Double result=0.00;

            int lastSize = 0;
            if (ma.size() >= 1) {
                lastSize = ma.size() - 1;
            }

            int pevNum=0;

            int nowNum=0;

            int len=0;


            for (CostRecordEntity costRecordEntity : expenseList) {

                XSSFRow row = sheet.createRow(rows);
                row.setHeight((short)900);//设置表格高度
                /**-------------------设置数据-------------------*/

                //住院日期
                int index =0;

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(costRecordEntity.getInDate());
                //发票或出院日期
                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(costRecordEntity.getOutDate());
                //类型
                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(costRecordEntity.getdName());
                //个人缴费
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getAllSelfcost()!=null) {
                    cell.setCellValue(costRecordEntity.getAllSelfcost());
                }else{
                    cell.setCellValue(0);
                }
                //自付
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getSelfCost()!=null) {
                    cell.setCellValue(costRecordEntity.getSelfCost());
                }else{
                    cell.setCellValue(0);
                }
                //补充医疗基数
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getBaseNum()!=null) {
                    cell.setCellValue(costRecordEntity.getBaseNum());
                }else{
                    cell.setCellValue(0);
                }
                //补充医疗系数
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getMFactor()!=null) {
                    cell.setCellValue(costRecordEntity.getMFactor());
                }else{
                    cell.setCellValue(0);
                }
                //补充医疗报销额
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getMCost()!=null) {
                    cell.setCellValue(costRecordEntity.getMCost());
                }else{
                    cell.setCellValue(0);
                }
                //补充医疗不报销额
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getMNocost()!=null) {
                    cell.setCellValue(costRecordEntity.getMNocost());
                }else{
                    cell.setCellValue(0);
                }
                //重疾自付基数
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getMaSelf()!=null) {
                    cell.setCellValue(costRecordEntity.getMaSelf());
                }else{
                    cell.setCellValue(0);
                }
                //重疾报销额
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getMaReduce()!=null) {
                    cell.setCellValue(costRecordEntity.getMaReduce());
                }else{
                    cell.setCellValue(0);
                }
                //合计报销额
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getCostAll()!=null) {
                    cell.setCellValue(costRecordEntity.getCostAll());
                }else{
                    cell.setCellValue(0);
                }
                //实际支付
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getAcCost()!=null) {
                    cell.setCellValue(costRecordEntity.getAcCost());
                }else{
                    cell.setCellValue(0);
                }
                //上次重疾累计
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getPevMa()!=null) {
                    cell.setCellValue(costRecordEntity.getPevMa());
                }else{
                    cell.setCellValue(0);
                }
                //本次重疾累计
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getNowMa()!=null) {
                    cell.setCellValue(costRecordEntity.getNowMa());
                }else{
                    cell.setCellValue(0);
                }
                //省外重疾计算
                cell = getCell(cellStyle, row, index++);
                if(costRecordEntity.getOutMacount()!=null) {
                    cell.setCellValue(costRecordEntity.getOutMacount());
                }else{
                    cell.setCellValue(0);
                }

                for(int i=0;i<ma.size();i++){
                    cell=getCell(cellStyle,row,index+i);
                    cell.setCellValue(0);
                }

                MajorDiseaseEntity majorDiseaseEntity0=ma.get(lastSize);

                if(majorDiseaseEntity0!=null&&costRecordEntity.getPevMa()<=majorDiseaseEntity0.getMax()) {
                    for (int i = 0; i < ma.size(); i++) {
                        MajorDiseaseEntity majorDiseaseEntity = ma.get(i);
                        if (majorDiseaseEntity.getMin() <= costRecordEntity.getPevMa() && costRecordEntity.getPevMa() <= majorDiseaseEntity.getMax()) {
                            pevNum = i;
                            break;
                        }
                    }
                    for (int i = 0; i < ma.size(); i++) {
                        MajorDiseaseEntity majorDiseaseEntity = ma.get(i);
                        len = i + 1;
                        if (majorDiseaseEntity.getMin() <= costRecordEntity.getNowMa() && costRecordEntity.getNowMa() <= majorDiseaseEntity.getMax()) {
                            nowNum = i;
                            len=i;
                            break;
                        }
                    }
                    if (len == ma.size()) {
                        MajorDiseaseEntity majorDiseaseEntity = ma.get(len-1);
                        costRecordEntity.setNowMa(majorDiseaseEntity.getMax());
                        nowNum = len - 1;
                    }
                    if (pevNum == nowNum) {
                        MajorDiseaseEntity majorDiseaseEntity = ma.get(pevNum);
                        cell = getCell(cellStyle, row, index + pevNum);
                        cell.setCellValue(costRecordEntity.getMaSelf() * majorDiseaseEntity.getIFactor());
                    }
                    if (nowNum - pevNum == 1) {
                        MajorDiseaseEntity majorDiseaseEntity1 = ma.get(pevNum);
                        cell = getCell(cellStyle, row, index + pevNum);
                        cell.setCellValue((majorDiseaseEntity1.getMax() - costRecordEntity.getPevMa()) * majorDiseaseEntity1.getIFactor());
                        MajorDiseaseEntity majorDiseaseEntity2 = ma.get(nowNum);
                        cell = getCell(cellStyle, row, index + nowNum);
                        cell.setCellValue((costRecordEntity.getNowMa() - majorDiseaseEntity1.getMax()) * majorDiseaseEntity2.getIFactor());
                    }
                    if (nowNum - pevNum > 1) {
                        Double last = costRecordEntity.getMaSelf();
                        //OK

                        MajorDiseaseEntity majorDiseaseEntity1 = ma.get(pevNum);

                        cell = getCell(cellStyle, row, index + pevNum);
                        last = last - (majorDiseaseEntity1.getMax() - costRecordEntity.getPevMa());
                        result = result + (majorDiseaseEntity1.getMax() - costRecordEntity.getPevMa()) * majorDiseaseEntity1.getIFactor();
                        cell.setCellValue((majorDiseaseEntity1.getMax() - costRecordEntity.getPevMa()) * majorDiseaseEntity1.getIFactor());

                        for (int i = pevNum + 1; i < nowNum; i++) {
                            MajorDiseaseEntity majorDiseaseEntity = ma.get(i);
                            cell = getCell(cellStyle, row, index + i);
                            result = result + ((majorDiseaseEntity.getMax() - majorDiseaseEntity.getMin()) * majorDiseaseEntity.getIFactor());
                            last = last - (majorDiseaseEntity.getMax() - majorDiseaseEntity.getMin());
                            cell.setCellValue(result);
                        }

                        MajorDiseaseEntity majorDiseaseEntity2 = ma.get(nowNum);
                        cell = getCell(cellStyle, row, index + nowNum);
                        result = result + (last * majorDiseaseEntity2.getIFactor());
                        cell.setCellValue(result);
                    }
                }

                /**----------------------------------------------*/
                ColNumber=index+ma.size();
                rows++;
            }
            //设置每一列宽度
            for(int a=1;a<=ColNumber;a++){
                sheet.setColumnWidth(a, sheet.getColumnWidth(a) * 20/ 10);
            }
    }

    private void outPutExcel(XSSFWorkbook workbook, String fileName, HttpServletResponse response) throws Exception	{
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xlsx");
        response.setContentType("Application/msexcel");
        workbook.write(response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    private XSSFWorkbook getSelectWorkbook(String templetPath) throws Exception {
        System.out.println("----------------------------------------");
        System.out.println("path:"+templetPath);
        System.out.println("----------------------------------------");
        XSSFWorkbook workbook = null;
        File templetFile = new File(templetPath);
        workbook = new XSSFWorkbook(new FileInputStream(templetFile));
        return workbook;
    }

    private XSSFCellStyle getXssfCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true);//
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        // 设置表格线
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//
        // 设置单元格字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style.setFont(font);
        return style;
    }

    private XSSFCell getCell(XSSFCellStyle cellStyle, XSSFRow row, int index) {
        XSSFCell cell = row.createCell(index);// 序号
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        return cell;
    }



    private File saveTempFile(InputStream is, String outputPath, String fileName) {
        File fOut = new File(outputPath, fileName);
        if(!fOut.exists()){
            (new File(fOut.getParent())).mkdirs();
        }
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(fOut);
            bos = new BufferedOutputStream(fos);
            int b;
            while((b = is.read()) != -1){
                bos.write(b);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        } finally {
            try {
                if(bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return fOut;
    }

    }
