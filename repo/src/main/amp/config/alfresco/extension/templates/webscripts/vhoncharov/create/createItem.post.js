function main() {
  var folderType = "vh:device";
  var contentType = "vh:application";
  var documentName = url.templateArgs.documentName;
  var pathItems = documentName.split("/");
  var currentNode = companyhome;
  for (var i = 0; i < pathItems.length - 1; i++) {
    var node = currentNode.createNode(pathItems[i], folderType);
    if (node != null) {
      currentNode = node;
    } else {
      model.msg = "Failed to create folder!";
      return;
    }
  }
  currentNode.createNode(pathItems[pathItems.length - 1], contentType);
  if (currentNode != null) {
    model.document = currentNode;
    model.msg = "Created OK!";
  } else {
    model.msg = "Failed to create application!";
    return;
  }
}

main();