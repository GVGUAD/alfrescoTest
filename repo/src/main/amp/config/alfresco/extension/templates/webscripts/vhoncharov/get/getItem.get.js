<import resource="classpath:/alfresco/extension/templates/webscripts/vhoncharov/ContentModelHelper.js">;

function main() {
  var request = "TYPE:\"vh:document\"";
  if (args.name) {
    request += " AND @cm\\:name:\"" + args.name + "\"";
  }
  if (args.appVersion) {
    request += " AND @vh\\:appVersion:\"" + args.appVersion + "\"";
  }
  if (args.appDescription) {
    request += " AND @vh\\:appDescription:\"" + args.appDescription + "\"";
  }
  if (args.region) {
    request += " AND @vh\\:region:\"" + args.region + "\"";
  }

  var applicationNodes = search.luceneSearch(request);
  if (applicationNodes == null || applicationNodes.length === 0) {
    status.code = 404;
    status.message = "No VH documents found";
    status.redirect = true;
  } else {
    var applicationDocInfos = new Array();
    for (var i = 0; i < applicationNodes.length; i++) {
      applicationDocInfos[i] = new ApplicationInfo(applicationNodes[i]);
    }
    model.vhDocs = applicationDocInfos;
    return model;
  }
}

main();