package com.zjl.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjl.bo.deal.DealBo;
import com.zjl.pojo.Admin;
import com.zjl.pojo.Deal;
import com.zjl.pojo.Stock;
import com.zjl.pojo.Worker;
import com.zjl.query.deal.AddDealQuery;
import com.zjl.query.deal.UpdateDealQuery;
import com.zjl.result.ResultBean;
import com.zjl.service.DealService;
import com.zjl.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/deal")
@Api(tags = "订单管理接口")
@Transactional
public class DealController {
    @Autowired
    private DealService dealService;
    @Autowired
    private StockService stockService;

    @PostMapping("/addDeal")
    @ApiOperation("代下订单")
    public ResultBean<Void> addDeal(@RequestBody @Valid AddDealQuery addDealQuery) {
        ResultBean<Void> resultBean = new ResultBean<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String name = null;
        Stock stock = stockService.getById(addDealQuery.getDealStockId());
        if (stock == null) {
            return resultBean.setTimestamp(System.currentTimeMillis())
                    .setData(null)
                    .setMsg("不存在此商品")
                    .setCode(HttpStatus.OK.value());
        }
        Deal deal = new Deal();
        deal.setDealName(addDealQuery.getDealName());
        deal.setDealCustom(addDealQuery.getDealCustom());
        deal.setDealPeople(addDealQuery.getDealPeople());
        deal.setDealAddr(addDealQuery.getDealAddr());
        deal.setDealTel(addDealQuery.getDealTel());
        deal.setDealTime(new Date());
        if (principal instanceof Worker) {
            name = ((Worker) principal).getWorkName();
        } else if (principal instanceof Admin) {
            name = ((Admin) principal).getUkAdminUsername();
        }
        deal.setDealName(addDealQuery.getDealName());
        deal.setDealWorker(name);
        deal.setDealStock(stock.getStockName());
        deal.setDealNum(addDealQuery.getDealNum());
        deal.setDealUnit(stock.getStockUnit());
        deal.setDealDetail(addDealQuery.getDealDetail());
        deal.setDealState("0");
        stock.setStockOut(stock.getStockOut().add(deal.getDealNum()));
        stock.setStockLeft(stock.getStockLeft().subtract(deal.getDealNum()));
        if (stock.getStockLeft().compareTo(BigDecimal.valueOf(0)) == -1) {
            return resultBean.setTimestamp(System.currentTimeMillis())
                    .setData(null)
                    .setMsg("库存不足")
                    .setCode(HttpStatus.OK.value());
        }
        boolean update = stockService.updateById(stock);
        boolean save = dealService.save(deal);
        if (save&&update) {
            resultBean.setCode(HttpStatus.OK.value())
                    .setMsg("下单成功");
        } else {
            resultBean.setMsg("未知原因下单失败")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        return resultBean.setTimestamp(System.currentTimeMillis());
    }

    @PutMapping("/updateDeal")
    @ApiOperation("")
    public ResultBean<Void> updateDeal(@RequestBody @Valid UpdateDealQuery updateDealQuery) {
        ResultBean<Void> resultBean = new ResultBean<>();
        Deal deal = dealService.getById(updateDealQuery.getId());
        if (deal == null) {
            return resultBean.setTimestamp(System.currentTimeMillis())
                    .setData(null)
                    .setMsg("不存在此商品")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        if (!deal.getDealState().equals("0")) {
            return resultBean.setTimestamp(System.currentTimeMillis())
                    .setData(null)
                    .setMsg("已出库，无法修改")
                    .setCode(HttpStatus.BAD_REQUEST.value());
        }
        deal.setDealName(updateDealQuery.getDealName());
        deal.setDealCustom(updateDealQuery.getDealCustom());
        deal.setDealPeople(updateDealQuery.getDealPeople());
        deal.setDealAddr(updateDealQuery.getDealAddr());
        deal.setDealTel(updateDealQuery.getDealTel());
        deal.setDealNum(updateDealQuery.getDealNum());
        deal.setDealDetail(updateDealQuery.getDealDetail());
        dealService.updateById(deal);
        return resultBean.setTimestamp(System.currentTimeMillis())
                .setData(null)
                .setMsg("更新成功")
                .setCode(HttpStatus.OK.value());
    }

    @GetMapping("/queryDeal/{pageNum}")
    @ApiOperation("查询订单列表")
    public ResultBean<IPage<DealBo>> queryDeal(@PathVariable("pageNum") Integer pageNum) {
        return dealService.queryAll(pageNum);
    }


    @GetMapping("/getExcel")
    @ApiOperation("导出excel")
    public void getExcel(ModelMap map,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        List<DealBo> list = dealService.getList();
        ExportParams params = new ExportParams("订单列表", "订单列表", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, DealBo.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "订单表");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}

