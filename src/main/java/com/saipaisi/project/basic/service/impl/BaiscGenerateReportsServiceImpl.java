package com.saipaisi.project.basic.service.impl;


import cn.afterturn.easypoi.entity.ImageEntity;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.common.utils.StringUtils;
import com.saipaisi.common.utils.bean.BeanUtils;
import com.saipaisi.common.utils.date.DateUtil;
import com.saipaisi.common.utils.word.JfreeUtil;
import com.saipaisi.common.utils.word.WordUtil;
import com.saipaisi.project.basic.domain.*;
import com.saipaisi.project.basic.service.*;
import com.saipaisi.project.order.domain.OrderForm;
import com.saipaisi.project.order.service.OrderFormService;
import com.saipaisi.project.system.domain.BehavioralIndicator;
import com.saipaisi.project.system.service.BehavioralIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ldap.PagedResultsControl;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 生成报告服务层具体实现
 */
@Service
public class BaiscGenerateReportsServiceImpl implements BaiscGenerateReportsService {

    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private BasicInformationService basicInformationService;

    @Autowired
    private BasicHistoricEvolutionService basicHistoricEvolutionService;

    @Autowired
    private BasicShareholderService basicShareholderService;

    @Autowired
    private BasicBranchOrgService basicBranchOrgService;

    @Autowired
    private BasicEmbranchmentOrgService basicEmbranchmentOrgService;

    @Autowired
    private BasicMainRunManagerService basicMainRunManagerService;

    @Autowired
    private BasicMainManagerService basicMainManagerService;

    @Autowired
    private BasicEmployeeConditionService basicEmployeeConditionService;

    @Autowired
    private BasicPatentInformationService basicPatentInformationService;

    @Autowired
    private BasicSoftwareCopyrightService basicSoftwareCopyrightService;

    @Autowired
    private BasicCopyrightService basicCopyrightService;

    @Autowired
    private BasicQualificationInformationService basicQualificationInformationService;

    @Autowired
    private BasicPositionIndustryService basicPositionIndustryService;

    @Autowired
    private BasicHonorService basicHonorService;

    @Autowired
    private BasicCreditRecordService basicCreditRecordService;

    @Autowired
    private BasicJudicativePaperService basicJudicativePaperService;

    @Autowired
    private BasicExecutedEnterpriseService basicExecutedEnterpriseService;

    @Autowired
    private BasicPromiseEnterpriseService basicPromiseEnterpriseService;

    @Autowired
    private BasicWinBiddingService basicWinBiddingService;

    @Autowired
    private BaiscContractDisputesService baiscContractDisputesService;

    @Autowired
    private BasicScholarshipPrizeService basicScholarshipPrizeService;

    @Autowired
    private BasicHistoryClassService basicHistoryClassService;

    @Autowired
    private BasicSystemInformationService basicSystemInformationService;

    @Autowired
    private BasicDataTableService basicDataTableService;

    @Autowired
    private BehavioralIndicatorService behavioralIndicatorService;


    @Override
    public void generateReports(String ordersn) {
        HashMap<String, Object> map = new HashMap<>();
        String date = DateUtils.getDate("yyyy年MM月dd日");
        //查询订单的基本信息
        OrderForm orderForm = orderFormService.selectByOrdersn(ordersn);

        map.put("name", orderForm.getEnterprise());
        map.put("oneDate", orderForm.getOneDate());
        map.put("twoDate", orderForm.getTwoDate());
        map.put("threeDate", orderForm.getThreeDate());
        map.put("makeDate", date);
        map.put("allGrade",orderForm.getAssessmentResult());
        map.put("architectureReview","总结：未获取到企业组织架构图。");
        map.put("prictue","无");
        //查询企业基本信息
        BasicInformation basicInformation = new BasicInformation();
        basicInformation.setOrderSn(ordersn);
        BehavioralIndicator behavioralIndicator = null;
        List<BasicInformation> list = basicInformationService.selectBasicInformationList(basicInformation);
        if (!list.isEmpty()) {
            basicInformation = list.get(0);
            behavioralIndicator = behavioralIndicatorService.selectBehavioralIndicatorByCode(basicInformation.getIndusty());
            map.put("address", basicInformation.getAddress());
            map.put("operName", basicInformation.getOperName());
            map.put("regCapiDesc", basicInformation.getRegCapiDesc() + "万元");
            map.put("companyType", basicInformation.getEconKind());
            if (basicInformation.getStrategy().equals("1")){
                map.put("developmentStrategy","近三年发展规划未制定");

            }else if(basicInformation.getStrategy().equals("2")){
                map.put("developmentStrategy","近三年发展规划切实可行且执行一般");

            }else {
                map.put("developmentStrategy","近三年发展规划切实可行且执行一般");

            }
            map.put("startDate", basicInformation.getStartDate());
            map.put("creditNo", basicInformation.getCreditNo());//社会信用统一号
            map.put("contributedCapital", basicInformation.getActualCapi());//实缴资本
            map.put("companyIndusty", basicInformation.getIndusty());//
            map.put("openBank", basicInformation.getOpenBank());
            map.put("companyPhone",basicInformation.getCompanyPhone());
            map.put("creditNumber", basicInformation.getCreditNumber());
            map.put("accout", basicInformation.getAccout());
            map.put("scope", basicInformation.getScope());
            map.put("econKind", basicInformation.getEconKind());
            if(!StringUtils.isEmpty(basicInformation.getPicture())){
                ImageEntity imageEntity=new ImageEntity( JfreeUtil.imgToByPathByte(basicInformation.getPicture()),500,400);
                map.put("prictue",imageEntity);
                map.put("architectureReview","总结：总体来看，公司架构较为完善，人员布局明确管理水平较好。");
            }
        }
        //历史沿革
        historicEvolution(map, ordersn);
        //股东信息
        shareholder(map, ordersn);
        //分支机构
        localOffice(map, ordersn);
        //对外投资企业
        foreignEnterprise(map, ordersn);
        //主要经营管理者信息
        basicMainRunManager(map, ordersn);
        //主要管理者信用信息
        basicMainManager(map, ordersn);
        //从业人员情况
        basicEmployeeCondition(map, ordersn);
        //管理体系
        basicSystemInformation(map, ordersn);
        //统计表
        basicDataTableService(map,ordersn,behavioralIndicator);
        //专利信息
        patentInformation(map,ordersn);
        //软件著作权
        basicSoftwareCopyright(map,ordersn);
        //著作权
        basicCopyright(map,ordersn);
        //资质信息
        certifications(map,ordersn);
        //行业地位
        basicPositionIndustry(map,ordersn);
        //发展能力
        //荣誉
        hoonr(map,ordersn);
        //合同纠纷
        baiscContractDisputes(map,ordersn);
        //信用记录
        basicCreditRecord(map,ordersn);
        //裁判文书
        basicJudicativePaper(map,ordersn);
        //被执行企业
        basicExecutedEnterprise(map,ordersn);
        //失信企业
        basicPromiseEnterprise(map,ordersn);
        //中标履行情况
        basicWinBidding(map,ordersn);
        //偿债能力
        //营运能力
        //盈利能力
        //获奖情况
        basicScholarshipPrize(map,ordersn);
        //历史等级
        historyClass(map,ordersn);
        WordUtil.exportWord("D:\\世纪中启2021版报告生成模板.docx", "D:/", "生成文件.docx", map);

    }

    @Override
    public void score(String ordersn) {
        Double score=new Double(0.00);
        //查询企业基本信息
        BasicInformation basicInformation = new BasicInformation();
        basicInformation.setOrderSn(ordersn);
        List<BasicInformation> list = basicInformationService.selectBasicInformationList(basicInformation);
        BasicInformation basicInformation1=list.get(0);
        BehavioralIndicator behavioralIndicator=behavioralIndicatorService.selectBehavioralIndicatorByCode(basicInformation1.getIndusty());

        //注册资金
        BigDecimal regCapiDesc =basicInformation1.getRegCapiDesc();
        if (regCapiDesc.compareTo(new BigDecimal(500))<0){
            score+=0.5;
        }else {
            score+=1;
        }
        //规模
        String scale=basicInformation1.getScale();
        switch (scale){
            case "1":
                score+=0.5;
                break;
            case "2":
                score+=1.0;
                break;
            case "3":
                score+=1.5;
                break;
            case "4":
                score+=2.0;
                break;
        }
        //成立年限
        int chengli=DateUtils.yearCompare(DateUtils.parseDate(basicInformation1.getStartDate()),new Date());
        if (chengli<1){
            score+=0.1;
        }else if (1<=chengli&&chengli<=2){
            score+=0.25;
        }else if(3<=chengli&&chengli<=4){
            score+=0.5;
        }else if(chengli>5){
            score+=1;
        }
        BasicBranchOrg basicBranchOrg = new BasicBranchOrg();
        basicBranchOrg.setOrderSn(ordersn);
        //分支机构
        List<BasicBranchOrg> branchOrgs = basicBranchOrgService.selectBasicBranchOrgList(basicBranchOrg);
        if (branchOrgs!=null&&!branchOrgs.isEmpty()){
          if (branchOrgs.size()>=2){
              score+=1;
          }else {
              score+=0.5;
          }
        }
        //对外投资
        BasicEmbranchmentOrg basicEmbranchmentOrg = new BasicEmbranchmentOrg();
        basicEmbranchmentOrg.setOrderSn(ordersn);
        List<BasicEmbranchmentOrg> embranchmentOrgs = basicEmbranchmentOrgService.selectBasicEmbranchmentOrgList(basicEmbranchmentOrg);
        if (embranchmentOrgs!=null && !embranchmentOrgs.isEmpty()){
            score+=0.5;
        }
        //主要经营管理者信息
        BasicMainRunManager basicMainRunManager = new BasicMainRunManager();
        basicMainRunManager.setOrderSn(ordersn);
        List<BasicMainRunManager> basicMainRunManagers = basicMainRunManagerService.selectBasicMainRunManagerList(basicMainRunManager);
        for (BasicMainRunManager e:basicMainRunManagers
             ) {
            if (e.getEducation()>=5){
                score+=1.5;
                break;
            }
            if (e.getRank().equals("2")||e.getRank().equals("3")){
                score+=1.5;
                break;
            }
        }

        //主要管理者信用信息
        BasicMainManager basicMainManager = new BasicMainManager();
        basicMainManager.setOrderSn(ordersn);
        List<BasicMainManager> basicMainManagers = basicMainManagerService.selectBasicMainManagerList(basicMainManager);
        if (basicMainManagers !=null&&!basicMainManagers.isEmpty()){
            if (basicMainManagers.size()>=5){
                score-=5;
            }else {
                score-=basicMainManagers.size();
            }
        }else {
            score+=5;
        }
        //从业人员情况
        BasicEmployeeCondition basicEmployeeCondition = new BasicEmployeeCondition();
        basicEmployeeCondition.setOrderSn(ordersn);
        List<BasicEmployeeCondition> basicEmployeeConditions = basicEmployeeConditionService.selectBasicEmployeeConditionList(basicEmployeeCondition);
        if (basicEmployeeConditions!=null&&!basicEmployeeConditions.isEmpty()){
            BasicEmployeeCondition employeeCondition=basicEmployeeConditions.get(0);
            Integer sumPerson=new Integer(employeeCondition.getPersonSum());
            if (sumPerson>=1&&sumPerson<=19){
                score+=0.5;
            }else if (sumPerson>=20&&sumPerson<=40){
                score+=0.8;
            }else if(sumPerson>=41){
                score+=1.0;
            }
        }
        //管理体系
        BasicSystemInformation basicSystemInformation = new BasicSystemInformation();
        basicSystemInformation.setOrderSn(ordersn);
        List<BasicSystemInformation> basicSystemInformations = basicSystemInformationService.selectBasicSystemInformationList(basicSystemInformation);
        for (BasicSystemInformation b:basicSystemInformations
             ) {
            Date dates=DateUtils.parseDate(b.getEffectiveCutoff());
            if(dates.compareTo(new Date())>0){
              score+=0.5;
            }
        }
        //公司架构
        if(basicInformation1.getArchitecture().equals("2")){
            score+=1;
        }
        //专利系信息
        BasicPatentInformation basicPatentInformation=new BasicPatentInformation();
        basicPatentInformation.setOrderSn(ordersn);
        List<BasicPatentInformation> basicPatentInformations=basicPatentInformationService.selectBasicPatentInformationList(basicPatentInformation);
        if (basicPatentInformations!=null&&!basicPatentInformations.isEmpty()){
            if (basicPatentInformations.size()>=3){
                score+=1.5;
            }else {
                Double sum=0.5*basicPatentInformations.size();
                score+=sum;
            }
        }
        //软著信息
        BasicSoftwareCopyright basicSoftwareCopyright=new BasicSoftwareCopyright();
        basicSoftwareCopyright.setOrderSn(ordersn);
        List<BasicSoftwareCopyright> basicSoftwareCopyrights=basicSoftwareCopyrightService.selectBasicSoftwareCopyrightList(basicSoftwareCopyright);
        if (basicSoftwareCopyrights !=null&&!basicSoftwareCopyrights.isEmpty()){
            if (basicSoftwareCopyrights.size()>=3){
                score+=1.5;
            }else {
                Double sum=0.5*basicSoftwareCopyrights.size();
                score+=sum;
            }
        }
        //著作权
        BasicCopyright basicCopyright=new BasicCopyright();
        basicCopyright.setOrderSn(ordersn);
        List<BasicCopyright> basicCopyrights=basicCopyrightService.selectBasicCopyrightList(basicCopyright);
        if (basicCopyrights !=null&&!basicCopyrights.isEmpty()){
            if (basicCopyrights.size()>=3){
                score+=1.5;
            }else {
                Double sum=0.5*basicCopyrights.size();
                score+=sum;
            }
        }
        //资质信息
        BasicQualificationInformation basicQualificationInformation=new BasicQualificationInformation();
        basicQualificationInformation.setOrderSn(ordersn);
        List<BasicQualificationInformation> basicQualificationInformations=basicQualificationInformationService.selectBasicQualificationInformationList(basicQualificationInformation);
        if (basicQualificationInformations!=null&&!basicQualificationInformations.isEmpty()){
            score+=1.0;
        }
        //产业环境
        score+=2.0;
        //发展战略
        if ("2".equals(basicInformation1.getStrategy())){
              score+=1.0;
        }else if("3".equals(basicInformation1.getStrategy())){
              score+=2.0;
        }
        //社会责任
        BasicHonor basicHonor=new BasicHonor();
        basicHonor.setOrderSn(ordersn);
        List<BasicHonor> basicHonors=basicHonorService.selectBasicHonorList(basicHonor);
        if (basicHonors!=null&&!basicHonors.isEmpty()){
            score+=1.0;
        }
        //
        //（二）中标履约情况
        BasicWinBidding basicWinBidding=new BasicWinBidding();
        basicWinBidding.setOrderSn(ordersn);
        List<BasicWinBidding> basicWinBiddings=basicWinBiddingService.selectBasicWinBiddingList(basicWinBidding);
        if (basicWinBiddings!=null&&basicWinBiddings.isEmpty()){
            int count=0;
            for (BasicWinBidding b:basicWinBiddings
                 ) {
               if (b.getState().equals("是")){{
                   count+=1;
               }
               }
            }
            if (count>2){
                score+=2;
            }else {
                score+=(1*count);
            }
        }
        //失信企业
        BasicPromiseEnterprise basicPromiseEnterprise=new BasicPromiseEnterprise();
        basicPromiseEnterprise.setOrderSn(ordersn);
        List<BasicPromiseEnterprise> basicPromiseEnterprises=basicPromiseEnterpriseService.selectBasicPromiseEnterpriseList(basicPromiseEnterprise);
        if(basicPromiseEnterprises==null||basicPromiseEnterprises.size()==0){
            score+=10.0;
        }
        //被执行企业
        BasicExecutedEnterprise basicExecutedEnterprise=new BasicExecutedEnterprise();
        basicExecutedEnterprise.setOrderSn(ordersn);
        List<BasicExecutedEnterprise> basicExecutedEnterprises=basicExecutedEnterpriseService.selectBasicExecutedEnterpriseList(basicExecutedEnterprise);
        if (basicExecutedEnterprises!=null&&!basicExecutedEnterprises.isEmpty()){
            if (basicExecutedEnterprises.size()==1){
              score+=9.0;
            }else if (basicExecutedEnterprises.size()==2){
                score+=7.0;
            }
        }else {
            score+=10.0;
        }
        //信用情況
        BasicCreditRecord basicCreditRecord=new BasicCreditRecord();
        basicCreditRecord.setOrderSn(ordersn);
        List<BasicCreditRecord> basicCreditRecords=basicCreditRecordService.selectBasicCreditRecordList(basicCreditRecord);

        //合同纠纷
        BaiscContractDisputes baiscContractDisputes = new BaiscContractDisputes();
        baiscContractDisputes.setOrderSn(ordersn);
        List<BaiscContractDisputes> baiscContractDisputesList = baiscContractDisputesService.selectBaiscContractDisputesList(baiscContractDisputes);
        if (baiscContractDisputesList !=null &&!baiscContractDisputesList.isEmpty()){
            if (baiscContractDisputesList.size()<10){
                double remain=10.0-(1*baiscContractDisputesList.size());
                score+=remain;
            }
        }else {
            score+=10;
        }
        //财务状况
        dataTables(ordersn,score,behavioralIndicator);
        grad(score);
        OrderForm orderForm=orderFormService.selectByOrdersn(ordersn);
        orderForm.setAssessmentResult(grad(score));
        orderFormService.updateOrderForm(orderForm);

    }


    //根据分数确定等级
    public String grad(Double score){
        if (score>=90.0){
          return "AAA";
        }else if (score<90.0&&score>=80.0){
            return "AA";
        }else if(score<80&&score>=70.0){
            return "A";
        }else if(score<70.0&&score>=60.0){
            return "BBB";
        }else if(score<60.0&&score>=50.0){
            return "BB";
        }else if(score<50.0&&score>=40.0){
            return "B";
        }else if(score<40.0&&score>=30.0){
            return "CCC";
        }else if(score<30.0&&score>=20.0){
            return "CC";
        }else if(score<20.0&&score>=10.0){
            return "C";
        }else if(score<10.0){
            return "D";
        }
      return "";
    }

    //统计表的计算打分
    public void dataTables(String ordersn,Double socre,BehavioralIndicator behavioralIndicator){
        BasicDataTable basicDataTable = new BasicDataTable();
        basicDataTable.setOrderSn(ordersn);
        List<BasicDataTable> list = basicDataTableService.selectBasicDataTableList(basicDataTable);
        //偿债能力
        List<BasicDataTable> ayinAbility=list.stream().filter(e->e.getType().equals("5")).collect(Collectors.toList());
        for (BasicDataTable e:ayinAbility) {
            if (e.getSortIndex()==1){
            //资产负债率
                String assetLiabilityRatio=behavioralIndicator.getAssetLiabilityRatio();
                String[] asset=assetLiabilityRatio.split(",");
                int count=0;
                for (String s:asset
                     ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                     count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==2){
            //现金流动负债比率（%）
               String cashFlowLiabilityRatio=behavioralIndicator.getCashFlowLiabilityRatio();
               String[] cashs=cashFlowLiabilityRatio.split(",");
                int count=0;
                for (String s:cashs
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==3){
            //速动比率（%）
                String quickRatio=behavioralIndicator.getQuickRatio();
                String[] quickRatios=quickRatio.split(",");
                int count=0;
                for (String s:quickRatios
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if(e.getSortIndex()==4){
             //利息保障倍数（倍）
                String timesInterestEarned=behavioralIndicator.getTimesInterestEarned();
                String[] tims=timesInterestEarned.split(",");
                int count=0;
                for (String s:tims
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }
        }
        //运营能力
        List<BasicDataTable> operation=list.stream().filter(e->e.getType().equals("6")).collect(Collectors.toList());
        for (BasicDataTable e:operation) {
            if (e.getSortIndex()==1){
                //总资产周转率（次/年）
                String turnoverTotalCapital=behavioralIndicator.getTurnoverTotalCapital();
                String[] tur=turnoverTotalCapital.split(",");
                int count=0;
                for (String s:tur
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==2){
                //应收账款周转率（次/年）
                String turnoverAccountReceivable=behavioralIndicator.getTurnoverAccountReceivable();
                String[] turn=turnoverAccountReceivable.split(",");
                int count=0;
                for (String s:turn
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==3){
                //流动资产周转率（次/年）
                String velocityLiquidAssets=behavioralIndicator.getVelocityLiquidAssets();
                String[] vel=velocityLiquidAssets.split(",");
                int count=0;
                for (String s:vel
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }
        }
        //盈利能力
        List<BasicDataTable> profitability=list.stream().filter(e->e.getType().equals("7")).collect(Collectors.toList());
        for (BasicDataTable e:profitability) {
            if (e.getSortIndex()==1){
                //净资产收益率（%）
                String returnEquity=behavioralIndicator.getReturnEquity();
                String[] ret=returnEquity.split(",");
                int count=0;
                for (String s:ret
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==2){
                //销售（营业）利润率（%）
                String salesOperatingProfit=behavioralIndicator.getSalesOperatingProfit();
                String[] sal=salesOperatingProfit.split(",");
                int count=0;
                for (String s:sal
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==3){
                //盈余现金保障倍数（倍）
                String earnedProtectionMultiple=behavioralIndicator.getEarnedProtectionMultiple();
                String[] sal=earnedProtectionMultiple.split(",");
                int count=0;
                for (String s:sal
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }
        }
        //8经营增长能力
        List<BasicDataTable> operational=list.stream().filter(e->e.getType().equals("8")).collect(Collectors.toList());
        for (BasicDataTable e:operational) {
            if (e.getSortIndex()==1){
                //销售（营业）增长率（%）
                String earnedProtectionMultiple=behavioralIndicator.getSalesBusinessGrowthRate();
                String[] sal=earnedProtectionMultiple.split(",");
                int count=0;
                for (String s:sal
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==2){
                //销售（营业）利润增长率（%）
                String earnedProtectionMultiple=behavioralIndicator.getSalesOperatingGrowthRate();
                String[] sal=earnedProtectionMultiple.split(",");
                int count=0;
                for (String s:sal
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }else if (e.getSortIndex()==3){
                //资本积累率（%）
                String earnedProtectionMultiple=behavioralIndicator.getRateCapitalAccumulation();
                String[] sal=earnedProtectionMultiple.split(",");
                int count=0;
                for (String s:sal
                ) {
                    BigDecimal b=new BigDecimal(s);
                    if (b.compareTo(e.getThree())>=0){
                        count+=1;
                    }
                }
                if(count==3){
                    socre+=1.5;
                }else if (count==2){
                    socre+=1.0;
                }else if (count==1){
                    socre+=0.5;
                }
            }
        }
    }

    //历史沿革
    public void historicEvolution(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicHistoricEvolution basicHistoricEvolution = new BasicHistoricEvolution();
        basicHistoricEvolution.setOrderSn(ordersn);
        List<BasicHistoricEvolution> list = basicHistoricEvolutionService.selectBasicHistoricEvolutionList(basicHistoricEvolution);
        int sn = 1;
        for (BasicHistoricEvolution e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }

        map.put("hisorty", maps);
    }

    //股东组成
    public void shareholder(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicShareholder basicShareholder = new BasicShareholder();
        basicShareholder.setOrderSn(ordersn);
        List<BasicShareholder> list = basicShareholderService.selectBasicShareholderList(basicShareholder);
        BigDecimal sumShouldCapi = new BigDecimal(0);
        BigDecimal sumRealCapil = new BigDecimal(0);
        BigDecimal sumRealScale = new BigDecimal(0);
        int sn = 1;
        for (BasicShareholder e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            sumRealScale=sumRealScale.add(e.getRealScale());
            sumRealCapil=sumRealCapil.add(e.getRealCapil());
            sumShouldCapi = sumShouldCapi.add(e.getShouldCapi());
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        map.put("sumShouldCapi",sumShouldCapi);
        map.put("sumRealCapil",sumRealCapil);
        map.put("sumRealScale",sumRealScale);
        map.put("BasicShareholder", maps);
    }

    //分支机构
    public void localOffice(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicBranchOrg basicBranchOrg = new BasicBranchOrg();
        basicBranchOrg.setOrderSn(ordersn);
        List<BasicBranchOrg> list = basicBranchOrgService.selectBasicBranchOrgList(basicBranchOrg);
        int sn = 1;
        for (BasicBranchOrg e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        map.put("BasicBranchOrg", maps);
    }

    //对外投资企业
    public void foreignEnterprise(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicEmbranchmentOrg basicEmbranchmentOrg = new BasicEmbranchmentOrg();
        basicEmbranchmentOrg.setOrderSn(ordersn);
        List<BasicEmbranchmentOrg> list = basicEmbranchmentOrgService.selectBasicEmbranchmentOrgList(basicEmbranchmentOrg);
        int sn = 1;
        for (BasicEmbranchmentOrg e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        map.put("BasicEmbranchmentOrg", maps);
    }

    //主要经营管理者信息
    public void basicMainRunManager(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicMainRunManager basicMainRunManager = new BasicMainRunManager();
        basicMainRunManager.setOrderSn(ordersn);
        List<BasicMainRunManager> list = basicMainRunManagerService.selectBasicMainRunManagerList(basicMainRunManager);
        int sn = 1;
        for (BasicMainRunManager e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            maps.add(ma);
            sn += 1;
        }
        map.put("BasicMainRunManager", maps);
    }

    //主要管理者信用信息
    public void basicMainManager(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicMainManager basicMainManager = new BasicMainManager();
        basicMainManager.setOrderSn(ordersn);
        List<BasicMainManager> list = basicMainManagerService.selectBasicMainManagerList(basicMainManager);
        for (BasicMainManager e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            maps.add(ma);

        }
        map.put("BasicMainManager", maps);
    }

    //从业人员情况
    public void basicEmployeeCondition(Map<String, Object> map, String ordersn) {
        BasicEmployeeCondition basicEmployeeCondition = new BasicEmployeeCondition();
        basicEmployeeCondition.setOrderSn(ordersn);
        List<BasicEmployeeCondition> list = basicEmployeeConditionService.selectBasicEmployeeConditionList(basicEmployeeCondition);
        if (list != null && !list.isEmpty()) {
            BasicEmployeeCondition entity = list.get(0);
            map.put("employeePersonSum", entity.getPersonSum());
            map.put("seniorSchool", entity.getSeniorSchool());
            map.put("junior", entity.getJunior());
            map.put("regular", entity.getRegular());
            map.put("oneTwoYears", entity.getOneTwoYears());
            map.put("threeFiveYears", entity.getThreeFiveYears());
            map.put("moreThanFiveYear", entity.getMoreThanFiveYear());
        }else {
            map.put("employeePersonSum", 0);
            map.put("seniorSchool", 0);
            map.put("junior", 0);
            map.put("regular", 0);
            map.put("oneTwoYears", 0);
            map.put("threeFiveYears", 0);
            map.put("moreThanFiveYear", 0);
        }
    }

    //体系信息
    public void basicSystemInformation(Map<String, Object> map, String ordersn) {
        map.put("securityProgram", "--");
        map.put("securityCode", "--");
        map.put("securityEffectiveCutoff", "--");
        map.put("securityOrg", "--");
        map.put("securityVerdict", "--");

        map.put("qualityProgram", "--");
        map.put("qualityCode", "--");
        map.put("qualityEffectiveCutoff", "--");
        map.put("qualityOrg", "--");
        map.put("qualityVerdict", "--");

        map.put("environmentProgram", "--");
        map.put("environmentCode", "--");
        map.put("environmentEffectiveCutoff", "--");
        map.put("environmentOrg", "--");
        map.put("environmentVerdict", "--");
        BasicSystemInformation basicSystemInformation = new BasicSystemInformation();
        basicSystemInformation.setOrderSn(ordersn);
        List<BasicSystemInformation> list = basicSystemInformationService.selectBasicSystemInformationList(basicSystemInformation);
        list.forEach(e -> {
            String securityVerdict = "公司获得了" + e.getCertificationBody() + "出具的认证证书，证书编号为" + e.getCertificationCode() +
                    "，证明公司符合" + e.getStandard() + "标准，认证范围为：" + e.getCertificationScope() + "，有效期至" + e.getEffectiveCutoff();
            switch (e.getType()) {
                case "1":
                    map.put("securityProgram", e.getCertificationProgram());
                    map.put("securityCode", e.getCertificationCode());
                    map.put("securityEffectiveCutoff", e.getEffectiveCutoff());
                    map.put("securityOrg", e.getCertificationBody());
                    map.put("securityVerdict", securityVerdict);
                    break;
                case "2":
                    map.put("qualityProgram", e.getCertificationProgram());
                    map.put("qualityCode", e.getCertificationCode());
                    map.put("qualityEffectiveCutoff", e.getEffectiveCutoff());
                    map.put("qualityOrg", e.getCertificationBody());
                    map.put("qualityVerdict", securityVerdict);
                    break;
                case "3":
                    map.put("environmentProgram", e.getCertificationProgram());
                    map.put("environmentCode", e.getCertificationCode());
                    map.put("environmentEffectiveCutoff", e.getEffectiveCutoff());
                    map.put("environmentOrg", e.getCertificationBody());
                    map.put("environmentVerdict", securityVerdict);
                    break;
            }
        });
    }

    //数据表
    public void basicDataTableService(Map<String, Object> map, String ordersn, BehavioralIndicator behavioralIndicator) {
        BasicDataTable basicDataTable = new BasicDataTable();
        basicDataTable.setOrderSn(ordersn);
        List<BasicDataTable> list = basicDataTableService.selectBasicDataTableList(basicDataTable);
        //1,现金
        List<BasicDataTable> cash = list.stream().filter(e -> e.getType().equals("1")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        List<Map<String, Object>> cashMaps = new ArrayList<>();
        cash.stream().forEach(e -> cashMaps.add(BeanUtils.parseObj2Map(e)));
        map.put("cashMaps", cashMaps);
        //2,利润
        List<BasicDataTable> profit = list.stream().filter(e -> e.getType().equals("2")).sorted((e1, e2) -> (e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        List<Map<String, Object>> profitMaps = new ArrayList<>();
        map.put("allTaking",0);
        map.put("retainedProfits",0);
        profit.stream().forEach(e -> {
            profitMaps.add(BeanUtils.parseObj2Map(e));
            if (e.getSortIndex() == 1) {
                //营业收入
                map.put("allTaking", e.getFour());
            }
            if (e.getSortIndex() == 4) {
                //净利润
                map.put("retainedProfits", e.getFour());

            }
        });
        map.put("profitMaps", profitMaps);

        //3负债，
        List<BasicDataTable> liabilities = list.stream().filter(e -> e.getType().equals("3")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        List<Map<String, Object>> liabilitiesMaps = new ArrayList<>();
        map.put("totalAssets",0);
        map.put("allTotalLiability",0);
        liabilities.stream().forEach(e -> {
            liabilitiesMaps.add(BeanUtils.parseObj2Map(e));
            if (e.getSortIndex() == 2) {
                //负债总额
                map.put("allTotalLiability", e.getFour().divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_UP));
            }
            if (e.getSortIndex() == 5) {
                //资产总额
                map.put("totalAssets", e.getFour().divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_UP));
            }
        });
        map.put("liabilitiesMaps", liabilitiesMaps);

        //4计算的总表
        List<BasicDataTable> sumTable = list.stream().filter(e -> e.getType().equals("4")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        List<Map<String, Object>> sumtaileMaps = new ArrayList<>();
        sumTable.stream().forEach(e -> sumtaileMaps.add(BeanUtils.parseObj2Map(e)));
        map.put("sumtaileMaps", sumtaileMaps);
        //发展能力
        List<Map<String,Object>>development=new ArrayList();
        sumtaileMaps.stream().forEach(e->{
            if ((Integer)e.get("sortIndex")>=12){
                development.add(e);
            }
        });
        map.put("developmentMaps",development);
        //5偿债能力
        List<BasicDataTable> ayinAbility = list.stream().filter(e -> e.getType().equals("5")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        StringBuilder ayinAbilityContexts=new StringBuilder("近三年公司资产负债率呈波动趋势，");
        for (BasicDataTable e:ayinAbility
             ) {
            if (e.getSortIndex()==1){
                if (e.getThree().compareTo(e.getFour())>=0){
                   ayinAbilityContexts.append(map.get("threeDate")+"年末为"+e.getThree()+"%,略高于行业平均水平，公司负债规模较大；");
                }else {
                    ayinAbilityContexts.append(map.get("threeDate")+"年末为"+e.getThree()+"%,略低于行业平均水平，公司负债规模一般；");
                }
            }else if(e.getSortIndex()==2){
                if (e.getThree().compareTo(e.getFour())>=0){
                    ayinAbilityContexts.append(map.get("threeDate")+"年当年现金流动负债比率为"+e.getThree()+"%,略高于行业平均水平，表明公司偿付短期债务的能力较强；");
                }else {
                    ayinAbilityContexts.append(map.get("threeDate")+"年当年现金流动负债比率为"+e.getThree()+"%,略低于行业平均水平，表明公司偿付短期债务的能力一般；");
                }
            }else if(e.getSortIndex()==3){
                if (e.getThree().compareTo(e.getFour())>=0){
                    ayinAbilityContexts.append(map.get("threeDate")+"年期末速动比率为"+e.getThree()+"%,高于行业平均水平，表明公司流动资产对流动负债覆盖能力较好；");
                }else {
                    ayinAbilityContexts.append(map.get("threeDate")+"年期末速动比率为"+e.getThree()+"%,低于行业平均水平，表明公司流动资产对流动负债覆盖能力一般；");
                }
            }

        }
        List<Map<String, Object>> ayinAbilityMaps = new ArrayList<>();
        ayinAbility.stream().forEach(e -> ayinAbilityMaps.add(BeanUtils.parseObj2Map(e)));
        map.put("ayinAbilityMaps", ayinAbilityMaps);
        map.put("ayinAbilityContexts",ayinAbilityContexts.toString());
        //6运营能力
        List<BasicDataTable> operation = list.stream().filter(e -> e.getType().equals("6")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        List<Map<String, Object>> operationMaps = new ArrayList<>();
        operation.stream().forEach(e -> operationMaps.add(BeanUtils.parseObj2Map(e)));
        map.put("operationMaps", operationMaps);
        map.put("operationContexts","--");
        StringBuilder operationContexts=new StringBuilder("");
        operation.forEach(e->{
            if (e.getSortIndex()==1){
                if (e.getThree().compareTo(e.getFour())>=0){
                    operationContexts.append("截止"+map.get("threeDate")+"年，总资产周转率为"+e.getThree()+"次,高于行业平均值，反映公司总资产周转速度较快，总资产利用效率高。\t\n");
                }else {
                    operationContexts.append("截止"+map.get("threeDate")+"年，总资产周转率为"+e.getThree()+"次,低于行业平均值，反映公司总资产周转速度较慢，总资产利用效率有待提升。\t\n");
                }
            }else if(e.getSortIndex()==2){
                if (e.getThree().compareTo(e.getFour())>=0){
                    operationContexts.append("截止"+map.get("threeDate")+"年，应收账款周转率为"+e.getThree()+"次,高于行业平均值，反映公司应收账款的收账速度较慢，账款回收能力强。\t\n");
                }else {
                    operationContexts.append("截止"+map.get("threeDate")+"年，应收账款周转率为"+e.getThree()+"次,低于行业平均值，反映公司应收账款的收账速度较慢，账款回收能力有待提升。\t\n");
                }
            }else if(e.getSortIndex()==3){
                if (e.getThree().compareTo(e.getFour())>=0){
                    operationContexts.append("截止"+map.get("threeDate")+"年，流动资产周转率为"+e.getThree()+"次,反映公司流动资产周转率速度较快，流动资产利用效率高。\t\n");
                }else {
                    operationContexts.append("截止"+map.get("threeDate")+"年，流动资产周转率为"+e.getThree()+"次,反映公司流动资产周转率速度较慢，流动资产利用效率有待提升。\t\n");
                }
            }
        });
        map.put("operationContexts",operationContexts);
        //7盈利能力
        List<BasicDataTable> profitability = list.stream().filter(e -> e.getType().equals("7")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        List<Map<String, Object>> profitabilityMaps = new ArrayList<>();
        profitability.stream().forEach(e -> profitabilityMaps.add(BeanUtils.parseObj2Map(e)));
        map.put("profitabilityMaps", profitabilityMaps);
        map.put("profitabilitySum","--");
        StringBuilder profitabilityContexts=new StringBuilder("");
        for (BasicDataTable e:profitability
             ) {
            if (e.getSortIndex()==1){
                if (e.getThree().compareTo(e.getFour())>=0){
                    profitabilityContexts.append("截止"+map.get("threeDate")+"年，净资产收益率为"+e.getThree()+"%,高于行业平均值，反映公司运用净资产的获利能力强。\t\n");
                }else {
                    profitabilityContexts.append("截止"+map.get("threeDate")+"年，净资产收益率为"+e.getThree()+"%,低于行业平均值，反映公司运用净资产的获利能力偏弱。\t\n");
                }
            }else if(e.getSortIndex()==2){
                if (e.getThree().compareTo(e.getFour())>=0){
                    profitabilityContexts.append("截止"+map.get("threeDate")+"年，营业利润率为"+e.getThree()+"%,高于行业平均值，反映公司主营业务盈利能力偏强。\t\n");
                }else {
                    profitabilityContexts.append("截止"+map.get("threeDate")+"年，营业利润率为"+e.getThree()+"%,低于行业平均值，反映公司主营业务盈利能力偏弱。\t\n");
                }
            }else if(e.getSortIndex()==3){
                if (e.getThree().compareTo(e.getFour())>=0){
                    profitabilityContexts.append("截止"+map.get("threeDate")+"年，盈余现金保障倍数（倍）为"+e.getThree()+"%,高于行业平均值，反映公司现金流强。\t\n");
                }else {
                    profitabilityContexts.append("截止"+map.get("threeDate")+"年，盈余现金保障倍数（倍）为"+e.getThree()+"%,低于行业平均值，反映公司现金流偏少。\t\n");
                }
            }
        }

        map.put("profitabilitySum",profitabilityContexts.toString());
        //8经营增长能力
        List<BasicDataTable> operational = list.stream().filter(e -> e.getType().equals("8")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        List<Map<String, Object>> operationalMaps = new ArrayList<>();
        operational.stream().forEach(e -> operationalMaps.add(BeanUtils.parseObj2Map(e)));
        StringBuilder operationalContext =new StringBuilder("");
        map.put("operationalContext","--");
        map.put("operationalMaps", operationalMaps);
        for (BasicDataTable e:operational
        ) {
            if (e.getSortIndex()==1){
                if (e.getThree().compareTo(e.getFour())>=0){
                    operationalContext.append("截止"+map.get("threeDate")+"年，销售（营业）增长率为"+e.getThree()+"%,高于行业平均值，反映公司经营能力强。\t\n");
                }else {
                    operationalContext.append("截止"+map.get("threeDate")+"年，销售（营业）增长率为"+e.getThree()+"%,低于行业平均值，反映公司经营能力偏弱。\t\n");
                }
            }else if(e.getSortIndex()==2){
                if (e.getThree().compareTo(e.getFour())>=0){
                    operationalContext.append("截止"+map.get("threeDate")+"年，销售（营业）利润增长率为"+e.getThree()+"%,高于行业平均值，反映公司业务盈利能力偏强。\t\n");
                }else {
                    operationalContext.append("截止"+map.get("threeDate")+"年，销售（营业）利润增长率为"+e.getThree()+"%,低于行业平均值，反映公司业务盈利能力偏弱。\t\n");
                }
            }
        }
        map.put("operationalContext",operationalContext.toString());

        //9现金流量分析
        List<BasicDataTable> cashFlowAnalysis = list.stream().filter(e -> e.getType().equals("9")).sorted((e1, e2) -> -(e1.getSortIndex() - e2.getSortIndex())).collect(Collectors.toList());
        if (cashFlowAnalysis != null && !cashFlowAnalysis.isEmpty()) {
            List<Map<String, Object>> cashFlowAnalysisMaps = new ArrayList<>();
            cashFlowAnalysis.stream().forEach(e -> {
                cashFlowAnalysisMaps.add(BeanUtils.parseObj2Map(e));
                if (e.getSortIndex() == 1) {
                    map.put("netCashInflows", e.getThree());
                }
                if (e.getSortIndex() == 2) {
                    map.put("netInflowS", e.getThree());
                }

            });
            map.put("cashFlowAnalysisMaps", cashFlowAnalysisMaps);
            map.put("ConclusionTheCash", "总体看，公司整体经营获现能力较强。");

        } else {
            map.put("ConclusionTheCash", "没有现金流：本次调查未获取被评企业的现金流量表，无法判断公司近期现金流动负债比率趋势走向，公司经营活动产生的净现金流对流动负债的保障能力有待进一步观察。");
        }

    }

    //专利信息
    public void patentInformation(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();

        BasicPatentInformation basicPatentInformation = new BasicPatentInformation();
        basicPatentInformation.setOrderSn(ordersn);
        List<BasicPatentInformation> list = basicPatentInformationService.selectBasicPatentInformationList(basicPatentInformation);
        map.put("patentInformationShow","结论：被评企业尚未有任何专利信息。");
        int sn = 1;
        for (BasicPatentInformation e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
            map.put("patentInformationShow","结论：以上调查截止于"+DateUtils.getDate("yyyy年MM月dd日"));
        }

        map.put("BasicPatentInformation", maps);
    }

    //软件著作权
    public void basicSoftwareCopyright(Map<String, Object> map, String ordersn) {
        map.put("softwareVerdict","结论：被评企业尚未申请任何软件著作权。");
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicSoftwareCopyright basicSoftwareCopyright = new BasicSoftwareCopyright();
        basicSoftwareCopyright.setOrderSn(ordersn);
        List<BasicSoftwareCopyright> list = basicSoftwareCopyrightService.selectBasicSoftwareCopyrightList(basicSoftwareCopyright);
        int sn = 1;
        for (BasicSoftwareCopyright e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        if (list!=null&&list.size()>0){
            map.put("softwareVerdict","结论：以上调查截止于"+DateUtils.getDate("yyyy年MM月dd日"));
        }
        map.put("BasicSoftwareCopyright", maps);
    }

    //著作权
    public void basicCopyright(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicCopyright basicCopyright = new BasicCopyright();
        basicCopyright.setOrderSn(ordersn);
        List<BasicCopyright> list = basicCopyrightService.selectBasicCopyrightList(basicCopyright);
        int sn = 1;
        for (BasicCopyright e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        map.put("BasicCopyright", maps);
    }

    //资质信息
    public void certifications(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicQualificationInformation BasicQualificationInformation = new BasicQualificationInformation();
        BasicQualificationInformation.setOrderSn(ordersn);
        List<BasicQualificationInformation> list = basicQualificationInformationService.selectBasicQualificationInformationList(BasicQualificationInformation);
        int sn = 1;
        for (BasicQualificationInformation e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        map.put("certifications", maps);
    }

    //行业地位
    public void basicPositionIndustry(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicPositionIndustry basicPositionIndustry = new BasicPositionIndustry();
        basicPositionIndustry.setOrderSn(ordersn);
        List<BasicPositionIndustry> list = basicPositionIndustryService.selectBasicPositionIndustryList(basicPositionIndustry);
        for (BasicPositionIndustry e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            maps.add(ma);
        }
        map.put("BasicPositionIndustry", maps);
    }

    //荣誉
    public void hoonr(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicHonor basicHonor = new BasicHonor();
        basicHonor.setOrderSn(ordersn);
        List<BasicHonor> list = basicHonorService.selectBasicHonorList(basicHonor);
        int sn = 1;
        for (BasicHonor e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        map.put("BasicHonor", maps);
    }

    //信用情况
    public void basicCreditRecord(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicCreditRecord basicCreditRecord = new BasicCreditRecord();
        basicCreditRecord.setOrderSn(ordersn);
        List<BasicCreditRecord> list = basicCreditRecordService.selectBasicCreditRecordList(basicCreditRecord);
        int sn = 1;
        for (BasicCreditRecord e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        if (list!=null&&list.size()>0){
            map.put("basicCreditRecordReslut","被评企业截止发现"+list.size()+"条违约情况");
        }else {
            map.put("basicCreditRecordReslut","未发现公司存在不良信用记录");
        }
        map.put("BasicCreditRecord", maps);
    }


    //裁判文书
    public void basicJudicativePaper(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicJudicativePaper basicJudicativePaper = new BasicJudicativePaper();
        basicJudicativePaper.setOrderSn(ordersn);
        Map<String, Object> map1=new HashMap<>();
        List<BasicJudicativePaper> list = basicJudicativePaperService.selectBasicJudicativePaperList(basicJudicativePaper);
        for (BasicJudicativePaper e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            maps.add(ma);
        }
        map.put("BasicJudicativePaper", maps);
    }

    //被执行企业
    public void basicExecutedEnterprise(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicExecutedEnterprise basicExecutedEnterprise = new BasicExecutedEnterprise();
        basicExecutedEnterprise.setOrderSn(ordersn);
        List<BasicExecutedEnterprise> list = basicExecutedEnterpriseService.selectBasicExecutedEnterpriseList(basicExecutedEnterprise);
        for (BasicExecutedEnterprise e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            maps.add(ma);
        }
        map.put("BasicExecutedEnterprise", maps);
    }

    //失信企业
    public void basicPromiseEnterprise(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicPromiseEnterprise basicPromiseEnterprise = new BasicPromiseEnterprise();
        basicPromiseEnterprise.setOrderSn(ordersn);
        List<BasicPromiseEnterprise> list = basicPromiseEnterpriseService.selectBasicPromiseEnterpriseList(basicPromiseEnterprise);
        for (BasicPromiseEnterprise e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            maps.add(ma);
        }
        map.put("BasicPromiseEnterprise", maps);
    }

    //中标履约情况
    public void basicWinBidding(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicWinBidding basicWinBidding = new BasicWinBidding();
        basicWinBidding.setOrderSn(ordersn);
        List<BasicWinBidding> list = basicWinBiddingService.selectBasicWinBiddingList(basicWinBidding);
        int sn = 1;
        int cout=0;
        for (BasicWinBidding e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
            if (e.getState().equals("否")) {
                cout += 1;
            }
        }
        if (cout>0){
            map.put("BasicWinBiddingContext","该公司出现"+cout+"条未履约。");
        }else {
            map.put("BasicWinBiddingContext","该公司未出现未履约情况。");

        }
        map.put("BasicWinBidding", maps);
    }

    //合同纠纷
    public void baiscContractDisputes(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BaiscContractDisputes baiscContractDisputes = new BaiscContractDisputes();
        baiscContractDisputes.setOrderSn(ordersn);
        List<BaiscContractDisputes> list = baiscContractDisputesService.selectBaiscContractDisputesList(baiscContractDisputes);
        int sn = 1;
        for (BaiscContractDisputes e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        if (list != null && !list.isEmpty()) {
            map.put("contractDisputesConclusion", "结论：被评企业近三年有合同纠纷，合同履约情况一般。");
        } else {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("sn", "----");
            map1.put("decisonTime", "----");
            map1.put("decisoncode", "----");
            map1.put("defendant", "----");
            map1.put("result", "----");
            maps.add(map1);
            map.put("contractDisputesConclusion", "结论：被评企业近三年无合同纠纷，合同履约情况优秀。");
        }
        map.put("BaiscContractDisputes", maps);
    }

    //获奖情况
    public void basicScholarshipPrize(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicScholarshipPrize basicScholarshipPrize = new BasicScholarshipPrize();
        basicScholarshipPrize.setOrderSn(ordersn);
        List<BasicScholarshipPrize> list = basicScholarshipPrizeService.selectBasicScholarshipPrizeList(basicScholarshipPrize);
        int sn = 1;
        for (BasicScholarshipPrize e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        if (list != null && !list.isEmpty()) {
        } else {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("sn", "----");
            map1.put("name", "----");
            map1.put("theunit", "----");
            map1.put("winingTime", "----");
            maps.add(map1);
        }
        map.put("BasicScholarshipPrize", maps);
    }

    //历史等级情况
    public void historyClass(Map<String, Object> map, String ordersn) {
        List<Map<String, Object>> maps = new ArrayList<>();
        BasicHistoryClass basicHistoryClass = new BasicHistoryClass();
        basicHistoryClass.setOrderSn(ordersn);
        List<BasicHistoryClass> list = basicHistoryClassService.selectBasicHistoryClassList(basicHistoryClass);
        int sn = 1;
        for (BasicHistoryClass e : list
        ) {
            Map<String, Object> ma = BeanUtils.parseObj2Map(e);
            ma.put("sn", sn);
            maps.add(ma);
            sn += 1;
        }
        if (list != null && !list.isEmpty()) {
        } else {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("sn", "----");
            map1.put("year", "----");
            map1.put("grade", "----");
            map1.put("ratingfirms", "----");
            maps.add(map1);
        }
        map.put("HistoryClass", maps);
    }


}
