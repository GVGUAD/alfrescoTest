var applicationProperties = {
  name: "cm:name",
  modified: "cm:modified",
  creator: "cm:creator",
  created: "cm:created",
  region: "vh:region",
  appVersion: "vh:appVersion",
  appDescription: "vh:appDescription"
};
function ApplicationInfo(doc) {
  this.ref = doc.nodeRef;
  this.name = doc.properties.name;
  this.creator = doc.properties.creator;
  this.createdDate = doc.properties.created;
  this.modifier = doc.properties.modifier;
  this.modifiedDate = doc.properties.modified;
  this.appVersion = doc.properties[applicationProperties.appVersion];
  this.appDescription = doc.properties[applicationProperties.appDescription];
  this.region = doc.properties[applicationProperties.region];
}