function main() {
  var noderef = url.templateArgs.noderef;
  var document = search.findNode(noderef);

  try {
    document.remove();
    model.msg = "Item was removed";
  } catch (e) {
    model.msg = e;
    return;
  }
}

main();
