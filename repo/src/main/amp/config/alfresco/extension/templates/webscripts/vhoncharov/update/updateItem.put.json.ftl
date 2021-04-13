<#assign datetimeformat="EEE, dd MMM yyyy HH:mm:ss zzz">
{
    "nodeRef"       : "${node.ref}",
    "name"          : "${node.name}",
    "creator"       : "${node.creator!"Unknown"}",
    "createdDate"   : "${node.createdDate?string(datetimeformat)}",
    "modifier"      : "${node.modifier!"Unknown"}",
    "modifiedDate"  : "${node.modifiedDate?string(datetimeformat)}",
    "appVersion"    : "${node.appVersion!"Unknown"}",
    "appDescription": "${node.appDescription!"Unknown"}",
    "region"        : "${node.region!"Unknown"}"
}