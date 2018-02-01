[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.storagePlugin.list")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/webuploader.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $listTable = $("#listTable");
	var $install = $("#listTable a.install");
	var $uninstall = $("#listTable a.uninstall");
	
	//文件上传
	var settings = {fileType: "all",
	                extensions:"jpg,jpeg,bmp,gif,png,swf,flv,mp3,wav,avi,rm,rmvb,zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx"};
	
	var $filePicker = $("#filePicker");
	
	[@flash_message /]
	
	//文件上传
	$filePicker.uploader(settings);

	// 安装
	$install.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.storagePlugin.installConfirm")}",
			onOk: function() {
				$.ajax({
					url: $this.attr("href"),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(message) {
						if (message.type == "success") {
							location.reload(true);
						} else {
							$.message(message);
						}
					}
				});
			}
		});
		return false;
	});
	
	// 卸载
	$uninstall.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.storagePlugin.uninstallConfirm")}",
			onOk: function() {
				$.ajax({
					url: $this.attr("href"),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(message) {
						if (message.type == "success") {
							location.reload(true);
						} else {
							$.message(message);
						}
					}
				});
			}
		});
		return false;
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.storagePlugin.list")} <span>(${message("admin.page.total", storagePlugins?size)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<div class="buttonGroup">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<span>${message("StoragePlugin.name")}</span>
				</th>
				<th>
					<span>${message("StoragePlugin.version")}</span>
				</th>
				<th>
					<span>${message("StoragePlugin.author")}</span>
				</th>
				<th>
					<span>${message("StoragePlugin.isEnabled")}</span>
				</th>
				<th>
					<span>${message("admin.common.action")}</span>
				</th>
			</tr>
			[#list storagePlugins as storagePlugin]
				<tr>
					<td>
						[#if storagePlugin.siteUrl??]
							<a href="${storagePlugin.siteUrl}" target="_blank">${storagePlugin.name}</a>
						[#else]
							${storagePlugin.name}
						[/#if]
					</td>
					<td>
						${storagePlugin.version!'-'}
					</td>
					<td>
						${storagePlugin.author!'-'}
					</td>
					<td>
						<span class="${storagePlugin.isEnabled?string("true", "false")}Icon">&nbsp;</span>
					</td>
					<td>
						[#if storagePlugin.isInstalled]
							[#if storagePlugin.settingUrl??]
								<a href="${storagePlugin.settingUrl}">[${message("admin.storagePlugin.setting")}]</a>
							[/#if]
							[#if storagePlugin.uninstallUrl??]
								<a href="${storagePlugin.uninstallUrl}" class="uninstall">[${message("admin.storagePlugin.uninstall")}]</a>
							[/#if]
						[#else]
							[#if storagePlugin.installUrl??]
								<a href="${storagePlugin.installUrl}" class="install">[${message("admin.storagePlugin.install")}]</a>
							[/#if]
						[/#if]
					</td>
				</tr>
			[/#list]
			<tr>
			    <td colspan ="5">
					<span class="fieldSet">
						<input type="text" name="logo" class="text" value="${setting.logo}" maxlength="200" />
						<a href="javascript:;" id="filePicker" class="button">${message("admin.upload.filePicker")}</a>
					</span>
				</td>
			</tr>
			<!--tr>
			    <th  id="showProgress" colspan="11" border="5"/>
			</tr-->
			<tr>
			    <th>
					<span>FileName</span>
				</th>
				<th>
					<span>FileSize(KB)</span>
				</th>
				<!--th>
					<span>SourcePath</span>
				</th-->
				<th>
					<span>DestPath</span>
				</th>
				<th>
					<span>HttpStart</span>
				</th>
				<th>
					<span>HttpEnd</span>
				</th>
				<th>
					<span>HttpRate(KB/S)</span>
				</th>
				<th>
					<span>StorageType</span>
				</th>
				<th>
					<span>StorageStart</span>
				</th>
				<th>
					<span>StorageEnd</span>
				</th>
				<th>
					<span>StorageRate(KB/S)</span>
				</th>
				 <th>
					<span>Preview</span>
				</th>
			</tr>
			
			[#list uploadTasks as uploadTask]
				<tr>
					<td>
						${uploadTask.fileName!'-'}
					</td>
					<td>
						${uploadTask.fileSize!'-'}
					</td>
					<!--td>
						${uploadTask.sourcePath!'-'}
					</td-->
					
					<td>
						${uploadTask.destPath!'-'}
					</td>
					
					<td>
						[#if uploadTask.httpStart??]
						${uploadTask.httpStart?string.medium}
						[#else]
						-
						[/#if]	
					</td>
					<td>
						[#if uploadTask.httpEnd??]
						${uploadTask.httpEnd?string.medium}
						[#else]
						-
						[/#if]
					</td>
					<td>
						${uploadTask.httpRate!'-'}
					</td>
					<td>
						${uploadTask.storageType!'-'}
					</td>
					<td>
						[#if uploadTask.storageStart??]
						${uploadTask.storageStart?string.medium}
						[#else]
						-
						[/#if]
					</td>
					<td>
					    [#if uploadTask.storageEnd??]
						${uploadTask.storageEnd?string.medium}
						[#else]
						-
						[/#if]
					</td>
					<td>
						${uploadTask.storageRate!'-'}
					</td>
					<td>
						[#if uploadTask.viewLink??]
							<a href="${uploadTask.viewLink}" target="_blank">View</a>
						[#else]
							View
						[/#if]
					</td>
				</tr>
			[/#list]
		</table>
	</form>
</body>
</html>
[/#escape]