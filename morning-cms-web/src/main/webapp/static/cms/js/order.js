/**
 * 进行格式转换
 */
function timeFormatter(value) {
	return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
}
function payFormatter(value) {
	if (value == 1) {
		return '<span class="label label-primary">在线支付</span>'
	} else if (value == 0) {
		return '<span class="label label-danger">线下支付</span>'
	}
}
function shipmentTimeFormatter(value) {
	if (value == 3){
        return '<span class="label label-info">双休日、假日送货</span>'
	}
	else if (value == 2) {
        return '<span class="label label-info">工作日送货</span>'
	} else if (value == 1) {
        return '<span class="label label-info">不限送货时间</span>'
	}
}
function shipmentTypeFormatter(value) {
    if (value == 1){
        return '<span class="label label-info">附加运费</span>'
    }
    else if (value == 0) {
        return '<span class="label label-info">免运费</span>'
    }
}

function invoiceTypeFormatter(value) {
	if (value == 1){
		return '<span class="label label-info">不开发票</span>'
	}
	else if (value == 2){
		return '<span class="label label-info">电子发票</span>'
	}
	else if (value == 3){
        return '<span class="label label-info">普通发票</span>'
	}
}

function orderStatusFormatter(value) {
    if (value == 1){
        return '<span class="label label-warning">订单提交</span>'
    }
    else if (value == 2){
        return '<span class="label label-primary">已付款</span>'
    }
    else if (value == 3){
        return '<span class="label label-info">拣取配货</span>'
    }
    else if (value == 4){
        return '<span class="label label-info">商品出库</span>'
    }
    else if (value == 5){
        return '<span class="label label-info">等待收货</span>'
    }
    else if (value == 6){
        return '<span class="label label-success">确认收货</span>'
    }
    else if (value == 11){
        return '<span class="label label-danger">自动取消订单</span>'
    }
    else if (value == 12){
        return '<span class="label label-danger">手动取消订单</span>'
    }
}

function actionFormatter(value, row, index) {
    return [
        '<a class="product m-r-sm text-primary" href="javascript:void(0)" title="订单商品详情">',
        '<i class="glyphicon glyphicon-sort-by-attributes-alt"></i>',
        '</a>',
        '<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>',
        '<a class="remove m-r-sm text-danger" href="javascript:void(0)" title="取消订单">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>',
    ].join('');
}

window.actionEvents = {
	'click .edit' : function(e, value, row, index) {
		layer_show(row.name, baselocation + '/system/order/list/' + row.orderId + '/edit', 900, 650)
	},
	'click .remove' : function(e, value, row, index) {
		admin_delete(index, row.orderId);
	},
	'click .product' : function(e, value, row, index) {
		window.location.href = baselocation + '/system/order/list/' + row.orderId + '/product/view';
	}
};

/**
 * 取消订单
 */
function admin_delete(index, value) {
	layer.confirm('确认要取消该订单吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'delete',
			dataType : 'json',
			url : baselocation + '/system/order/list/delete/' + value,
			success : function(result) {
				if (result.code == 1) {
                    $('#table').bootstrapTable('updateCell', {
                        index : index,
                        field : 'orderStatus',
                        value : 12
                    });
					layer.msg('该订单取消成功!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	});
}

/**
 * 多选框插件
 */
$(document).ready(function() {
	$('input').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
});

/**
 * 系统提示
 */
$(function() {
	$('.status-tip').on("click", function() {
		layer.tips('"显示" 代表此数据可用<br>"隐藏" 代表此数据不可用', '.status-tip');
	})
})


/**
 * 表单验证
 */
$(function() {
	$('#form').bootstrapValidator({
		container : 'tooltip',
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			'orderNumber' : {
				message : '订单号码验证失败',
				validators : {
					notEmpty : {
						message : '订单号码不能为空'
					},
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '订单号码只能为数字'
                    }
				}
			},
			'userId' : {
				message : '用户ID验证失败',
				validators : {
					notEmpty : {
						message : '用户ID不能为空'
					},
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '用户只能为数字'
                    }
				}
			},	
			'payType' : {
				message : '支付方式验证失败',
				validators : {
					notEmpty : {
						message : '支付方式不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '支付方式只能为数字'
		            }
				}
			},
			'shipmentTime' : {
				message : '配送时间验证失败',
				validators : {
					notEmpty : {
						message : '配送时间不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '配送时间只能为数字'
		            }
				}
			},	
			'shipmentType' : {
				message : '配送方式验证失败',
				validators : {
					notEmpty : {
						message : '配送方式不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '配送方式只能为数字'
		            }
				}
			},
            'invoiceType' : {
                message : '是否发票验证失败',
                validators : {
                    notEmpty : {
                        message : '是否发票不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '是否发票只能为数字'
                    }
                }
            },
            'orderStatus' : {
                message : '订单状态验证失败',
                validators : {
                    notEmpty : {
                        message : '订单状态不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '订单状态只能为数字'
                    }
                }
            },'orderAmount' : {
                message : '订单金额验证失败',
                validators : {
                    notEmpty : {
                        message : '订单金额不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '订单金额只能为数字'
                    }
                }
            },'userName' : {
                message : '收货人验证失败',
                validators : {
                    notEmpty : {
                        message : '收货人不能为空'
                    },
                }
            },'userPhone' : {
            	message : '手机号码验证失败',
            	validators : {
                	notEmpty : {
                    	message : '手机号码不能为空'
                	},
                	regexp: {
                    	regexp: /^[0-9]*$/,
                    	message: '手机号码只能为数字'
                	}
            	}
        	},'provinceName' : {
                message : '省验证失败',
                validators : {
                    notEmpty : {
                        message : '省不能为空'
                    },
                }
            },'cityName' : {
                message : '市验证失败',
                validators : {
                    notEmpty : {
                        message : '市不能为空'
                    },
                }
            },'districtName' : {
                message : '区验证失败',
                validators : {
                    notEmpty : {
                        message : '区不能为空'
                    },
                }
            },'userAdress' : {
                message : '详细地址验证失败',
                validators : {
                    notEmpty : {
                        message : '详细地址不能为空'
                    },
                }
            },'userZipcode' : {
                message : '邮编验证失败',
                validators : {
                    notEmpty : {
                        message : '邮编不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '邮编只能为数字'
                    }
                }
            },
		}
	})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');
			
			var method = $('#form').attr('data-method');
			// Use Ajax to submit form data
			if (method == 'put') {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'put',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("更新订单成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.message, {
								icon : 2,
								time : 1000
							});
						}
					}
				})
			}
		});
})