//操作左侧菜单与中间tab区

//在右边center区域打开菜单，新增tab
function Open(text, url) {
    if ($("#tabs").tabs('exists', text)) {
        $('#tabs').tabs('select', text);
    } else {
        $('#tabs').tabs('add', {
            title : text,
            closable : true,
            content :'<iframe src="'+url+'" frameBorder="0" border="0"  style="width: 100%; height: 100%;"/>'
        });
    }
}

//几个关闭事件的实现
function CloseTab(menu, type) {
    var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tabs");
	
    if (type === "close") {
        tabs.tabs("close", curTabTitle);
        return;
    }

    var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];

    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != curTabTitle && type === "Other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && type === "All") {
            closeTabsTitle.push(opt.title);
        }
    });

    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}