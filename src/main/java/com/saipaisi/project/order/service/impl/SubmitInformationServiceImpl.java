package com.saipaisi.project.order.service.impl;

import com.alibaba.excel.EasyExcel;
import com.saipaisi.common.utils.ThreadLocalCache;
import com.saipaisi.project.basic.domain.*;
import com.saipaisi.project.basic.excleimport.DataTableExcelModelListener;
import com.saipaisi.project.basic.mapper.BasicDataTableMapper;
import com.saipaisi.project.basic.service.*;
import com.saipaisi.project.basic.service.impl.BasicInformationServiceImpl;
import com.saipaisi.project.order.domain.AllMainInfoReq;
import com.saipaisi.project.order.domain.OrderForm;
import com.saipaisi.project.order.service.OrderFormService;
import com.saipaisi.project.order.service.SubmitInformationService;
import com.saipaisi.project.system.domain.BehavioralIndicator;
import com.saipaisi.project.system.service.BehavioralIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author alfred.zong
 * @Date 2021/5/27 10:12
 * @Description
 */
@Service
public class SubmitInformationServiceImpl implements SubmitInformationService {

    @Autowired
    private BasicInformationService basicInformationService;

    @Autowired
    private BasicMainRunManagerService basicMainRunManagerService;

    @Autowired
    private BasicPositionIndustryService basicPositionIndustryService;

    @Autowired
    private BasicEmployeeConditionService basicEmployeeConditionService;

    @Autowired
    private BasicHonorService basicHonorService;

    @Autowired
    private BasicMainManagerService basicMainManagerService;

    @Autowired
    private BasicSystemInformationService basicSystemInformationService;

    @Autowired
    private BasicWinBiddingService basicWinBiddingService;

    @Autowired
    private BasicDataTableMapper basicDataTableMapper;

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private BehavioralIndicatorService behavioralIndicatorService;

    @Override
    @Transactional
    public void submitInfoAll(AllMainInfoReq allMainInfoReq) throws FileNotFoundException {
        String orderSn = allMainInfoReq.getBasicInformation().getOrderSn();
        OrderForm orderForm =orderFormService.selectByOrdersn(orderSn);
        orderForm.setOneDate(allMainInfoReq.getOneDate());
        orderForm.setTwoDate(allMainInfoReq.getTwoDate());
        orderForm.setThreeDate(allMainInfoReq.getThreeDate());
        orderForm.setCashStatement(allMainInfoReq.getCashStatement());
        orderForm.setBalanceSheet(allMainInfoReq.getBalanceSheet());
        orderForm.setIncomeStatement(allMainInfoReq.getIncomeStatement());
        orderFormService.updateOrderForm(orderForm);
        //????????????
        basicInformationService.updateBasicInformation(allMainInfoReq.getBasicInformation());
        BehavioralIndicator behavioralIndicator = behavioralIndicatorService.selectBehavioralIndicatorByCode(allMainInfoReq.getBasicInformation().getIndusty());

        System.out.println("--------------------------");

        String industy = allMainInfoReq.getBasicInformation().getIndusty();
        System.out.println(industy);

        BehavioralIndicator indicator = behavioralIndicatorService.selectBehavioralIndicatorByCode(industy);
        System.out.println(indicator);

        System.out.println(behavioralIndicator);
        System.out.println("--------------------------");

        //???????????????
        List<BasicMainManager> list = allMainInfoReq.getBasicMainManager();
        list.forEach(e -> {
            basicMainManagerService.insertBasicMainManager(e);
        });

        basicPositionIndustryService.deleteBasicPositionIndustryByOrdersn(orderSn);
        //????????????
        List<BasicPositionIndustry> basicPositionIndustrys = allMainInfoReq.getBasicPositionIndustry();
        basicPositionIndustrys.stream().forEach(e -> basicPositionIndustryService.insertBasicPositionIndustry(e));
        //???????????????
        List<BasicMainRunManager> basicMainRunManagers = allMainInfoReq.getBasicMainRunManager();
        basicMainRunManagerService.deleteBasicMainRunManagerByOrdersn(orderSn);
        basicMainRunManagers.stream().forEach(e -> basicMainRunManagerService.insertBasicMainRunManager(e));

        //??????
        List<BasicHonor> basicHonor = allMainInfoReq.getBasicHonor();
        basicHonorService.deleteBasicHonorByOrdersn(orderSn);
        basicHonor.stream().forEach(e -> basicHonorService.insertBasicHonor(e));

        //????????????
        List<BasicEmployeeCondition> basicEmployeeConditions = allMainInfoReq.getBasicEmployeeCondition();
        basicEmployeeConditionService.deleteBasicEmployeeConditionByOrdersn(orderSn);
        basicEmployeeConditions.stream().forEach(e -> basicEmployeeConditionService.insertBasicEmployeeCondition(e));

        //????????????
        List<BasicSystemInformation> basicSystemInformations = allMainInfoReq.getBasicSystemInformations();
        basicSystemInformationService.deleteBasicSystemInformationByOrdersn(orderSn);
        basicSystemInformations.stream().forEach(e -> basicSystemInformationService.insertBasicSystemInformation(e));
        //??????????????????
        List<BasicWinBidding> basicWinBidding = allMainInfoReq.getBasicWinBidding();
        basicWinBiddingService.deleteBasicWinBiddingByOrdersn(orderSn);
        basicWinBidding.stream().forEach(e -> {
            basicWinBiddingService.insertBasicWinBidding(e);
        });
        dataTables(allMainInfoReq,behavioralIndicator);
    }

    @Override
    public AllMainInfoReq selectAll(String ordersn) {
        AllMainInfoReq allMainInfoReq=new AllMainInfoReq();
        //????????????
        BasicInformation basicInformation=basicInformationService.selectBasicInformationByordersn(ordersn);
        allMainInfoReq.setBasicInformation(basicInformation);
        //?????????????????????
        List<BasicMainRunManager> basicMainRunManager=basicMainRunManagerService.selectBasicMainRunManagerByOrdersn(ordersn);
        allMainInfoReq.setBasicMainRunManager(basicMainRunManager);
        //????????????
        List<BasicPositionIndustry> basicPositionIndustries=basicPositionIndustryService.selectBasicPositionIndustryByOrdersn(ordersn);
        allMainInfoReq.setBasicPositionIndustry(basicPositionIndustries);
        //?????????????????????
        List<BasicMainManager> basicMainManagers=basicMainManagerService.selectBasicMainManagerByOrdersn(ordersn);
        allMainInfoReq.setBasicMainManager(basicMainManagers);
        //??????
        List<BasicHonor> basicHonors=basicHonorService.selectBasicHonorByordersn(ordersn);
        allMainInfoReq.setBasicHonor(basicHonors);
        //??????????????????
        List<BasicEmployeeCondition>  basicEmployeeConditions=basicEmployeeConditionService.selectBasicEmployeeConditionByOrdersn(ordersn);
        allMainInfoReq.setBasicEmployeeCondition(basicEmployeeConditions);
        //????????????
        List<BasicSystemInformation> basicSystemInformations=basicSystemInformationService.selectBasicSystemInformationByOrdersn(ordersn);
        allMainInfoReq.setBasicSystemInformations(basicSystemInformations);
        //????????????
        List<BasicWinBidding> basicWinBiddings=basicWinBiddingService.selectBasicWinBiddingByOrdersn(ordersn);
        allMainInfoReq.setBasicWinBidding(basicWinBiddings);
        OrderForm orderForm=orderFormService.selectByOrdersn(ordersn);
        allMainInfoReq.setOneDate(orderForm.getOneDate());
        allMainInfoReq.setTwoDate(orderForm.getTwoDate());
        allMainInfoReq.setThreeDate(orderForm.getThreeDate());
        allMainInfoReq.setCashStatement(orderForm.getCashStatement());
        allMainInfoReq.setBalanceSheet(orderForm.getBalanceSheet());
        allMainInfoReq.setIncomeStatement(orderForm.getIncomeStatement());
        return allMainInfoReq;
    }


    /**
     * ???????????????????????????
     */
    public void dataTables(AllMainInfoReq allMainInfoReq,BehavioralIndicator behavioralIndicator) throws FileNotFoundException {

        basicDataTableMapper.deleteBasicDataTableByOrdersn(allMainInfoReq.getBasicInformation().getOrderSn());

        //?????????
//        File file = new File(allMainInfoReq.getCashStatement());
        File file = new File("D:\\?????????.xlsx");
        InputStream inputStream = new FileInputStream(file);
        EasyExcel.read(inputStream, BasicDataTable.class, new DataTableExcelModelListener(basicDataTableMapper, "1", allMainInfoReq.getBasicInformation().getOrderSn())).sheet().doRead();

        //??????
//        File file1 = new File(allMainInfoReq.getBalanceSheet());
        File file1 = new File("D:\\?????????.xlsx");
        InputStream inputStream1 = new FileInputStream(file1);
        EasyExcel.read(inputStream1, BasicDataTable.class, new DataTableExcelModelListener(basicDataTableMapper, "3", allMainInfoReq.getBasicInformation().getOrderSn())).sheet().doRead();

        //?????????
//        File file2 = new File(allMainInfoReq.getIncomeStatement());
        File file2 = new File("D:\\?????????.xlsx");
        InputStream inputStream2 = new FileInputStream(file2);
        EasyExcel.read(inputStream2, BasicDataTable.class, new DataTableExcelModelListener(basicDataTableMapper, "2", allMainInfoReq.getBasicInformation().getOrderSn())).sheet().doRead();
        calculateDatas(allMainInfoReq, behavioralIndicator);

    }

    /**
     * ????????????
     */
    public void calculateDatas(AllMainInfoReq allMainInfoReq,BehavioralIndicator behavioralIndicator) {
        BasicDataTable basicDataTable = new BasicDataTable();
        basicDataTable.setOrderSn(allMainInfoReq.getBasicInformation().getOrderSn());
        List<BasicDataTable> list = basicDataTableMapper.selectBasicDataTableList(basicDataTable);

        //?????????
        List<BasicDataTable> cashStatements = (List<BasicDataTable>) ThreadLocalCache.getCache("dataTable1");
        //??????
        List<BasicDataTable> balanceSheet = (List<BasicDataTable>) ThreadLocalCache.getCache("dataTable3");
        //?????????
        List<BasicDataTable> incomeStatement = (List<BasicDataTable>) ThreadLocalCache.getCache("dataTable2");
        generalTable(cashStatements,balanceSheet,incomeStatement,behavioralIndicator);
        ThreadLocalCache.remove();

    }

    //????????????
    private void generalTable(List<BasicDataTable> cashStatements, List<BasicDataTable> balanceSheet, List<BasicDataTable> incomeStatement,BehavioralIndicator behavioralIndicator) {
        List<BasicDataTable> generalTable = new ArrayList<>();
        //?????????
        BasicDataTable one = new BasicDataTable();
        one.setTitle("??????????????????");
        BasicDataTable basicDataTable = balanceSheet.get(balanceSheet.size() - 1);
        String ordersn = basicDataTable.getOrderSn();
        one.setOne(basicDataTable.getTwo().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        one.setTwo(basicDataTable.getThree().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        one.setThree(basicDataTable.getFour().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        one.setOrderSn(ordersn);
        one.setType("4");
        one.setSortIndex(1);
        generalTable.add(one);
        //?????????
        BasicDataTable two = new BasicDataTable();
        basicDataTable = incomeStatement.get(0);
        two.setTitle("??????????????????");
        two.setSortIndex(2);
        two.setType("4");
        two.setOrderSn(ordersn);
        two.setOne(basicDataTable.getTwo().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        two.setTwo(basicDataTable.getThree().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        two.setThree(basicDataTable.getFour().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        generalTable.add(two);
        //?????????
        BasicDataTable three = new BasicDataTable();
        basicDataTable = balanceSheet.get(1);
        three.setTitle("???????????????");
        three.setOrderSn(ordersn);
        three.setType("4");
        three.setSortIndex(3);
        three.setOne(basicDataTable.getTwo().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        three.setTwo(basicDataTable.getThree().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        three.setThree(basicDataTable.getFour().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_UP));
        generalTable.add(three);

        //?????????
        BasicDataTable four = new BasicDataTable();
        basicDataTable = cashStatements.get(0);
        BasicDataTable balanceSheettable = balanceSheet.get(2);
        four.setTitle("????????????????????????");
        four.setOrderSn(ordersn);
        four.setSortIndex(4);
        four.setType("4");
        four.setOne(basicDataTable.getTwo().divide(balanceSheettable.getTwo(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        four.setTwo(basicDataTable.getThree().divide(balanceSheettable.getThree(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        four.setThree(basicDataTable.getFour().divide(balanceSheettable.getFour(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        generalTable.add(four);

        //?????????
        BasicDataTable five = new BasicDataTable();
        five.setType("4");
        five.setSortIndex(5);
        five.setOrderSn(ordersn);
        five.setTitle("????????????");
        basicDataTable = balanceSheet.get(3);
        BasicDataTable basicDataTable1 = balanceSheet.get(0);
        balanceSheettable = balanceSheet.get(2);
        five.setOne((basicDataTable.getTwo().subtract(basicDataTable1.getTwo())).divide(balanceSheettable.getTwo(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        five.setTwo((basicDataTable.getThree().subtract(basicDataTable1.getThree())).divide(balanceSheettable.getThree(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        five.setThree((basicDataTable.getFour().subtract(basicDataTable1.getFour())).divide(balanceSheettable.getFour(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        generalTable.add(five);

        //?????????
        BasicDataTable six = new BasicDataTable();
        six.setType("4");
        six.setSortIndex(6);
        six.setOrderSn(ordersn);
        six.setTitle("??????????????????");
        basicDataTable = incomeStatement.get(0);
        basicDataTable1 = balanceSheet.get(4);
        six.setOne(basicDataTable.getTwo().multiply(new BigDecimal(2)).divide(basicDataTable1.getOne().add(basicDataTable1.getTwo()),2, BigDecimal.ROUND_UP));
        six.setTwo(basicDataTable.getThree().multiply(new BigDecimal(2)).divide(basicDataTable1.getTwo().add(basicDataTable1.getThree()),2, BigDecimal.ROUND_UP));
        six.setThree(basicDataTable.getFour().multiply(new BigDecimal(2)).divide(basicDataTable1.getThree().add(basicDataTable1.getFour()),2, BigDecimal.ROUND_UP));
        generalTable.add(six);

        //?????????
        BasicDataTable seventh = new BasicDataTable();
        seventh.setType("4");
        seventh.setSortIndex(7);
        seventh.setOrderSn(ordersn);
        seventh.setTitle("?????????????????????");
        basicDataTable1 = balanceSheet.get(5);
        seventh.setOne(basicDataTable.getTwo().multiply(new BigDecimal(2)).divide(basicDataTable1.getOne().add(basicDataTable1.getTwo()),2, BigDecimal.ROUND_UP).setScale(2, BigDecimal.ROUND_UP));
        seventh.setTwo(basicDataTable.getThree().multiply(new BigDecimal(2)).divide(basicDataTable1.getTwo().add(basicDataTable1.getThree()),2, BigDecimal.ROUND_UP).setScale(2, BigDecimal.ROUND_UP));
        seventh.setThree(basicDataTable.getFour().multiply(new BigDecimal(2)).divide(basicDataTable1.getThree().add(basicDataTable1.getFour()),2, BigDecimal.ROUND_UP).setScale(2, BigDecimal.ROUND_UP));
        generalTable.add(seventh);

        //?????????
        BasicDataTable eighth = new BasicDataTable();
        eighth.setType("4");
        eighth.setSortIndex(8);
        eighth.setOrderSn(ordersn);
        eighth.setTitle("?????????????????????");
        basicDataTable1 = balanceSheet.get(3);
        eighth.setOne(basicDataTable.getTwo().multiply(new BigDecimal(2)).divide(basicDataTable1.getOne().add(basicDataTable1.getTwo()),2, BigDecimal.ROUND_UP).setScale(2, BigDecimal.ROUND_UP));
        eighth.setTwo(basicDataTable.getThree().multiply(new BigDecimal(2)).divide(basicDataTable1.getTwo().add(basicDataTable1.getThree()),2, BigDecimal.ROUND_UP).setScale(2, BigDecimal.ROUND_UP));
        eighth.setThree(basicDataTable.getFour().multiply(new BigDecimal(2)).divide(basicDataTable1.getThree().add(basicDataTable1.getFour()),2, BigDecimal.ROUND_UP).setScale(2, BigDecimal.ROUND_UP));
        generalTable.add(eighth);

        //?????????
        BasicDataTable nine = new BasicDataTable();
        nine.setType("4");
        nine.setSortIndex(9);
        nine.setOrderSn(ordersn);
        nine.setTitle("??????????????????");
        basicDataTable = incomeStatement.get(3);
        basicDataTable1 = balanceSheet.get(6);
        nine.setOne(basicDataTable.getTwo().multiply(new BigDecimal(2)).divide(basicDataTable1.getOne().add(basicDataTable1.getTwo()),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        nine.setTwo(basicDataTable.getThree().multiply(new BigDecimal(2)).divide(basicDataTable1.getTwo().add(basicDataTable1.getThree()),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        nine.setThree(basicDataTable.getFour().multiply(new BigDecimal(2)).divide(basicDataTable1.getThree().add(basicDataTable1.getFour()),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP));
        generalTable.add(nine);

        //?????????
        BasicDataTable ten = new BasicDataTable();
        ten.setType("4");
        ten.setSortIndex(10);
        ten.setOrderSn(ordersn);
        ten.setTitle("???????????????");
        basicDataTable = incomeStatement.get(2);
        basicDataTable1 = incomeStatement.get(1);
        ten.setOne(basicDataTable.getTwo().divide(basicDataTable1.getTwo(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        ten.setTwo(basicDataTable.getThree().divide(basicDataTable1.getThree(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        ten.setThree(basicDataTable.getFour().divide(basicDataTable1.getFour(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        generalTable.add(ten);

        //???11???
        BasicDataTable eleven = new BasicDataTable();
        eleven.setType("4");
        eleven.setSortIndex(11);
        eleven.setOrderSn(ordersn);
        eleven.setTitle("????????????????????????");
        basicDataTable=cashStatements.get(0);
        basicDataTable1=incomeStatement.get(3);
        eleven.setOne(basicDataTable.getTwo().divide(basicDataTable1.getTwo(),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        eleven.setTwo(basicDataTable.getThree().divide(basicDataTable1.getThree(),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        eleven.setThree(basicDataTable.getFour().divide(basicDataTable1.getFour(),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        generalTable.add(eleven);



        //???12???
        BasicDataTable twelve = new BasicDataTable();
        twelve.setType("4");
        twelve.setSortIndex(12);
        twelve.setOrderSn(ordersn);
        twelve.setTitle("???????????????");
        basicDataTable=incomeStatement.get(0);
        twelve.setOne((basicDataTable.getTwo().subtract(basicDataTable.getOne())).divide(basicDataTable.getOne(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        twelve.setTwo((basicDataTable.getThree().subtract(basicDataTable.getTwo())).divide(basicDataTable.getTwo(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        twelve.setThree((basicDataTable.getFour().subtract(basicDataTable.getThree())).divide(basicDataTable.getThree(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        twelve.setFour(new BigDecimal(behavioralIndicator.getOperatingGrowthRate().split(",")[2]));
        generalTable.add(twelve);

        //???13???
        BasicDataTable thirteen = new BasicDataTable();
        thirteen.setType("4");
        thirteen.setSortIndex(13);
        thirteen.setOrderSn(ordersn);
        thirteen.setTitle("?????????????????????");
        basicDataTable=incomeStatement.get(2);
        thirteen.setOne((basicDataTable.getTwo().subtract(basicDataTable.getOne())).divide(basicDataTable.getOne(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        thirteen.setTwo((basicDataTable.getThree().subtract(basicDataTable.getTwo())).divide(basicDataTable.getTwo(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        thirteen.setThree((basicDataTable.getFour().subtract(basicDataTable.getThree())).divide(basicDataTable.getThree(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        thirteen.setFour(new BigDecimal(behavioralIndicator.getGrowthRateOperatingProfit().split(",")[2]));
        generalTable.add(thirteen);

        //???14???
        BasicDataTable fourteen = new BasicDataTable();
        fourteen.setType("4");
        fourteen.setSortIndex(14);
        fourteen.setOrderSn(ordersn);
        fourteen.setTitle("???????????????");
        basicDataTable=balanceSheet.get(6);
        fourteen.setOne((basicDataTable.getTwo().subtract(basicDataTable.getOne())).divide(basicDataTable.getOne(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        fourteen.setTwo((basicDataTable.getThree().subtract(basicDataTable.getTwo())).divide(basicDataTable.getTwo(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        fourteen.setThree((basicDataTable.getFour().subtract(basicDataTable.getThree())).divide(basicDataTable.getThree(),2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP));
        fourteen.setFour(new BigDecimal(behavioralIndicator.getDevelopCapitalAccumulation().split(",")[2]));
        generalTable.add(fourteen);

        //????????????
        List<BasicDataTable> repay=new ArrayList<>();
        BasicDataTable repayone = new BasicDataTable();
        repayone.setTitle("??????????????????%??? ");
        repayone.setOrderSn(ordersn);
        repayone.setSortIndex(1);
        repayone.setType("5");
        repayone.setOne(generalTable.get(2).getOne());
        repayone.setTwo(generalTable.get(2).getTwo());
        repayone.setThree(generalTable.get(2).getThree());
        String[] ass=behavioralIndicator.getAssetLiabilityRatio().split(",");
        repayone.setFour(new BigDecimal(ass[2]));
        repay.add(repayone);

        BasicDataTable repayTwo = new BasicDataTable();
        repayTwo.setTitle("???????????????????????????%???");
        repayTwo.setOrderSn(ordersn);
        repayTwo.setSortIndex(2);
        repayTwo.setType("5");
        repayTwo.setOne(generalTable.get(3).getOne());
        repayTwo.setTwo(generalTable.get(3).getTwo());
        repayTwo.setThree(generalTable.get(3).getThree());
        repayone.setFour(new BigDecimal(behavioralIndicator.getCashFlowLiabilityRatio().split(",")[2]));
        repay.add(repayTwo);

        BasicDataTable repayThree = new BasicDataTable();
        repayThree.setTitle("???????????????%???");
        repayThree.setOrderSn(ordersn);
        repayThree.setSortIndex(3);
        repayThree.setType("5");
        repayThree.setOne(generalTable.get(4).getOne());
        repayThree.setTwo(generalTable.get(4).getTwo());
        repayThree.setThree(generalTable.get(4).getThree());
        repayThree.setFour(new BigDecimal(behavioralIndicator.getQuickRatio().split(",")[2]));
        repay.add(repayThree);

        BasicDataTable repayFour = new BasicDataTable();
        basicDataTable=incomeStatement.get(4);
        basicDataTable1=incomeStatement.get(5);
        repayFour.setTitle("???????????????????????????");
        repayFour.setOrderSn(ordersn);
        repayFour.setSortIndex(4);
        repayFour.setType("5");
        repayFour.setOne((basicDataTable.getTwo().add(basicDataTable1.getTwo())).divide(basicDataTable1.getTwo(),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        repayFour.setTwo((basicDataTable.getThree().add(basicDataTable1.getThree())).divide(basicDataTable1.getThree(),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        repayFour.setThree((basicDataTable.getFour().add(basicDataTable1.getFour())).divide(basicDataTable1.getFour(),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        repayFour.setFour(new BigDecimal(behavioralIndicator.getTimesInterestEarned().split(",")[2]));
        repay.add(repayFour);

        //????????????
        List<BasicDataTable> operationCapacity=new ArrayList<>();
        BasicDataTable operationOne = new BasicDataTable();
        operationOne.setType("6");
        operationOne.setTitle("????????????????????????/??????");
        operationOne.setOrderSn(ordersn);
        operationOne.setSortIndex(1);
        operationOne.setOne(generalTable.get(5).getOne());
        operationOne.setTwo(generalTable.get(5).getTwo());
        operationOne.setThree(generalTable.get(5).getThree());
        operationOne.setFour(new BigDecimal(behavioralIndicator.getTurnoverTotalCapital().split(",")[2]));
        operationCapacity.add(operationOne);

        BasicDataTable operationTwo = new BasicDataTable();
        operationTwo.setType("6");
        operationTwo.setTitle("???????????????????????????/??????");
        operationTwo.setOrderSn(ordersn);
        operationTwo.setSortIndex(2);
        operationTwo.setOne(generalTable.get(6).getOne());
        operationTwo.setTwo(generalTable.get(6).getTwo());
        operationTwo.setThree(generalTable.get(6).getThree());
        operationTwo.setFour(new BigDecimal(behavioralIndicator.getTurnoverTotalCapital().split(",")[2]));
        operationCapacity.add(operationTwo);

        BasicDataTable operationThree = new BasicDataTable();
        operationThree.setType("6");
        operationThree.setTitle("???????????????????????????/??????");
        operationThree.setOrderSn(ordersn);
        operationThree.setSortIndex(3);
        operationThree.setOne(generalTable.get(7).getOne());
        operationThree.setTwo(generalTable.get(7).getTwo());
        operationThree.setThree(generalTable.get(7).getThree());
        operationThree.setFour(new BigDecimal(behavioralIndicator.getVelocityLiquidAssets().split(",")[2]));
        operationCapacity.add(operationThree);


        //????????????
        List<BasicDataTable> profitability=new ArrayList<>();
        BasicDataTable profiOne = new BasicDataTable();
        profiOne.setTitle("?????????????????????%???");
        profiOne.setSortIndex(1);
        profiOne.setOrderSn(ordersn);
        profiOne.setType("7");
        profiOne.setOne(generalTable.get(8).getOne());
        profiOne.setTwo(generalTable.get(8).getTwo());
        profiOne.setThree(generalTable.get(8).getThree());
        profiOne.setFour(new BigDecimal(behavioralIndicator.getReturnEquity().split(",")[2]));
        profitability.add(profiOne);

        BasicDataTable profiTwo = new BasicDataTable();
        profiTwo.setTitle("??????????????????????????????%???");
        profiTwo.setSortIndex(2);
        profiTwo.setOrderSn(ordersn);
        profiTwo.setType("7");
        profiTwo.setOne(generalTable.get(9).getOne());
        profiTwo.setTwo(generalTable.get(9).getTwo());
        profiTwo.setThree(generalTable.get(9).getThree());
        profiTwo.setFour(new BigDecimal(behavioralIndicator.getSalesOperatingProfit().split(",")[2]));
        profitability.add(profiTwo);

        BasicDataTable profiThree = new BasicDataTable();
        profiThree.setTitle("?????????????????????????????????");
        profiThree.setSortIndex(3);
        profiThree.setOrderSn(ordersn);
        profiThree.setType("7");
        profiThree.setOne(generalTable.get(10).getOne());
        profiThree.setTwo(generalTable.get(10).getTwo());
        profiThree.setThree(generalTable.get(10).getThree());
        profiThree.setFour(new BigDecimal(behavioralIndicator.getEarnedProtectionMultiple().split(",")[2]));
        profitability.add(profiThree);

        //??????????????????
        List<BasicDataTable> growthCapacity=new ArrayList<>();
        BasicDataTable growthOne = new BasicDataTable();
        growthOne.setTitle("??????????????????????????????%???");
        growthOne.setSortIndex(1);
        growthOne.setOrderSn(ordersn);
        growthOne.setType("8");
        growthOne.setOne(generalTable.get(11).getOne());
        growthOne.setTwo(generalTable.get(11).getTwo());
        growthOne.setThree(generalTable.get(11).getThree());
        growthOne.setFour(new BigDecimal(behavioralIndicator.getSalesBusinessGrowthRate().split(",")[2]));
        growthCapacity.add(growthOne);

        BasicDataTable growthTwo = new BasicDataTable();
        growthTwo.setTitle("????????????????????????????????????%???");
        growthTwo.setSortIndex(2);
        growthTwo.setOrderSn(ordersn);
        growthTwo.setType("8");
        growthTwo.setOne(generalTable.get(12).getOne());
        growthTwo.setTwo(generalTable.get(12).getTwo());
        growthTwo.setThree(generalTable.get(12).getThree());
        growthTwo.setFour(new BigDecimal(behavioralIndicator.getSalesOperatingGrowthRate().split(",")[2]));
        growthCapacity.add(growthTwo);

        BasicDataTable growthThree = new BasicDataTable();
        growthThree.setTitle("??????????????????%???");
        growthThree.setSortIndex(1);
        growthThree.setOrderSn(ordersn);
        growthThree.setType("8");
        growthThree.setOne(generalTable.get(13).getOne());
        growthThree.setTwo(generalTable.get(13).getTwo());
        growthThree.setThree(generalTable.get(13).getThree());
        growthThree.setFour(new BigDecimal(behavioralIndicator.getRateCapitalAccumulation().split(",")[2]));
        growthCapacity.add(growthThree);

        //??????????????????
        List<BasicDataTable> cashFlowAnalysis=new ArrayList<>();
        BasicDataTable cashFlowOne = new BasicDataTable();
        basicDataTable=cashStatements.get(1);
        cashFlowOne.setTitle("???????????????????????????");
        cashFlowOne.setSortIndex(1);
        cashFlowOne.setType("9");
        cashFlowOne.setOrderSn(ordersn);
        cashFlowOne.setOne(basicDataTable.getTwo().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowOne.setTwo(basicDataTable.getThree().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowOne.setThree(basicDataTable.getFour().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowAnalysis.add(cashFlowOne);


        BasicDataTable cashFlowTwo = new BasicDataTable();
        basicDataTable=cashStatements.get(0);
        cashFlowTwo.setTitle("??????????????????????????????????????????");
        cashFlowTwo.setSortIndex(2);
        cashFlowTwo.setType("9");
        cashFlowTwo.setOrderSn(ordersn);
        cashFlowTwo.setOne(basicDataTable.getTwo().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowTwo.setTwo(basicDataTable.getThree().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowTwo.setThree(basicDataTable.getFour().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowAnalysis.add(cashFlowTwo);

        BasicDataTable cashFlowThree = new BasicDataTable();
        basicDataTable=cashStatements.get(2);
        cashFlowThree.setTitle("??????????????????????????????????????????");
        cashFlowThree.setSortIndex(3);
        cashFlowThree.setType("9");
        cashFlowThree.setOrderSn(ordersn);
        cashFlowThree.setOne(basicDataTable.getTwo().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowThree.setTwo(basicDataTable.getThree().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowThree.setThree(basicDataTable.getFour().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowAnalysis.add(cashFlowThree);


        BasicDataTable cashFlowFour = new BasicDataTable();
        basicDataTable=cashStatements.get(3);
        cashFlowFour.setTitle("??????????????????????????????????????????");
        cashFlowFour.setSortIndex(4);
        cashFlowFour.setType("9");
        cashFlowFour.setOrderSn(ordersn);
        cashFlowFour.setOne(basicDataTable.getTwo().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowFour.setTwo(basicDataTable.getThree().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowFour.setThree(basicDataTable.getFour().divide(new BigDecimal(10000),2, BigDecimal.ROUND_UP).setScale(2,BigDecimal.ROUND_UP));
        cashFlowAnalysis.add(cashFlowFour);


        List<BasicDataTable> allList=new ArrayList<>();

        allList.addAll(generalTable);
        allList.addAll(cashFlowAnalysis);
        allList.addAll(growthCapacity);
        allList.addAll(profitability);
        allList.addAll(operationCapacity);
        allList.addAll(repay);
        allList.stream().forEach(e->basicDataTableMapper.insertBasicDataTable(e));

    }


}
