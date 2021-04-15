// <import resource="classpath:/alfresco/extension/templates/webscripts/vhoncharov/ContentModelHelper.js">

  function main() {
  var noderef = url.templateArgs.noderef;
  var node = search.findNode(noderef);
  if(json.has("name")) node.properties[applicationProperties.name] = json.get("name");
  if(json.has("region")) node.properties[applicationProperties.region] = json.get("region");
  if(json.has("appVersion"))node.properties[applicationProperties.appVersion] = json.get("appVersion");
  if(json.has("appDescription")) node.properties[applicationProperties.appDescription] = json.has("appDescription");
  node.save();
  model.node = new ApplicationInfo(node);
  return model;
}
  main();