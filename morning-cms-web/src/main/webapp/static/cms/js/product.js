/**
 * 进行格式转换
 */
function timeFormatter(value) {
	return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
}

function picImgFormatter(value, row) {
    return '<a href="' + imagelocation + '/' + row.picImg + '" target="_blank" title="' + row.name + '">' + value + '</a>';
}

function labelIdFormatter(value) {
	if (value == 1){
        return '<span class="label label-danger">热销</span>'
	}
	else if (value == 2){
        return '<span class="label label-info">新品</span>'
	}
	else if (value == 3){
        return '<span class="label label-primary">现货</span>'
	}
	else if (value == 4){
        return '<span class="label label-warning">有赠品</span>'
	}
}

function showInShelveFormatter(value) {
    if (value == 1){
        return '<span class="label label-info">已上架</span>'
    }
    else if (value == 0){
        return '<span class="label label-warning">已下架</span>'
    }
}

function actionFormatter(value, row, index) {
	if (row.showInShelve == 1) {
		return [
			'<a class="freeze m-r-sm text-info" href="javascript:void(0)" title="下架">',
			'<i class="glyphicon glyphicon-pause"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="log m-r-sm text-primary" href="javascript:void(0)" title="产品详情">',
			'<i class="glyphicon glyphicon-sort-by-attributes-alt"></i>',
			'</a>',
		].join('');
	} else {
		return [
			'<a class="normal m-r-sm text-info" href="javascript:void(0)" title="上架">',
			'<i class="glyphicon glyphicon-play"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="log m-r-sm text-primary" href="javascript:void(0)" title="产品详情">',
			'<i class="glyphicon glyphicon-sort-by-attributes-alt"></i>',
			'</a>',
		].join('');
	}
}

window.actionEvents = {
	'click .freeze' : function(e, value, row, index) {
		status_stop(index, row.productId);
	},
	'click .normal' : function(e, value, row, index) {
		status_start(index, row.productId);
	},
	'click .edit' : function(e, value, row, index) {
		layer_show(row.name, baselocation + '/product/list/' + row.productId + '/edit', 900, 650)
	},
	'click .log' : function(e, value, row, index) {
		window.location.href = baselocation + '/product/list/' + row.productId + '/detail/view';
	}
};

/**
 * 隐藏产品
 */
function status_stop(index, value) {
	layer.confirm('确认要下架该产品吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
			url : baselocation + '/product/list/' + value + '/audit',
			success : function(result) {
				if (result.code == 1) {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
                            showInShelve : 0,
						}
					});
					layer.msg('该产品下架成功!', {
						icon : 5,
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
 * 上架产品
 */
function status_start(index, value) {
	layer.confirm('确认要上架该产品吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
			url : baselocation + '/product/list/' + value + '/audit',
			success : function(result) {
				if (result.code == 1) {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
                            showInShelve : 1,
						}
					});
					layer.msg('该产品上架成功!', {
						icon : 6,
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
            'name' : {
                message : '商品名称验证失败',
                validators : {
                    notEmpty : {
                        message : '商品名称不能为空'
                    },
                }
            },
			'showScore' : {
				message : '积分验证失败',
				validators : {
					notEmpty : {
						message : '积分不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '积分只能为数字'
		            }
				}
			},
			'showPrice' : {
				message : '价钱验证失败',
				validators : {
					notEmpty : {
						message : '价钱不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '价钱只能为数字'
		            }
				}
			},
            'paramNames' : {
                message : '参数名称验证失败',
                validators : {
                    notEmpty : {
                        message : '参数名称不能为空'
                    },
                }
            },
            'paramValues' : {
                message : '参数值验证失败',
                validators : {
                    notEmpty : {
                        message : '参数值不能为空'
                    },
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

            ztreeObject = $.fn.zTree.getZTreeObj("ztree");
            var nodes = ztreeObject.getCheckedNodes(true);
            var categoryId = '';
            if (nodes != null && nodes.length > 0) {
                for (var i = 0; i < nodes.length; i++) {
                    categoryId = nodes[i].categoryId;
                }
            }
            var params = '';
            params += $form.serialize();
            params += "&categoryId=" + categoryId;

			var method = $('#form').attr('data-method');
			// Use Ajax to submit form data
			if (method == 'put') {
				$.ajax({
					data : params,
					dataType : 'json',
					type : 'put',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("更新商品成功!", {
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
			} else if (method == 'post') {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'post',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("创建商品成功!", {
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

/**
 * 查看按钮
 */
$(function() {
    $('.view-button').on("click", function() {
        if ($('input[name="picImg"]').val() != null && $('input[name="picImg"]').val() != "") {
            window.open(imagelocation + '/' + $('input[name="picImg"]').val());
        }
    })
})

/**
 * 图片上传
 */
$(function() {
    $('.upload-button').on("click", function() {
        parent.layer.msg("图片上传成功!", {
            shade : 0.3,
            time : 1500
        });
        $('input[name="picImg"]').val("images/goods/20170226/1471797894445.jpg");
    })
})

var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

elems.forEach(function(html) {
    var switchery = new Switchery(html, {
        size : 'small'
    });
});

/**
 * 初始化菜单树
 */
var ztreeObject;
var setting = {
    data : {
        simpleData : {
            enable : true,
            idKey : "categoryId",
            pIdKey : "parentId",
            rootPId : 0
        },
        key : {
            name : 'name',
            title : 'name'
        }
    },
    check : {
        enable : true,
        chkboxType:  { "Y": "", "N": "" },
		chkStyle:"radio",
        radioType : "all"
    }
};
$(function() {
    treedata = eval('(' + treedata + ')');
    ztreeObject = $.fn.zTree.init($("#ztree"), setting, treedata);
    //展开所有节点
    ztreeObject.expandAll(true);
})

function deleteParamInput(index){
    $("#"+index).remove();
    $("div.paramFormGroup:first > label").html("商品参数：");
}
function addParamInput() {
    var id=$("div.paramFormGroup").length-1;
    var html="<div class=\"form-group m-t paramFormGroup\" id=\""+id+"\">" +
        "                  <label class=\"col-sm-2 col-xs-offset-1 control-label\"></label>" +
        "                  <div class=\"col-sm-2\">" +
        "                    <input type=\"text\" class=\"form-control\" name=\"paramNames\">" +
        "                  </div>" +
        "                  <div class=\"col-sm-5\">" +
        "                    <input type=\"text\" class=\"form-control\" name=\"paramValues\">" +
        "                  </div>" +
        "                  <div class=\"col-sm-1\">" +
        "                    <button type=\"button\" class=\"btn btn-default\" onclick=\"deleteParamInput("+id+")\"><i class=\"glyphicon glyphicon-minus\"></i></button>" +
        "                  </div>" +
        "                </div>";
    $("div.paramFormGroup:last").before(html);
    $("div.paramFormGroup > label").html("");
    $("div.paramFormGroup:first > label").html("商品参数：");
}