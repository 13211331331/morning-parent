<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>更新订单- 叮咚Morning</title>
<link rel="stylesheet" href="${ctxsta}/common/icheck/flat/green.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>更新订单<small> 订单信息时应当遵循合法、正当、必要的原则，明示目的、方式和范围。</small></h5>
          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
        </div>
        <div class="ibox-content">
          <form id="form" class="form-horizontal" action="${ctx}/system/order/list/${order.orderId}" data-method="put">
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">订单号码：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="orderNumber" disabled value="${order.orderNumber}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group m-t">
              <label class="col-sm-2 col-xs-offset-1 control-label">用户ID：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="userId" disabled value="${order.userId}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">支付方式：</label>
              <div class="col-sm-7">
                <label class="radio-inline">
                  <input type="radio" name="payType" value="0" ${order.payType eq '0'?'checked="checked"':''}>
                  线下支付</label>
                <label class="radio-inline">
                  <input type="radio" name="payType" value="1" ${order.payType eq '1'?'checked="checked"':''}>
                  线上支付</label>
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">配送时间：</label>
              <div class="col-sm-7">
                <label class="radio-inline">
                  <input type="radio" name="shipmentTime" value="1" ${order.shipmentTime eq '1'?'checked="checked"':''}>
                  不限配送时间</label>
                <label class="radio-inline">
                  <input type="radio" name="shipmentTime" value="2" ${order.shipmentTime eq '2'?'checked="checked"':''}>
                  工作日配送</label>
                <label class="radio-inline">
                  <input type="radio" name="shipmentTime" value="3" ${order.shipmentTime eq '3'?'checked="checked"':''}>
                  双休日、节假日配送</label>
              </div>
            </div>
            <div class="hr-line-dashed"></div>    
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">配送方式：</label>
              <div class="col-sm-7">
                <label class="radio-inline">
                  <input type="radio" name="shipmentType" value="0" ${order.shipmentType eq '0'?'checked="checked"':''}>
                  免运费</label>
                <label class="radio-inline">
                  <input type="radio" name="shipmentType" value="1" ${order.shipmentType eq '1'?'checked="checked"':''}>
                  附加运费</label>
              </div>
            </div>
            <div class="hr-line-dashed"></div>                       
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">是否发票：</label>
              <div class="col-sm-9">
                <label class="radio-inline">
                  <input type="radio" name="invoiceType" value="1" ${order.invoiceType eq '1'?'checked="checked"':''}>
                  不开发票</label>
                <label class="radio-inline">
                  <input type="radio" name="invoiceType" value="2" ${order.invoiceType eq '2'?'checked="checked"':''}>
                  电子发票</label>
                <label class="radio-inline">
                  <input type="radio" name="invoiceType" value="3" ${order.invoiceType eq '3'?'checked="checked"':''}>
                  普通发票</label>
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">订单状态：</label>
              <div class="col-sm-7">
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="1" ${order.orderStatus eq '1'?'checked="checked"':''}>
                  订单提交</label>
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="2" ${order.orderStatus eq '2'?'checked="checked"':''}>
                  已付款</label>
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="3" ${order.orderStatus eq '3'?'checked="checked"':''}>
                  拣取配货</label>
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="4" ${order.orderStatus eq '4'?'checked="checked"':''}>
                  商品出库</label>
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="5" ${order.orderStatus eq '5'?'checked="checked"':''}>
                  等待收货</label>
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="6" ${order.orderStatus eq '6'?'checked="checked"':''}>
                  确认收货</label>
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="11" ${order.orderStatus eq '11'?'checked="checked"':''}>
                  自动取消订单</label>
                <label class="radio-inline">
                  <input type="radio" name="orderStatus" value="12" ${order.orderStatus eq '12'?'checked="checked"':''}>
                  手动取消订单</label>
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <label class="col-sm-2 col-xs-offset-1 control-label">总金额：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="orderAmount" value="${order.payAmount}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <input type="hidden" class="form-control" name="orderShipmentId" value="${order.orderShipment.orderShipmentId}" >
              <label class="col-sm-2 col-xs-offset-1 control-label">收货人：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="userName" value="${order.orderShipment.userName}">
              </div>
              <label class="col-sm-2 col-xs-offset-1 control-label">手机号码：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="userPhone" value="${order.orderShipment.userPhone}">
              </div>
              <label class="col-sm-2 col-xs-offset-1 control-label">社区：</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" name="provinceName" value="${order.orderShipment.provinceName}">
              </div>
              <div class="col-sm-3">
                <input type="text" class="form-control" name="cityName" value="${order.orderShipment.cityName}">
              </div>
              <div class="col-sm-3">
                <input type="text" class="form-control" name="districtName" value="${order.orderShipment.districtName}">
              </div>
              <label class="col-sm-2 col-xs-offset-1 control-label">详细地址：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="userAdress" value="${order.orderShipment.userAdress}">
              </div>
              <label class="col-sm-2 col-xs-offset-1 control-label">邮编：</label>
              <div class="col-sm-7">
                <input type="text" class="form-control" name="userZipcode" value="${order.orderShipment.userZipcode}">
              </div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group">
              <div class="col-sm-12 text-center">
                <button class="btn btn-primary" type="submit">提交</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<myfooter> 
  <!-- 自定义js --> 
  <script src="${ctxsta}/cms/js/order.js"></script>
</myfooter>
</body>
</html>
