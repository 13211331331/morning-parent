<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>订单管理 - 叮咚Morning</title>
<link rel="stylesheet" href="${ctxsta}/common/bootstrap-table/bootstrap-table.min.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>订单管理</h5>
          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
        </div>
        <div class="ibox-content">
          <div class="row row-lg">
            <div class="col-sm-12">
              <div class="example-wrap">
                <div class="example">
                  <%--<div id="toolbar" class="btn-group m-t-sm">
                    <shiro:hasPermission name="order:create">
                      <button type="button" class="btn btn-default"  title="创建广告位" onclick="layer_show('创建广告位','${ctx}/online/advert/create','800','700')"> <i class="glyphicon glyphicon-plus"></i> </button>
                    </shiro:hasPermission>
                  </div>--%>
                  <table id="table"
                         data-toggle="table"
                         data-height="600"
                         data-search="true"
                         data-show-refresh="true"
                         data-show-toggle="true"
                         data-show-export="true"
                         data-show-pagination-switch="true"
                         data-show-columns="true"
                         data-url="${ctx}/system/order/list/"
                         data-pagination="true"
                         data-page-size="20"
                         data-page-list="[20, 50, 100, 200]"
                         data-side-pagination="server"
                         data-striped="true"
                         data-sort-order="desc"
                         data-toolbar="#toolbar">
                    <thead>
                      <tr>
                        <th data-field="orderId" data-halign="center" data-align="center" data-sortable="true">订单id</th>
                        <th data-field="orderNumber" data-halign="center" data-align="center" data-sortable="true">订单号码</th>
                        <th data-field="userId" data-halign="center" data-align="center" data-sortable="true">用户编号</th>
                        <th data-field="payType" data-formatter="payFormatter" data-halign="center" data-align="center" data-sortable="true">支付方式</th>
                        <th data-field="shipmentTime" data-formatter="shipmentTimeFormatter" data-halign="center" data-align="center" data-sortable="true">配送时间</th>
                        <th data-field="shipmentType" data-formatter="shipmentTypeFormatter" data-halign="center" data-align="center" data-sortable="true">配送方式</th>
                        <th data-field="invoiceType" data-formatter="invoiceTypeFormatter" data-halign="center" data-align="center" data-sortable="true">是否发票</th>
                        <th data-field="orderStatus" data-formatter="orderStatusFormatter" data-halign="center" data-align="center" data-sortable="true">订单状态</th>
                        <th data-field="buyNumber"  data-halign="center" data-align="center" data-sortable="true">商品数量</th>
                        <th data-field="payAmount"  data-halign="center" data-align="center" data-sortable="true">总金额</th>
                        <th data-field="createTime" data-formatter="timeFormatter" data-halign="center" data-align="center" data-sortable="true">生成时间</th>
                        <th data-field="updateTime" data-formatter="timeFormatter" data-halign="center" data-align="center" data-sortable="true">更新时间</th>
                        <th data-formatter="actionFormatter" data-events="actionEvents" data-halign="center" data-align="center" data-sortable="true">操作</th>
                      </tr>
                    </thead>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<myfooter> 
  <!-- Bootstrap table --> 
  <script src="${ctxsta}/common/bootstrap-table/bootstrap-table.min.js"></script> 
  <script src="${ctxsta}/common/bootstrap-table/extensions/export/bootstrap-table-export.js"></script> 
  <script src="${ctxsta}/common/bootstrap-table/tableExport.js"></script> 
  <script src="${ctxsta}/common/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script> 
  <!-- 自定义js --> 
  <script src="${ctxsta}/cms/js/order.js"></script>
</myfooter>
</body>
</html>