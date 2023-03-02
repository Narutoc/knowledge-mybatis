package com.local.naruto.knowledge.util;

import com.local.naruto.exception.BadRequestException;
import com.local.naruto.knowledge.entity.ContentModel;
import com.local.naruto.knowledge.entity.MenuInfoModel;
import com.local.naruto.utils.DateUtils;
import com.local.naruto.utils.UUIDUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class ExportExcelUtil {

    /**
     * 读取excel表格内容
     *
     * @param path   文件路径
     * @param userId 用户id
     */
    public static List<MenuInfoModel> readInfo(String path, String userId) throws IOException {
        Workbook workbook = null;
        InputStream inputStream = null;
        String fileName;
        try {
            File file = new File(path);
            fileName = file.getName();
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            inputStream = new FileInputStream(file.getAbsolutePath());
            if (fileType.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileType.equalsIgnoreCase("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = workbook.getSheetAt(0);
            // 校验sheet是否合法
            if (sheet == null) {
                log.error("excel sheet can not be null！！");
                throw new BadRequestException("excel sheet is null", "excel sheet is null");
            }
            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            // 解析起始行
            int rowStart = firstRowNum + 2;
            // 数据总行数
            int rowEnd = sheet.getPhysicalNumberOfRows();
            List<MenuInfoModel> menuList = new ArrayList<>();
            // 解析每一行的数据，构造数据对象
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                // 获取第rowNum行的数据
                Row row = sheet.getRow(rowNum);
                if (null == row) {
                    continue;
                }
                // 菜单基础信息
                MenuInfoModel menu = convertRow2MenuModel(row, userId);
                // 菜单各语言信息
                List<ContentModel> menuLanguageList = convertRow2MenuContent(row, userId,
                    menu.getMenuId());
                menu.setMenuLanguageList(menuLanguageList);
                menuList.add(menu);
            }
            log.info("menuList size is " + menuList.size());
            return menuList;
        } finally {
            if (null != workbook) {
                workbook.close();
            }
            if (null != inputStream) {
                inputStream.close();
            }
        }
    }

    private static MenuInfoModel convertRow2MenuModel(Row row, String userId) {
        MenuInfoModel menu = new MenuInfoModel();
        menu.setMenuId(UUIDUtils.generateUuid());
        // 第一列为菜单排序信息
        String sortNum = convertCellValueToString(row.getCell(0));
        menu.setSortNum(sortNum);
        // 第二列为菜单状态
        String statusValue = convertCellValueToString(row.getCell(1));
        if (StringUtils.isEmpty(statusValue)) {
            log.error("menu status can not be null！！");
            throw new BadRequestException("menu status is null", "menu status is null");
        }
        if (statusValue.equals("启用")) {
            menu.setStatus("1");
        }
        if (statusValue.equals("停用")) {
            menu.setStatus("2");
        }
        if (statusValue.equals("删除")) {
            menu.setStatus("3");
        }
        menu.setCreatedDate(DateUtils.getUtcTime());
        menu.setLastModifiedDate(DateUtils.getUtcTime());
        menu.setCreatedUser(userId);
        menu.setLastModifiedUser(userId);
        return menu;
    }

    private static List<ContentModel> convertRow2MenuContent(Row row, String userId,
        String menuId) {
        List<ContentModel> menuLanguageList = new ArrayList<>();
        // 3~14列为语言信息，每4列为一种语言信息
        // 共有三种语言
        // 每种语言由3个小列组成:1-中文，2-英文，3-俄文
        for (int languageNum = 1; languageNum <= 3; languageNum++) {
            ContentModel languageInfo = new ContentModel();
            languageInfo.setContentId(UUIDUtils.generateUuid());
            languageInfo.setObjectId(menuId);
            languageInfo.setLang(String.valueOf(languageNum));
            // 表格行列起始为0
            String menuName = convertCellValueToString(row.getCell(4 * languageNum - 2));
            log.info("菜单名称：" + menuName);
            languageInfo.setContent1(menuName);
            String menuDesc = convertCellValueToString(row.getCell(4 * languageNum + 1 - 2));
            log.info("菜单描述：" + menuDesc);
            languageInfo.setContent2(menuDesc);
            String menuLink = convertCellValueToString(row.getCell(4 * languageNum + 2 - 2));
            log.info("菜单链接：" + menuLink);
            languageInfo.setContent3(menuLink);
            String languageStatus = convertCellValueToString(row.getCell(4 * languageNum + 3 - 2));
            if (StringUtils.isEmpty(languageStatus)) {
                log.error("language status can not be null！！");
                throw new BadRequestException("language status is null", "language status is null");
            }
            if (languageStatus.equals("启用")) {
                languageInfo.setStatus("1");
            }
            if (languageStatus.equals("停用")) {
                languageInfo.setStatus("2");
            }
            if (languageStatus.equals("删除")) {
                languageInfo.setStatus("3");
            }
            languageInfo.setCreatedDate(DateUtils.getUtcTime());
            languageInfo.setLastModifiedDate(DateUtils.getUtcTime());
            languageInfo.setCreatedUser(userId);
            languageInfo.setLastModifiedUser(userId);
            menuLanguageList.add(languageInfo);
        }
        return menuLanguageList;
    }

    /**
     * 将单元格内容转换为字符串
     *
     * @param cell 单元格对象
     * @return 返回单元格内容
     */
    private static String convertCellValueToString(Cell cell) {
        String cellValue = null;
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:   //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                if (cellValue.endsWith(".0")) {
                    cellValue = cellValue.substring(0, cellValue.indexOf(".0"));
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                boolean booleanValue = cell.getBooleanCellValue();
                cellValue = Boolean.toString(booleanValue);
                break;
            case BLANK:
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                break;
        }
        return cellValue;
    }
}
