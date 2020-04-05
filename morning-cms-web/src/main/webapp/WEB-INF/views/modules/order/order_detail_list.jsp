<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>广告详情管理 - 叮咚Morning</title>
<link rel="stylesheet" href="${ctxsta}/common/bootstrap-table/bootstrap-table.min.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>${order.orderNumber} -- 订单详情管理</h5>
          <div class="ibox-tools"> <a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a class="close-link"><i class="fa fa-times"></i></a> </div>
        </div>
        <div class="ibox-content">
          <div class="row row-lg">
            <div class="col-sm-12">
              <div class="example-wrap">
                <div class="example">
                  <div id="toolbar" class="btn-group m-t-sm">
                 	<button type="button" class="btn btn-default"  title="返回上一页" onclick="javascript:window.history.back();"> <i class="glyphicon glyphicon-circle-arrow-left"></i> </button>
                  </div>
                  <table id="table"
                         data-toggle="table"
                         data-height="600"
                         data-search="true"
                         data-show-refresh="true"
                         data-show-toggle="true"
                         data-show-export="true"
                         data-show-pagination-switch="true"
                         data-show-columns="true"
                         data-url="${ctx}/system/order/list/${order.orderId}/product/"
                         data-pagination="true"
                         data-page-size="20"
                         data-page-list="[20, 50, 100, 200]"
                         data-side-pagination="server"
                         data-striped="true"
                         data-sort-order="asc"
                         data-toolbar="#toolbar">
                    <thead>
                      <tr>
                        <th data-field="orderProductId" data-halign="center" data-align="center" data-sortable="true">订单商品ID</th>
                        <th data-field="productNumber" data-halign="center" data-align="center" data-sortable="true">商品编号</th>
                        <th data-field="name" data-halign="center" data-align="center" data-sortable="true">商品名称</th>
                        <th data-field="picImg" data-formatter="picImgFormatter" data-halign="center" data-align="center" data-sortable="true">商品图片</th>
                        <th data-field="productSpecName" data-halign="center" data-align="center" data-sortable="true">商品规格</th>
                        <th data-field="price" data-halign="center" data-align="center" data-sortable="true">单价</th>
                        <th data-field="buyNumber" data-halign="center" data-align="center" data-sortable="true">数量</th>
                        <th data-field="productAmount" data-halign="center" data-align="center" data-sortable="true">总价</th>
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
  <script src="${ctxsta}/cms/js/orderProduct.js"></script>
</myfooter>
</body>
</html>