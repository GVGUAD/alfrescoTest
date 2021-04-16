<html>
<body>
<#if error??>
    <p>Something went wrong</p>
    <p>${error}</p>
<#else>
    <p>Signaled ${args.id} for transition ${args.action}</p>
</#if>
</body>
</html>