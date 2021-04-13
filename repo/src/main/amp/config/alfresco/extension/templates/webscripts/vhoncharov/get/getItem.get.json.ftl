<#assign datetimeformat="EEE, dd MMM yyyy HH:mm:ss zzz">
{
"vhDocs" : [
<#list vhDocs as vhDoc>
    {
    "nodeRef"       : "${vhDoc.ref}",
    "name"          : "${vhDoc.name}",
    "creator"       : "${vhDoc.creator!"Unknown"}",
    "createdDate"   : "${vhDoc.createdDate?string(datetimeformat)}",
    "modifier"      : "${vhDoc.modifier!"Unknown"}",
    "modifiedDate"  : "${vhDoc.modifiedDate?string(datetimeformat)}",
    "appVersion"    : "${vhDoc.appVersion!"Unknown"}",
    "appDescription": "${vhDoc.appDescription!"Unknown"}",
    "region"        : "${vhDoc.region!"Unknown"}"
    }
    <#if vhDoc_has_next>,</#if>
</#list>
]
}